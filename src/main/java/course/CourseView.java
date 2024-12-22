package course;

import java.util.List;
import java.util.Scanner;

public class CourseView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayCourses(List<Course> courses) {
        System.out.println("=== Список курсов ===");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
