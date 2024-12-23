package user;


import user.admin.Admin;
import user.student.Student;
import user.teacher.Teacher;

public class UserFactory {
    public User createUser(Role role) {
        User user = null;
        switch (role) {
            case ADMIN:
                user = new Admin();
                break;
            case TEACHER:
                user = new Teacher();
                break;
            case STUDENT:
                user = new Student();
                break;
        }
    }
}
