package server;

import adapter.BuildAuto;
import client.DefaultSocketClient;
import util.Properties;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

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
            System.out.println("Client Socket accepted on: " + strHost + ":" + iPort);
        } catch (IOException e) {
            if (DEBUG) System.err.println("Accept failed");
            return false;
        }

        return true;
    }

    @Override
    public void handleSession() {
        Properties properties = null;
        char option;
        String model = null;

        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain stream to/from port " + iPort);
            System.exit(1);
        }

        try {
            if (in.readObject() instanceof Properties) {
                if ((properties = (Properties) in.readObject()) != null) {
                    buildCarModelOptions.buildAutoFromProperties(properties);
                    System.out.println("Successfully added automobile to the list");
                }

                System.out.println("\nNewly added automobile: \n");
                buildCarModelOptions.printAuto(properties.getProperty("CarModel"));
            } else if (in.readObject() instanceof Character) {
                if ((option = (Character) in.readObject()) == 'b' || (option = (Character) in.readObject()) == 'B') {
                    ArrayList<String> modelList = buildCarModelOptions.getModelList();
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(modelList);
                }
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
}
