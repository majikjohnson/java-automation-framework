package testingninja.framework.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserTestData {
    private JSONObject data;

    public UserTestData(String filename) {
        try {
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            data = (JSONObject) jsonParser.parse(reader);
            reader.close();
        } catch (ParseException | IOException e) {
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
