package by.epamlab.webdriver_advanced.service;

import java.util.ResourceBundle;

public class ConfigReader {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("configs");

    private ConfigReader() {
        throw new AssertionError("Cannot be instantiated directly.");
    }

    public static String get(String key) {
        return resourceBundle.getString(key);
    }
}
