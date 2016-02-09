package adapter;

import exception.AutoException;
import model.Automobile;
import util.AutomobileReader;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Vihan on 1/24/2016.
 */
public abstract class ProxyAutomobile {

    private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<>();

    public void buildAuto(String filename) throws AutoException {
        AutomobileReader reader = new AutomobileReader();
        addAuto(filename);
    }

    public void printAuto() {
        Iterator iterator = automobiles.entrySet().iterator();
        Map.Entry me;
        while (iterator.hasNext()) {
            me = (Map.Entry) iterator.next();
            Automobile automobile = (Automobile) me.getValue();
            System.out.println(automobile);
        }
    }

    public void updateOptionSetName(String automobileModel, String optionSetName, String newName) {
        Iterator iterator = automobiles.entrySet().iterator();
        Map.Entry me;
        Automobile automobile;
        while (iterator.hasNext()) {
            me = (Map.Entry) iterator.next();
            automobile = (Automobile) me.getValue();
            if (automobile.getModel().equals(automobileModel)) {
                automobile.updateOptionSetName(optionSetName, newName);
            }
        }
    }

    public void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice) {
        Iterator iterator = automobiles.entrySet().iterator();
        Map.Entry me;
        Automobile automobile;
        while (iterator.hasNext()) {
            me = (Map.Entry) iterator.next();
            automobile = (Automobile) me.getValue();
            if (automobile.getModel().equals(automobileModel)) {
                automobile.updateOptionPrice(optionSetName, optionName, newPrice);
            }
        }
    }

    public void fix() {

    }

    public void addAuto(String filename) throws AutoException {
        AutomobileReader reader = new AutomobileReader();
        Automobile automobile = reader.buildAutoObject(filename);
        automobiles.put(automobile.getModel(), automobile);
    }

    public void removeAuto(String automobileModel) {
        Iterator iterator = automobiles.entrySet().iterator();
        Map.Entry me;
        Automobile automobile;
        while (iterator.hasNext()) {
            me = (Map.Entry) iterator.next();
            automobile = (Automobile) me.getValue();
            if (automobile.getModel().equals(automobileModel)) {
                automobiles.remove(automobileModel);
            }
        }
    }
}
