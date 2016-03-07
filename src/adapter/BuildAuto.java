package adapter;

import model.Automobile;
import server.AutoServer;

import java.io.ObjectInputStream;

/**
 * Vihan Chaudhry
 * CIS 35B
 * Lab 3
 * 02/08/16
 */
public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, AutoServer {
}
