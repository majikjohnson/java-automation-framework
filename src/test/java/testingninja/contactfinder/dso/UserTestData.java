package testingninja.contactfinder.dso;

import org.json.simple.JSONObject;
import testingninja.framework.utils.JsonDataLoader;

import java.io.IOException;

public class UserTestData {
    private JSONObject data;

    public UserTestData(String resourceName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String fileName = classLoader.getResource(resourceName).getFile();
            data = JsonDataLoader.loadJsonObject(fileName);
        } catch (NullPointerException | IOException e) {
            System.err.println("Unable to load resource file: " + resourceName);
            e.printStackTrace();
        }
    }

    public String getEmail(String userType) {
        JSONObject user = (JSONObject) data.get(userType);
        return (String) user.get("email");
    }

    public String getPassword(String userType) {
        JSONObject user = (JSONObject) data.get(userType);
        return (String) user.get("password");
    }
}
