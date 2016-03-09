package server;

import adapter.BuildAuto;
import util.Properties;

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

    public void printAuto(String automobileModel) {
        buildAuto.printAuto(automobileModel);
    }
}
