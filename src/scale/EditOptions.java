package scale;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.FixAuto;
import adapter.UpdateAuto;
import exception.AutoException;

/**
 * Created by Vihan on 2/9/2016.
 */
public class EditOptions extends Thread implements CreateAuto, UpdateAuto, FixAuto {
    private BuildAuto buildAuto;

    public EditOptions(BuildAuto buildAuto) {
        this.buildAuto = buildAuto;
    }

    @Override
    public void buildAuto(String filename) throws AutoException {
        buildAuto.buildAuto(filename);
    }

    @Override
    public void printAuto(String automobileModel) {
        buildAuto.printAuto(automobileModel);
    }

    @Override
    public void updateOptionSetName(String automobileModel, String optionSetName, String newName) {
        buildAuto.updateOptionSetName(automobileModel, optionSetName, newName);
    }

    @Override
    public void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice) {
        buildAuto.updateOptionPrice(automobileModel, optionSetName, optionName, newPrice);
    }

    @Override
    public void fix() {
        buildAuto.fix();
    }
}
