package course;


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
        courseView.displayCourses(courses);
    }
}
