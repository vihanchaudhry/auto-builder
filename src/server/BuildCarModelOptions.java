package server;

import adapter.BuildAuto;
import model.Automobile;
import util.Properties;

import java.util.ArrayList;

/**
 * Created by vihan on 3/7/16.
 */
public class BuildCarModelOptions implements AutoServer {

    private BuildAuto buildAuto;

    public BuildCarModelOptions(BuildAuto buildAuto) {
        this.buildAuto = buildAuto;
    }

    @Override
    public void buildAutoFromProperties(Properties properties) {
        buildAuto.buildAutoFromProperties(properties);
    }

    @Override
    public ArrayList<String> getModelList() {
        return buildAuto.getModelList();
    }

    @Override
    public Automobile getAutomobile(String automobileModel) {
        return buildAuto.getAutomobile(automobileModel);
    }

    public void printAuto(String automobileModel) {
        buildAuto.printAuto(automobileModel);
    }
}
