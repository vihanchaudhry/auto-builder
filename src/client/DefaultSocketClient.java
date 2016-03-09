package client;

import util.AutomobileIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
            } catch (IOException e) {
                System.err.println("Unable to send file to server");
                System.exit(1);
            }

        } else if (clientInput.charAt(0) == 'b' || clientInput.charAt(0) == 'B') {
            // TODO: Configure a car
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