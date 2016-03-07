package model;

import exception.AutoException;
import model.OptionSet.Option;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class Automobile implements Serializable {

    private String make;
    private String model;
    private double basePrice;
    private ArrayList<OptionSet> optionSets;
    private ArrayList<Option> choices;

    // Testing
    private boolean available;

    // Constructors
    public Automobile() {
        optionSets = new ArrayList<>(); // Default size = 10
        choices = new ArrayList<>();

        available = true;
    }

    public Automobile(String make, String model, double basePrice, int size) {
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        optionSets = new ArrayList<>(size);
        choices = new ArrayList<>(size);

        available = true;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public synchronized String getModel() {
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
        double totalPrice = basePrice;
        for (Option choice : choices) {
            totalPrice += choice.getPrice();
        }
        return (int) Math.round(totalPrice);
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

    public ArrayList<Option> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Option> choices) {
        this.choices = choices;
    }

    public Option getOptionChoice(String optionSetName) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                return optionSet.getChoice();
            }
        }
        return null;
    }

    public String getOptionChoiceName(String optionSetName) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                return optionSet.getChoice().getName();
            }
        }
        return null;
    }

    public double getOptionChoicePrice(String optionSetName) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                return optionSet.getChoice().getPrice();
            }
        }
        return 0;
    }

    public void setOptionChoice(String optionSetName, String optionName) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                optionSet.setChoiceByName(optionName);
                choices.add(optionSet.getChoice());
            }
        }
    }

    @Override
    public synchronized String toString() {
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

    public synchronized void updateOptionSetName(String optionSetName, String newName) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(optionSetName)) {
                optionSet.setName(newName);
            }
        }
    }

    public synchronized void updateOptionPrice(String optionSetName, String optionName, double newPrice) {
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

    public synchronized void buildOptionSet(String splitLine[]) throws AutoException {
        int numberOfOptions = (splitLine.length - 1) / 2;
        OptionSet optionSet = new OptionSet(splitLine[0], numberOfOptions);
        optionSet.addOptions(splitLine, numberOfOptions);
        this.optionSets.add(optionSet);
    }
}
