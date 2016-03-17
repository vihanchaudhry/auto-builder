package util;

import exception.AutoException;
import exception.FixUtil;
import model.Automobile;

import java.io.*;
import java.util.ArrayList;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class AutomobileIO {

    public Automobile buildAutoFromText(String filename) throws AutoException {
        try {
            FileReader file = null;
            try {
                file = new FileReader(filename);
            } catch (IOException e) {
                throw new AutoException(AutoException.ERROR_INCORRECT_FILENAME);
            }

            BufferedReader buff = new BufferedReader(file);
            String line = buff.readLine();
            String splitLine[] = line.split(",");
            Automobile automobile = null;
            try {
                automobile = new Automobile(splitLine[0], splitLine[1], Double.parseDouble(splitLine[2]), 5);
            } catch (ArrayIndexOutOfBoundsException e) {
                automobile = new FixUtil().fixAutoPriceMissing(automobile, splitLine[0], splitLine[1]);
            }

            boolean eof = false;
            while (!eof) {
                line = buff.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    buildOptionSetObject(automobile, line);
                }
            }
            buff.close();
            file.close();
            return automobile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Automobile buildAutoFromProperties(Properties properties) {

        Automobile automobile = new Automobile(properties.getProperty("CarMake"), properties.getProperty("CarModel"), 0, 5);

        int optionCount = 1; // Start at Option 1
        String currentOption;
        String currentOptionValue;

        while ((currentOption = properties.getProperty("Option" + optionCount)) != null) {
            ArrayList<String> optionSetList = new ArrayList<>(20);
            optionSetList.add(currentOption);
            char optionValueCount = 'a'; // Start at OptionValue a

            while ((currentOptionValue = properties.getProperty("OptionValue" + optionCount + optionValueCount)) != null) {
                optionSetList.add(currentOptionValue);
                optionSetList.add("0");
                optionValueCount++;
            }

            try {
                String optionSetArray[] = new String[optionSetList.size()];
                optionSetArray = optionSetList.toArray(optionSetArray);
                automobile.addOptionSet(optionSetArray);
            } catch (AutoException e) {
                System.err.println("Failed to build option set " + optionCount + optionValueCount + " from Properties");
                System.exit(1);
            }
            optionCount++;
        }

        return automobile;
    }

    public void buildOptionSetObject(Automobile automobile, String line) throws AutoException {
        String splitLine[] = line.split(",");
        automobile.addOptionSet(splitLine);
    }

    public void serializeAutomobile(Automobile automobile) throws AutoException {
        String filename = automobile.getModel().toLowerCase().replaceAll(" ", "") + ".ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(automobile);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new AutoException(AutoException.ERROR_SERIALIZATION_FAILED);
        }

        System.out.println("Serialized data is saved in " + filename + "\n");
    }

    public Automobile deserializeAutomobile(String filename) throws IOException, AutoException {
        Automobile automobile;
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        try {
            automobile = (Automobile) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new AutoException(AutoException.ERROR_DESERIALING_AUTO);
        }
        in.close();
        fileIn.close();
        return automobile;
    }

    public Properties deserializeProperties(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Properties properties = (Properties) in.readObject();
        in.close();
        return properties;
    }

    public Properties parseProperties(String filename) throws IOException {
        Properties properties = new Properties();
        FileReader file = new FileReader(filename);
        BufferedReader in = new BufferedReader(file);
        properties.load(in);
        return properties;
    }
}
