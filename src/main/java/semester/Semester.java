package semester;

import course.Course;
import java.util.List;

public class Semester {
    private String name;
    private List<Course> courses;

    public Semester(String name) {
        this.name = name;
    }

    public Semester(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
