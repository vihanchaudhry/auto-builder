package adapter;

import exception.AutoException;
import model.Automobile;
import util.AutomobileIO;
import util.Properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public abstract class ProxyAutomobile {

    private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<>();

    public void buildAuto(String filename) {
        try {
            addAuto(filename);
        } catch (AutoException e) {
            System.err.println("Failed to add automobile from file: " + filename);
            System.exit(1);
        }
    }

    public void printAuto(String automobileModel) {
        System.out.println(automobiles.get(automobileModel));
    }

    public ArrayList<String> getModelList() {
        Iterator it = automobiles.entrySet().iterator();
        ArrayList<String> list = new ArrayList<>(automobiles.size());

        while (it.hasNext()) {
            Map.Entry me = (Map.Entry) it.next();
            Automobile automobile = (Automobile) me.getValue();
            list.add(automobile.getModel());
        }

        return list;
    }

    public void updateOptionSetName(String automobileModel, String optionSetName, String newName) {
        automobiles.get(automobileModel).updateOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice) {
        automobiles.get(automobileModel).updateOptionPrice(optionSetName, optionName, newPrice);
    }

    public void fix() {

    }

    public void buildAutoFromProperties(Properties properties) {
        AutomobileIO automobileIO = new AutomobileIO();
        Automobile automobile = automobileIO.buildAutoFromProperties(properties);
        automobiles.put(automobile.getModel(), automobile);
    }

    private void addAuto(String filename) throws AutoException {
        Automobile automobile = new AutomobileIO().buildAutoFromText(filename);
        automobiles.put(automobile.getModel(), automobile);
    }

    private void removeAuto(String automobileModel) {
        automobiles.remove(automobileModel);
    }
}
