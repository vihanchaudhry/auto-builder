package server;

import adapter.BuildAuto;
import model.Automobile;

import java.io.ObjectInputStream;

/**
 * Created by vihan on 3/7/16.
 */
public class BuildCarModelOptions implements AutoServer {
    private BuildAuto buildAuto;

    public BuildCarModelOptions(BuildAuto buildAuto) {
        this.buildAuto = buildAuto;
    }

    @Override
    public void buildAutoFromProperties(ObjectInputStream properties) {
        buildAuto.buildAutoFromProperties(properties);
    }
}
