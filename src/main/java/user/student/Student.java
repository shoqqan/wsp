package user.student;

import user.Role;
import user.User;

public class Student extends User {
    private String studentID;
    private int yearStudy;
    private boolean isExpelled;
    private String school;

    public Student(String id, String username, String password, String email, String role, int yearStudy, boolean isExpelled, String school) {
        super(id, username, password, email, Role.valueOf(role));
        this.yearStudy = yearStudy;
        this.isExpelled = isExpelled;
        this.school = school;
    }

    public String getStudentId() {
        return getId();
    }

    public String getFirstName() {
        return getUsername(); // Пример: используем имя пользователя как firstName
    }

    public String getLastName() {
        return ""; // Здесь можно добавить логику для фамилии, если она отсутствует
    }

    public int getYearStudy() {
        return yearStudy;
    }

    public String getSchool() {
        return school;
    }
}
