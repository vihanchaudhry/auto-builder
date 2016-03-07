package util;

import exception.AutoException;
import exception.FixUtil;
import model.Automobile;

import java.io.*;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class AutomobileIO {

    public Automobile buildAutoObject(String filename) throws AutoException {
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

    public void buildOptionSetObject(Automobile automobile, String line) throws AutoException {
        String splitLine[] = line.split(",");
        automobile.buildOptionSet(splitLine);
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

    public Properties parseProperties(String filename) throws IOException {
        Properties properties = new Properties();
        FileReader file = new FileReader(filename);
        BufferedReader in = new BufferedReader(file);
        properties.load(in);
        return properties;
    }
}
