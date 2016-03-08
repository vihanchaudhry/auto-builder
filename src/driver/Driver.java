package driver;

import adapter.BuildAuto;
import exception.AutoException;

import java.io.IOException;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 5
 * 03/07/16
 */
public class Driver {

    public static void main(String[] args) throws AutoException, IOException, ClassNotFoundException {
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String filename = "fordfocuswagonztw.txt";

        // Create an instance of Automobile using the CreateAuto interface
        buildAuto.buildAuto(filename);

        System.out.println("Reached end of program. Sockets aren't fully implemented yet");
    }
}
