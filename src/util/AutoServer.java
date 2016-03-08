package util;

import adapter.BuildAuto;
import server.BuildCarModelOptions;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vihan on 3/7/16.
 */
public class AutoServer {
    private BuildCarModelOptions buildCarModelOptions;


    public AutoServer(BuildAuto buildAuto) {
        buildCarModelOptions = new BuildCarModelOptions(buildAuto);
    }

    public void run() throws IOException, ClassNotFoundException {
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
            toClient.println("Success!");
        }
        toClient.close();
        fromClient.close();
        clientSocket.close();
        serverSocket.close();
    }
}
