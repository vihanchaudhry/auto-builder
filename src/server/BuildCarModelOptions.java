package server;

import adapter.BuildAuto;
import exception.AutoException;
import util.Properties;

import java.io.IOException;
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
    public void buildAutoFromProperties(Properties properties) {
        buildAuto.buildAutoFromProperties(properties);
    }
}
