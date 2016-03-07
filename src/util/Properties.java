package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by vihan on 3/6/16.
 */
public class Properties {
    private LinkedHashMap<String, String> properties;

    public LinkedHashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(LinkedHashMap<String, String> properties) {
        this.properties = properties;
    }

    private void putProperty(String splitLine[]) {
        properties.put(splitLine[0], splitLine[1]);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public void load(BufferedReader in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(in);
        String line = "";
        String []splitLine;
        while ((line = bufferedReader.readLine()) != null) {
            splitLine = line.split("=");
            putProperty(splitLine);
        }
    }
}
