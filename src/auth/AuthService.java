package auth;

import database.Database;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final Database db = Database.getInstance();
    private static AuthService instance;

    private AuthService() {

    }

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        List<User> users = (List<User>) db.getHashMap().get("users");
        if (users == null) {
            return false;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password) {
        User newUser = new User(username, password);

        List<User> users = (List<User>) db.getHashMap().get("users");
        if (users == null) {
            users = new ArrayList<>();
        }

        users.add(newUser);
        db.getHashMap().put("users", users);
    }
}
