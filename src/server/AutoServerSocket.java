package server;

import adapter.BuildAuto;
import client.DefaultSocketClient;
import model.Automobile;
import util.Properties;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by vihan on 3/7/16.
 */
public class AutoServerSocket extends DefaultSocketClient {

    private BuildCarModelOptions buildCarModelOptions;

    public AutoServerSocket(String strHost, int iPort, Socket clientSocket, BuildAuto buildAuto) {
        super(strHost, iPort);
        this.clientSocket = clientSocket;
        System.out.println("Client socket accepted on " + strHost + ":" + iPort);
        buildCarModelOptions = new BuildCarModelOptions(buildAuto);
    }

    @Override
    public boolean openConnection() {
        if (clientSocket.isConnected())
            return true;

        return false;
    }

    @Override
    public void handleSession() {
        Properties properties;
        char option;
        String model;
        Automobile autoToClient;
        Object tempObj;

        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain ObjectInputStream on port " + iPort);
            System.exit(1);
        }

        try {
            tempObj = in.readObject();
            if (tempObj instanceof Properties) {
                if ((properties = (Properties) tempObj) != null) {
                    buildCarModelOptions.buildAutoFromProperties(properties);
                    System.out.println("Successfully added automobile " + properties.getProperty("CarModel") + " to the list");
                }

                System.out.println("\nNewly added automobile: ");
                buildCarModelOptions.printAuto(properties.getProperty("CarModel"));
            } else if (tempObj instanceof Character) {
                option = Character.toLowerCase((Character) tempObj);
                if (option == 'b') {
                    ArrayList<String> modelList = buildCarModelOptions.getModelList();
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(modelList);
                    System.out.println("Sent auto list");
                }

                // Receive client model choice
                // Send back a copy of that model
                tempObj = in.readObject();
                if (tempObj instanceof String) {
                    model = (String) tempObj;
                    autoToClient = buildCarModelOptions.getAutomobile(model);
                    out.writeObject(autoToClient);
                    System.out.println("Automobile sent to client for configuration");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Incoming object is not an instance of a valid class");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Unable to receive object from client");
            System.exit(1);
        }
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
}
