package client;

import model.Automobile;
import util.AutomobileIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

    protected ObjectInputStream in;
    protected ObjectOutputStream out;
    protected Socket clientSocket;
    protected String strHost;
    protected int iPort;

    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }//constructor

    public static void main(String[] args) {
        /* debug main; does daytime on local host */
        String strLocalHost = null;

        try {
            strLocalHost = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
        }

        DefaultSocketClient d = new DefaultSocketClient(strLocalHost, iDAYTIME_PORT);
        d.start();
    }

    @Override
    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }//run

    @Override
    public boolean openConnection() {
        try {
            clientSocket = new Socket(strHost, iPort);
            System.out.println("Client Socket opened on: " + strHost + ":" + iPort);
        } catch (IOException socketError) {
            if (DEBUG) System.err.println
                    ("Unable to connect to " + strHost);
            return false;
        }
        try {
            //in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }

    @Override
    public void handleSession() {
        AutomobileIO automobileIO = new AutomobileIO();
        Scanner scanner = new Scanner(System.in);
        String clientInput;
        util.Properties properties = null;
        Automobile automobileForConfig = null;

        System.out.println("What would you like to do? (Enter a, b, or c)\n" +
                "\ta. Upload Properties file\n" +
                "\tb. Configure a car");
        clientInput = scanner.nextLine();

        if (clientInput.charAt(0) == 'a' || clientInput.charAt(0) == 'A') {
            System.out.println("Please provide the name of the .properties file (including the extension): ");
            clientInput = scanner.nextLine();

            try {
                properties = automobileIO.parseProperties(clientInput);
            } catch (IOException e) {
                System.err.println("Unable to open the file: " + clientInput);
                System.exit(1);
            }

            System.out.println("Valid file " + clientInput + " found! Sending to server...");

            try {
                out.writeObject(properties);
                System.out.println("Automobile successfully added!");
            } catch (IOException e) {
                System.err.println("Unable to send file to server");
                System.exit(1);
            }

        } else if (clientInput.charAt(0) == 'b' || clientInput.charAt(0) == 'B') {
            // Tell server to send list
            try {
                out.writeObject(clientInput.charAt(0));
            } catch (IOException e) {
                System.err.println("Unable to send char to server");
                System.exit(1);
            }

            // Start Input Stream
            try {
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                System.err.println("Could not get input stream");
                System.exit(1);
            }

            // Get list of automobile models
            ArrayList<String> modelList = null;
            try {
                System.out.println("Received list of automobiles: ");
                modelList = (ArrayList<String>) in.readObject();
                modelList.forEach(System.out::println);
            } catch (IOException e) {
                System.err.println("Could not get input stream array list");
                System.exit(1);
            } catch (ClassNotFoundException e) {
                System.err.println("Object is not an array list");
                System.exit(1);
            }

            // Request client input on which automobile to configure
            System.out.println("\nWhich automobile would you like to configure?");
            clientInput = scanner.nextLine();

            // Send the model name choice to the server
            try {
                if (modelList.contains(clientInput)) {
                    out.writeObject(clientInput);
                } else {
                    System.out.println("Automobile not found.");
                    System.exit(1);
                }
            } catch (IOException e) {
                System.err.println("Unable to send string to server");
                System.exit(1);
            }

            // TODO: Receive automobile object
            try {
                automobileForConfig = (Automobile) in.readObject();
                System.out.println("Received automobile! \n" + automobileForConfig.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // TODO: Configure automobile and print to screen
        } else {
            System.out.println("Invalid input ");
        }
    }

    public void sendOutput(String strOutput) {
        try {
            out.writeObject(strOutput);
        } catch (IOException e) {
            if (DEBUG)
                System.out.println("Error writing to " + strHost);
        }
    }

    public void handleInput(String strInput) {
        System.out.println(strInput);
    }

    @Override
    public void closeSession() {
        try {
            in = null;
            out = null;
            clientSocket.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }
}
