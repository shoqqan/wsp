package course;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CourseView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayCourses(List<Course> courses, CourseService courseService) {
        System.out.println("=== Список курсов ===");
        for (Course course : courses) {
            try {
                String teacherName = courseService.getTeacherName(course.getTeacherId().toString());
                int enrolled = courseService.getEnrolledCount(course.getId());
                int capacity = courseService.getCourseCapacity(course.getId());
                System.out.printf(
                        "ID: %s | Название: %s | Преподаватель: %s | Заполнено: (%d/%d)\n",
                        course.getId(), course.getTitle(), teacherName, enrolled, capacity
                );
            } catch (SQLException e) {
                System.out.println("Ошибка получения данных о курсе: " + course.getId());
            }
        }
    }


    public String getCourseIdInput() {
        System.out.print("Введите ID курса, на который вы хотите зарегистрироваться: ");
        return scanner.nextLine();
    }

    public void showSuccess(String message) {
        System.out.println("Успех: " + message);
    }

    public void showError(String message) {
        System.out.println("Ошибка: " + message);
    }
}
