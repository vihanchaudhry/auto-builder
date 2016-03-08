package client;

import util.AutomobileIO;
import util.Properties;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by vihan on 3/7/16.
 */
public class CarModelOptionsIO {
    private AutomobileIO automobileIO;

    public CarModelOptionsIO() throws IOException {
        automobileIO = new AutomobileIO();
    }
}
