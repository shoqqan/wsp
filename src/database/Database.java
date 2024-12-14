package database;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Database {
    private static Database instance;
    private HashMap database = new HashMap<>();
    private final File file = new File("data.json");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Database() {
        // private constructor to prevent instantiation
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
        database = objectMapper.readValue(file, database.getClass());
    }
    public void init() throws IOException {
        this.getFromJson();
    }
    public void save() throws IOException {
        this.setToJson();
    }
    public HashMap getHashMap() {
        return database;
    }
}
