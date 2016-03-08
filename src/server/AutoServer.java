package server;

import exception.AutoException;
import util.Properties;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by vihan on 3/7/16.
 */
public interface AutoServer {
    void buildAutoFromProperties(Properties properties);
}