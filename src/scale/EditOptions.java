package scale;

import model.Automobile;

import java.util.LinkedHashMap;

/**
 * Created by Vihan on 2/9/2016.
 */
public class EditOptions extends Thread {

    public void updateOptionSetName(LinkedHashMap<String, Automobile> automobiles, String automobileModel, String optionSetName, String newName) {
        automobiles.get(automobileModel).updateOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(LinkedHashMap<String, Automobile> automobiles, String automobileModel, String optionSetName, String optionName, double newPrice) {
        automobiles.get(automobileModel).updateOptionPrice(optionSetName, optionName, newPrice);
    }
}
