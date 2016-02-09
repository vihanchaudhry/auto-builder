package adapter;

/**
 * Created by Vihan on 1/24/2016.
 */
public interface UpdateAuto {

    void updateOptionSetName(String automobileModel, String optionSetName, String newName);

    void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice);
}
