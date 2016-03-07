package server;

import model.Automobile;

import java.io.ObjectInputStream;

/**
 * Created by vihan on 3/7/16.
 */
public interface AutoServer {
    Automobile buildAutoFromProperties(ObjectInputStream properties);
}