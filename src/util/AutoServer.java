package util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vihan on 3/7/16.
 */
public class AutoServer {
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
    }
}
