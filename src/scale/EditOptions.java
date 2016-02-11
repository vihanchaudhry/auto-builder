package scale;

import adapter.BuildAuto;
import adapter.UpdateAuto;

/**
 * Created by Vihan on 2/9/2016.
 */
public class EditOptions extends Thread implements UpdateAuto {
    private BuildAuto buildAuto;

    public EditOptions(BuildAuto buildAuto) {
        this.buildAuto = buildAuto;
    }

    @Override
    public void updateOptionSetName(String automobileModel, String optionSetName, String newName) {
        buildAuto.updateOptionSetName(automobileModel, optionSetName, newName);
    }

    @Override
    public void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice) {
        buildAuto.updateOptionPrice(automobileModel, optionSetName, optionName, newPrice);
    }
}
