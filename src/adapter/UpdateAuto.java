package adapter;

/**
 * Created by Vihan on 1/24/2016.
 */
public interface UpdateAuto {

    void updateOptionSetName(String optionSetName, String newName);

    void updateOptionPrice(String optionSetName, String optionName, double newPrice);
}
