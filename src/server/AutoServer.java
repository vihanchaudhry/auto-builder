package server;

import util.Properties;

import java.util.ArrayList;

/**
 * Created by vihan on 3/7/16.
 */
public interface AutoServer {
    void buildAutoFromProperties(Properties properties);

    ArrayList<String> getModelList();

    void sendAutoToClient(String automobileModel);
}