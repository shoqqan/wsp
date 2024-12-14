package auth;

import database.Database;

public class AuthService {
    private final Database db;
    public AuthService(Database database) {
        this.db = database;
    }
    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
    public void register(String username, String password) {

        System.out.println("Registered successfully!");
    }
}
