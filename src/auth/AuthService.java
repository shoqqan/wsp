package auth;

import database.Database;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthService {
    private final Database db = Database.getInstance();
    private static AuthService instance;

    private AuthService() {}

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        List<Map<String, Object>> users = (List<Map<String, Object>>) db.getHashMap().get("users");

        if (users == null) {
            return false;
        }

        for (Map<String, Object> userMap : users) {
            String storedUsername = (String) userMap.get("username");
            String storedPassword = (String) userMap.get("password");

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password) {
        List<Map<String, Object>> users = (List<Map<String, Object>>) db.getHashMap().get("users");

        if (users == null) {
            users = new ArrayList<>();
        }

        Map<String, Object> newUser = new java.util.LinkedHashMap<>();
        newUser.put("username", username);
        newUser.put("password", password);

        users.add(newUser);
        db.getHashMap().put("users", users);
    }
}