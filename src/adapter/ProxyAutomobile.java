package adapter;

import exception.AutoException;
import model.Automobile;
import util.AutomobileIO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;
import java.util.Properties;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public abstract class ProxyAutomobile {

    private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<>();

    public void buildAuto(String filename) throws AutoException {
        addAuto(filename);
    }

    public void printAuto(String automobileModel) {
        System.out.println(automobiles.get(automobileModel));
    }

    public void updateOptionSetName(String automobileModel, String optionSetName, String newName) {
        automobiles.get(automobileModel).updateOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice) {
        automobiles.get(automobileModel).updateOptionPrice(optionSetName, optionName, newPrice);
    }

    public void fix() {

    }

    public void buildAutoFromProperties(ObjectInputStream propertiesStream) throws IOException, ClassNotFoundException {
        AutomobileIO automobileIO = new AutomobileIO();
        util.Properties properties = automobileIO.deserializeProperties(propertiesStream);
        Automobile automobile = automobileIO.buildAutoObject(properties);
        automobiles.put(automobile.getModel(), automobile);
    }

    private void addAuto(String filename) throws AutoException {
        Automobile automobile = new AutomobileIO().buildAutoObject(filename);
        automobiles.put(automobile.getModel(), automobile);
    }

    private void removeAuto(String automobileModel) {
        automobiles.remove(automobileModel);
    }
}
