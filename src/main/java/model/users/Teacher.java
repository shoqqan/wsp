package model.users;

import model.enums.UserRole;

public class Teacher extends Employee {
    public Teacher(String username, String password) {
        super(username, password);
        this.role = UserRole.TEACHER;
    }
}
