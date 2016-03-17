package adapter;

/**
 * Created by vihan on 3/16/16.
 */
public interface EditThread {

    void threadedUpdateOptionSetName(String automobileModel, String optionSetName, String newName);

    void threadedUpdateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice);
}
