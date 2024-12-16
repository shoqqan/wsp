package teacher;

import user.User;
import user.UserRole;

public class Teacher extends User {
    public Teacher(String username, String password) {
        super(username, password);
        this.role = UserRole.TEACHER;
    }
}
