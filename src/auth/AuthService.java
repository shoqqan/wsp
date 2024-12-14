package auth;

import database.Database;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AuthService {
    private final Database db = Database.getInstance();
    private static AuthService instance;

    private AuthService() {
        // private constructor to prevent instantiation
    }
    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        if (Arrays.asList(db.getHashMap().get("users")).contains(username)) {
            return db.getHashMap().get(username).equals(password);
        }
        System.out.println(db.getHashMap().toString());
        return false;
    }
    public void register(String username, String password) {
        User newUser = new User(username, password);

        List<User> users = new ArrayList<>((List<User>) db.getHashMap().get("users"));

        users.add(newUser);
        db.getHashMap().put("users", users);
    }
}
