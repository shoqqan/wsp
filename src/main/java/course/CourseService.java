package course;

import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAll();
    }

    public void createCourse(String id, String title, Period period, int year, int credits) throws SQLException {
        Course course = new Course(id, title, period, year, credits);
        courseRepository.create(course);
    }

    public void createCourse(String id, String title, Period period, int year, int credits, Integer teacherId) throws SQLException {
        Course course = new Course(id, title, period, year, credits, teacherId);
        courseRepository.create(course);
    }
}
