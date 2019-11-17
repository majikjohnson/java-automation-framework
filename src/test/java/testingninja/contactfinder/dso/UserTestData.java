package testingninja.contactfinder.dso;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testingninja.framework.utils.JsonDataLoader;

import java.io.IOException;

public class UserTestData {
    private JSONObject data;

    public UserTestData(String resourceName) {
        String file;
        try {
        ClassLoader classLoader = getClass().getClassLoader();
        file = classLoader.getResource(resourceName).getFile();
        data = JsonDataLoader.loadJsonObject(file);
        }  catch (NullPointerException | IOException e) {
            System.err.println("Unable to load resource file: " + resourceName);
            e.printStackTrace();
        }
    }

    public String getEmail(int id) {
        JSONArray userArray = (JSONArray) data.get("users");
        JSONObject user = (JSONObject) userArray.get(id);
        return (String) user.get("email");
    }

    public String getPassword(int id) {
        JSONArray userArray = (JSONArray) data.get("users");
        JSONObject user = (JSONObject) userArray.get(id);
        return (String) user.get("password");
    }
}
