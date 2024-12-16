package database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import user.User;

public class Database {
    private static Database instance;
    private HashMap<String, Object> database = new HashMap<>();
    private final File file = new File("data.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Database() {

    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void setToJson() throws IOException {
        objectMapper.writeValue(file, database);
    }

    private void getFromJson() throws IOException {
        if (!file.exists() || file.length() == 0) {
            database = new HashMap<>();
            database.put("users", new ArrayList<User>());
            return;
        }

        database = objectMapper.readValue(file, new TypeReference<HashMap<String, Object>>() {
        });

        if (database.containsKey("users")) {
            List<User> users = objectMapper.convertValue(
                    database.get("users"),
                    new TypeReference<List<User>>() {
                    });
            database.put("users", users);
        }
    }

    public void init() throws IOException {
        this.getFromJson();
    }

    public void save() throws IOException {
        this.setToJson();
    }

    public HashMap<String, Object> getHashMap() {
        return database;
    }

}
