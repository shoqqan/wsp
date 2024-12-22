package model.users;

import user.Role;

public class Teacher extends Employee {
    public Teacher(String username, String password) {
        super(username, password);
        this.role = Role.TEACHER;
    }
}
