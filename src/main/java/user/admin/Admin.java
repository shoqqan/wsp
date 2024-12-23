package user.admin;

import user.Role;
import user.User;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
        this.setRole(Role.ADMIN);
    }

    public void createUser(String username, String password, String email, Role role) {
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
