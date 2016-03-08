package util;

import adapter.BuildAuto;
import exception.AutoException;
import server.BuildCarModelOptions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vihan on 3/7/16.
 */
public class ServerHandler {
    private BuildCarModelOptions buildCarModelOptions;


    public ServerHandler(BuildAuto buildAuto) {
        buildCarModelOptions = new BuildCarModelOptions(buildAuto);
    }

    public void run() throws IOException, ClassNotFoundException, AutoException {
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
    }
}
