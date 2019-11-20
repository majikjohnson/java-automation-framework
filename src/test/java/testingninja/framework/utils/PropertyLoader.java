package testingninja.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private Properties properties;

    public PropertyLoader(String resourceName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(resourceName);
            properties = new Properties();
            properties.load(inputStream);
        } catch (NullPointerException | IOException e) {
            System.err.println("Unable to load resource file: " + resourceName);
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
