package util;

import exception.AutoException;
import exception.FixUtil;
import model.Automobile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class AutomobileReader {

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
                automobile = new Automobile(splitLine[0], Double.parseDouble(splitLine[1]), 5);
            } catch (ArrayIndexOutOfBoundsException e) {
                automobile = new FixUtil().fixAutoPriceMissing(automobile, splitLine[0]);
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
        automobile.getModel();
        automobile.buildOptionSet(splitLine);
    }
}
