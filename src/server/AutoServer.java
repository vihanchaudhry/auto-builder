package server;

import model.Automobile;

import java.io.ObjectInputStream;

/**
 * Created by vihan on 3/7/16.
 */
public interface AutoServer {
    void buildAutoFromProperties(ObjectInputStream properties);
}