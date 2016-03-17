package driver;

import adapter.BuildAuto;
import client.SocketClientConstants;
import server.AutoServerSocket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 5
 * 03/07/16
 */
public class Driver {

    public static void main(String[] args) {
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String filename = "fordfocuswagonztw.txt";

        // Create an instance of Automobile using the CreateAuto interface
        buildAuto.buildAuto(filename);

        // Run server socket thread
        String strLocalHost = null;

        try {
            strLocalHost = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
            System.exit(1);
        }

        AutoServerSocket s = new AutoServerSocket(strLocalHost, SocketClientConstants.iDAYTIME_PORT, buildAuto);
        s.start();
    }
}
