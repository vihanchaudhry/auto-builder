package util;

/**
 * Created by vihan on 3/7/16.
 */
public interface SocketClientInterface {
    boolean openConnection();

    void handleSession();

    void closeSession();
}
