package util;

import exception.AutoException;
import model.Automobile;

import java.io.*;

/**
 * Created by Vihan on 1/11/2016.
 */
public class AutomobileSerializer {

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
}
