package exception;

import model.Automobile;

import java.util.Scanner;

/**
 * Created by Vihan on 1/26/2016.
 */
public class FixUtil {

    public void fixIncorrectFilename() {
    }

    public Automobile fixAutoPriceMissing(Automobile automobile, String name) {
        System.out.println("Automobile price not found. Please enter a new price: ");
        Scanner in = new Scanner(System.in);
        Double newPrice = in.nextDouble();
        System.out.println("Setting automobile price to $" + newPrice + "\n");
        return new Automobile(name, newPrice, 5);
    }

    public void fixSerializationFailed() {
    }

    public void fixDeserializingAuto() {
    }
}
