package client;

import util.AutomobileIO;
import util.Properties;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by vihan on 3/7/16.
 */
public class CarModelOptionsIO {
    private AutomobileIO automobileIO;

    public CarModelOptionsIO() {
        automobileIO = new AutomobileIO();
    }

    public void run() throws IOException {
        Socket clientSocket = null;
        ObjectOutputStream toServer = null;
        BufferedReader fromServer = null;

        try {
            clientSocket = new Socket("10.41.66.140", 8080);
            toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to host.");
            System.exit(1);
        }

        String servLine;

        while ((servLine = fromServer.readLine()) != null) {
            System.out.println("Server says: " + servLine);

            uploadProperties("toyotaprius.properties");
            FileInputStream fileIn = new FileInputStream("toyotaprius.properties.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            toServer.writeObject(in);
            System.out.println("Wrote to server successfully!");
        }

        toServer.close();
        fromServer.close();
        clientSocket.close();
    }

    public void uploadProperties(String filename) throws IOException {
        Properties properties = automobileIO.parseProperties(filename);
        automobileIO.serializeProperties(properties);
    }
}
