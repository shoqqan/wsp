package course;

import java.sql.SQLException;
import java.util.List;

public class CourseController {
    private final CourseView courseView;
    private final CourseService courseService;

    public CourseController(CourseView courseView, CourseService courseService) {
        this.courseView = courseView;
        this.courseService = courseService;
    }

    public void displayCourses() {
        List<Course> courses = courseService.getAllCourses();
        courseView.displayCourses(courses, courseService);
    }

    public void handleStudentRegistration(String studentId) {
        String courseId = courseView.getCourseIdInput();
        try {
            boolean success = courseService.registerStudentToCourse(courseId, studentId);
            if (success) {
                courseView.showSuccess("Студент успешно зарегистрирован на курс!");
            } else {
                courseView.showError("Регистрация на курс не удалась.");
            }
        } catch (SQLException e) {
            courseView.showError("Ошибка: " + e.getMessage());
        }
    }
}
