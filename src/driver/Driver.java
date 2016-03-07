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
        String filename = "fordfocuswagonztw.txt";

        // Create an instance of Automobile using the CreateAuto interface
        buildAuto.buildAuto(filename);

        // Create two instances of EditOptions to test synchronization
        EditOptions firstModifier = new EditOptions(buildAuto);
        EditOptions secondModifier = new EditOptions(buildAuto);

        // Start both threads
        firstModifier.start();
        secondModifier.start();

        // Name of the model that we will modify
        String automobileModel = "Focus Wagon ZTW";

        // Update the same option set from two different threads
        firstModifier.updateOptionSetName(automobileModel, "Power Moonroof", "Something");
        secondModifier.updateOptionSetName(automobileModel, "Something", "Nothing");

        // Update the same option price from two different threads
        secondModifier.updateOptionPrice(automobileModel, "Nothing", "No", -100);
        firstModifier.updateOptionPrice(automobileModel, "Nothing", "No", -295);

        // Print the automobile instance
        buildAuto.printAuto(automobileModel);
    }
}
