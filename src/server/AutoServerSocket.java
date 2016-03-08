package server;

import adapter.BuildAuto;
import client.DefaultSocketClient;
import util.Properties;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

/**
 * Created by vihan on 3/7/16.
 */
public class AutoServerSocket extends DefaultSocketClient {

    private BuildCarModelOptions buildCarModelOptions;
    private ServerSocket serverSocket;

    public AutoServerSocket(String strHost, int iPort, BuildAuto buildAuto) {
        super(strHost, iPort);
        buildCarModelOptions = new BuildCarModelOptions(buildAuto);
    }

    @Override
    public boolean openConnection() {
        try {
            serverSocket = new ServerSocket(iPort);
            System.out.println("Server Socket listening on port: " + iPort);
        } catch (IOException e) {
            if (DEBUG) System.err.println("Could on listen on port: " + iPort);
            return false;
        }

        try {
            clientSocket = serverSocket.accept();
            System.out.println("Client Socket opened on: " + strHost + ":" + iPort);
        } catch (IOException e) {
            if (DEBUG) System.err.println("Accept failed");
            return false;
        }

        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain stream to/from port " + iPort);
            return false;
        }

        return true;
    }

    @Override
    public void handleSession() {
        Properties properties;

        try {
            if ((properties = (Properties) in.readObject()) != null) {
                buildCarModelOptions.buildAutoFromProperties(properties);
                System.out.println("Successfully added automobile to the list");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Incoming object is not an instance of Properties");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Unable to receive file from client");
            System.exit(1);
        }
    }

    @Override
    public void closeSession() {
        try {
            in = null;
            out = null;
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    /*@Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
        ObjectInputStream fromClient = new ObjectInputStream(clientSocket.getInputStream());

        while ((fromClient = (ObjectInputStream) fromClient.readObject()) != null) {
            buildCarModelOptions.buildAutoFromProperties(fromClient);
            toClient.println("Successfully created and added automobile object to list.");
        }
        toClient.close();
        fromClient.close();
        clientSocket.close();
        serverSocket.close();
    }*/
}
