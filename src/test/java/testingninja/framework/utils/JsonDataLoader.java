package testingninja.framework.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataLoader {

    public static JSONObject loadJsonObject(String filename) throws IOException {
        JSONObject data;
        try {
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            data = (JSONObject) jsonParser.parse(reader);
            reader.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
            throw new IOException("Unable to load JSON file: " + filename);
        }
        return data;
    }
}
