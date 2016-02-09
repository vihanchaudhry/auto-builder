package driver;

import adapter.BuildAuto;
import exception.AutoException;

public class Driver {

    public static void main(String[] args) throws AutoException {
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String filename = "fordfocuswagonztw.txt"; // Fake file name

        // Create an instance of Automobile using the CreateAuto interface
        buildAuto.buildAuto(filename);

        // Print the Automobile object using the CreateAuto interface
        buildAuto.printAuto();

        // Update an OptionSet name using the UpdateAuto interface
        System.out.println("Updating OptionSet Side Impact Air Bags, then printing again...\n");
        buildAuto.updateOptionSetName("Ford Focus Wagon ZTW", "Side Impact Air Bags", "Rainbows");

        // Update an Option price using the UpdateAuto interface
        buildAuto.updateOptionPrice("Ford Focus Wagon ZTW", "Rainbows", "Yes", 700);

        // Print object again to test changes
        // "Side Impact Air Bags" should be "Rainbows"
        // Price of option "Yes" should be 700 instead of 350
        buildAuto.printAuto();
    }
}
