package database;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class Database {
    private static Database instance;
    private HashMap<String, Object> database = new HashMap<>();
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
        if (file.length() == 0) {
            System.out.println("Файл data.json пустй");
            database = new HashMap<>();
            return;
        }
        database = objectMapper.readValue(file, new TypeReference<HashMap<String, Object>>() {
        });
    }

    public void init() throws IOException {
        if (!file.exists()) {
            System.out.println("Файла data.json нет, создан новый data.json");
            file.createNewFile();
            database = new HashMap<>();
            return;
        }
        this.getFromJson();
    }

    public void save() throws IOException {
        this.setToJson();
    }

    public HashMap<String, Object> getHashMap() {
        return database;
    }
}
