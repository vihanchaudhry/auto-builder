package util;

import adapter.BuildAuto;
import server.BuildCarModelOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    public void run() throws IOException {
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

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


    }
}
