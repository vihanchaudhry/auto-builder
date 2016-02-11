package adapter;

import exception.AutoException;

/**
 * Created by Vihan on 1/24/2016.
 */
public interface CreateAuto {
    void buildAuto(String filename) throws AutoException;

    void printAuto(String automobileModel);
}
