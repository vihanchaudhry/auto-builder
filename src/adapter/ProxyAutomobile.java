package adapter;

import exception.AutoException;
import model.Automobile;
import util.AutomobileReader;

/**
 * Created by Vihan on 1/24/2016.
 */
public abstract class ProxyAutomobile {

    private static Automobile automobile;

    public void buildAuto(String filename) throws AutoException {
        AutomobileReader reader = new AutomobileReader();
        automobile = reader.buildAutoObject(filename);
    }

    public void printAuto() {
        // Model is never used
        System.out.println(automobile.toString());
    }

    public void updateOptionSetName(String optionSetName, String newName) {
        // It seems that modelName won't be used
        automobile.updateOptionSetName(optionSetName, newName);

    }

    public void updateOptionPrice(String optionSetName, String optionName, double newPrice) {
        // It seems that modelName and option won't be used
        automobile.updateOptionPrice(optionSetName, optionName, newPrice);
    }

    public void fix() {

    }
}
