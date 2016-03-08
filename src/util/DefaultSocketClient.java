package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket sock;
    private String strHost;
    private int iPort;

    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }//constructor

    public static void main(String arg[]) {
   /* debug main; does daytime on local host */
        String strLocalHost = "";
        try {
            strLocalHost =
                    InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
        }
        DefaultSocketClient d = new DefaultSocketClient
                (strLocalHost, iDAYTIME_PORT);
        d.start();
    }

    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }//run

    public boolean openConnection() {
        try {
            sock = new Socket(strHost, iPort);
        } catch (IOException socketError) {
            if (DEBUG) System.err.println
                    ("Unable to connect to " + strHost);
            return false;
        }
        try {
            in = new ObjectInputStream(sock.getInputStream());
            out = new ObjectOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }

    public void handleSession() {
        String strInput = "";
        if (DEBUG) System.out.println("Handling session with " + strHost + ":" + iPort);
        try {
            while ((strInput = (String) in.readObject()) != null)
                handleInput(strInput);
        } catch (IOException e) {
            if (DEBUG) System.out.println("Handling session with " + strHost + ":" + iPort);
        } catch (ClassNotFoundException e) {
            if (DEBUG) System.out.println("ObjectInputStream is not a String");
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

    public void closeSession() {
        try {
            in = null;
            out = null;
            sock.close();
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