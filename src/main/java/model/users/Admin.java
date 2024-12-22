package model.users;

import model.enums.UserRole;
// import java.util.Scanner;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
        this.setRole(UserRole.ADMIN);
    }

    public void createUser(String username, String password, String email, UserRole role) {
        // TODO: Ernur, implement it
        
    }

    public void deleteUser(String userId) {
        // TODO: Ernur, implement it
        
    }

    public void resetUserPassword(String userId) {
        // TODO: Ernur, implement it
        
    }

    public void viewAllUsers() {
        // TODO: Ernur, implement it
        
    }
}
