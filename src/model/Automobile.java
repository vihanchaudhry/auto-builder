package model;

import exception.AutoException;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Vihan on 1/9/2016.
 */
public class Automobile implements Serializable {

    private String make;
    private String model;
    private double basePrice;
    private ArrayList<OptionSet> optionSets;

    // Constructors
    public Automobile() {
        optionSets = new ArrayList<>(); // Default size = 10
    }

    public Automobile(String name, double basePrice, int size) {
        this.model = name;
        this.basePrice = basePrice;
        optionSets = new ArrayList<>(size);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getTotalPrice() {
        // TODO: Write this function after choice is done
        return 0;
    }

    public OptionSet getOptionSet(int index) {
        return optionSets.get(index);
    }

    public ArrayList<OptionSet> getOptionSets() {
        return optionSets;
    }

    public void setOptionSets(ArrayList<OptionSet> optionSets) {
        this.optionSets = optionSets;
    }

    public void setOptionSet(int index, OptionSet optionSet) {
        this.optionSets.set(index, optionSet);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Name: ").append(model)
                .append("\nBase Price: $").append(basePrice).append("\n");
        for (OptionSet optionSet : optionSets) {
            sb.append(optionSet.toString());
        }
        return sb.toString();
    }

    // Searching
    public OptionSet findOptionSetByName(String name) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName() == name) {
                return optionSet;
            }
        }
        return null;
    }

    // Updating
    public boolean updateOptionSetByName(String name, OptionSet newOptionSet) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName() == name) {
                optionSet = newOptionSet;
                return true;
            }
        }
        return false;
    }

    public void updateOptionSetName(String optionSetName, String newName) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                optionSet.setName(newName);
            }
        }
    }

    public void updateOptionPrice(String optionSetName, String optionName, double newPrice) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                optionSet.updateOptionName(optionName, newPrice);
            }
        }
    }

    // Deleting
    public boolean deleteOptionSetByName(String name) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName() == name) {
                optionSets.remove(optionSet);
                return true;
            }
        }
        return false;
    }

    public void buildOptionSet(String splitLine[]) throws AutoException {
        int numberOfOptions = (splitLine.length - 1) / 2;
        OptionSet optionSet = new OptionSet(splitLine[0], numberOfOptions);
        optionSet.addOptions(splitLine, numberOfOptions);
        this.optionSets.add(optionSet);
    }
}
