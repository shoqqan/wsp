package assessment;

import course.Course;

public class Assessment {
    private final Course course;
    private final double grade;
    private final double gpa;

    public Assessment(Course course, double grade, double gpa) {
        this.course = course;
        this.grade = grade;
        this.gpa = gpa;
    }

    public Course getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    public double getGpa() {
        return gpa;
    }
}
