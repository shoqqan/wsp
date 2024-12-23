package course;

import teacher.TeacherRepository;

import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAll();
    }

    public void createCourse(String id, String title, Period period, int year, int credits) throws SQLException {
        Course course = new Course(id, title, period, year, credits);
        courseRepository.create(course);
    }

    public void createCourse(String id, String title, Period period, int year, int credits, String teacherId) throws SQLException {
        Course course = new Course(id, title, period, year, credits, teacherId);
        courseRepository.create(course);
    }
    public boolean registerStudentToCourse(String courseId, String studentId) throws SQLException {
        return courseRepository.registerStudent(courseId, studentId);
    }

    public String getTeacherName(String teacherId) throws SQLException {
        return teacherRepository.getTeacherNameById(teacherId);
    }

    public int getEnrolledCount(String courseId) throws SQLException {
        return courseRepository.getEnrolledCount(courseId);
    }

    public int getCourseCapacity(String courseId) throws SQLException {
        return courseRepository.getCourseCapacity(courseId);
    }

}
