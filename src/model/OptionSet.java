package model;

import exception.AutoException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Vihan on 1/9/2016.
 */
public class OptionSet implements Serializable {

    private String name;
    private ArrayList<Option> options;

    // Constructors
    protected OptionSet(String name, int size) {
        this.name = name;
        options = new ArrayList<>(size);
    }

    // Getters
    protected String getName() {
        return name;
    }

    // Setters
    protected void setName(String name) {
        this.name = name;
    }

    protected Option getOption(int index) {
        return options.get(index);
    }

    protected ArrayList<Option> getOptions() {
        return options;
    }

    protected void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    protected void setOption(int index, Option option) {
        this.options.set(index, option);
    }

    protected void addOptions(String splitLine[], int numberOfOptions) throws AutoException {
        for (int i = 0; i < numberOfOptions; i++) {
            try {
                this.options.add(new Option(splitLine[i * 2 + 1], Double.parseDouble(splitLine[i * 2 + 2])));
            } catch (NumberFormatException e) {
                throw new AutoException(AutoException.ERROR_OPTION_DATA_MISSING);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\tOption Set: ").append(name)
                .append("\n\tOptions: ");
        for (Option option : options) {
            sb.append(option.toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    // Searching
    protected Option findOptionByName(String name) {
        for (Option option : options) {
            if (option.getName() == name) {
                return option;
            }
        }
        return null;
    }

    // Updating
    protected boolean updateOptionByName(String name, Option newOption) {
        for (Option option : options) {
            if (option.getName() == name) {
                option = newOption;
                return true;
            }
        }
        return false;
    }

    protected void updateOptionName(String optionName, double newPrice) {
        for (Option option : options) {
            if (option.getName().equals(optionName)) {
                option.setPrice(newPrice);
            }
        }
    }

    // Deleting
    protected boolean deleteOptionByName(String name) {
        for (Option option : options) {
            if (option.getName() == name) {
                options.remove(option);
                return true;
            }
        }
        return false;
    }

    // Inner class
    // Collected by OptionSet
    private class Option implements Serializable {

        private String name;
        private double price;

        // Constructor
        protected Option(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // Getters
        protected String getName() {
            return name;
        }

        // Setters
        protected void setName(String name) {
            this.name = name;
        }

        protected double getPrice() {
            return price;
        }

        protected void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\t\t").append(name)
                    .append("; Price: ");
            if (price != 0) {
                sb.append("$").append(price);
            } else {
                sb.append("N/A");
            }
            return sb.toString();
        }
    }
}
