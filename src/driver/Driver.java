package driver;

import adapter.BuildAuto;
import exception.AutoException;
import scale.EditOptions;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class Driver {

    public static void main(String[] args) throws AutoException {
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String filename = "fordfocuswagonztw.txt"; // Fake file name

        // Create an instance of Automobile using the CreateAuto interface
        buildAuto.buildAuto(filename);

        // Create two instances of EditOptions to test threading
        EditOptions firstModifier;
        EditOptions secondModifier;

        // Strings for the field that we will modify
        String automobileModel = "Ford Focus Wagon ZTW";
        String optionSetName = "Transmission";

        // Update an option set
        firstModifier = new EditOptions(buildAuto);
        firstModifier.updateOptionSetName(automobileModel, optionSetName, "Something");

        // Wait a bit
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Update the same option set from another thread
        secondModifier = new EditOptions(buildAuto);
        secondModifier.updateOptionSetName(automobileModel, optionSetName, "Something Else");

        // Print the automobile instance
        buildAuto.printAuto(automobileModel);

        // Update tests from Lab 3
        /*
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
        */
    }
}
