package server;

import model.Automobile;
import util.Properties;

import java.util.ArrayList;

/**
 * Created by vihan on 3/7/16.
 */
public interface AutoServer {
    void buildAutoFromProperties(Properties properties);

    ArrayList<String> getModelList();

    Automobile getAutomobile(String automobileModel);
}