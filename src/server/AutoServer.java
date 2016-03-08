package server;

import exception.AutoException;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by vihan on 3/7/16.
 */
public interface AutoServer {
    void buildAutoFromProperties(ObjectInputStream properties) throws IOException, ClassNotFoundException, AutoException;
}