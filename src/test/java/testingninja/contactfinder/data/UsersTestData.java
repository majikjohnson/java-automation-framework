package testingninja.contactfinder.data;

import org.json.simple.JSONObject;
import testingninja.framework.utils.Crypto;
import testingninja.framework.utils.JsonDataLoader;

import java.io.IOException;

public class UsersTestData {
    private JSONObject data;

    public UsersTestData(String resourceName) {
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

    public String getDecryptedPassword(String userType) {
        JSONObject user = (JSONObject) data.get(userType);
        String encryptedPassword = (String) user.get("password");
        String key = System.getenv("ENCRYPTION_KEY");
        return Crypto.decryptPassword(encryptedPassword, key);
    }
}
