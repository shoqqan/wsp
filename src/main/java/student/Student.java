package student;

import user.User;

import user.Role;

public class Student extends User {
    private int studentID;
    private int yearStudy;
    private boolean isExpelled;
    private String school;

    public Student(int id, String username, String password, String email, String role, int yearStudy, boolean isExpelled, String school) {
        super(id, username, password, email, Role.valueOf(role));
        this.yearStudy = yearStudy;
        this.isExpelled = isExpelled;
        this.school = school;
    }

    public int getStudentId() {
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
