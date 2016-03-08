package driver;

import adapter.BuildAuto;
import client.CarModelOptionsIO;
import exception.AutoException;
import server.ServerHandler;

import java.io.IOException;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class Driver {

    public static void main(String[] args) throws AutoException, IOException, ClassNotFoundException {
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String filename = "fordfocuswagonztw.txt";

        // Create an instance of Automobile using the CreateAuto interface
        buildAuto.buildAuto(filename);

        ServerHandler serverHandler = new ServerHandler(buildAuto);
        CarModelOptionsIO carModelOptionsIO = new CarModelOptionsIO();
    }
}
