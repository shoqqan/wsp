package course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private final Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("id"),
                        rs.getString("title"),
                        Period.valueOf(rs.getString("period").toUpperCase()),

                        rs.getInt("year"),
                        rs.getInt("credits"),
                        rs.getString("teacher_id")
                ));
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void create(Course course) throws SQLException {
        String sql = "INSERT INTO courses (id, title, period, year, credits, teacher_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course.getId());
            stmt.setString(2, course.getTitle());
            stmt.setString(3, course.getPeriod().toString());
            stmt.setInt(4, course.getYear());
            stmt.setInt(5, course.getCredits());
            stmt.setString(6, course.getTeacherId());
            stmt.executeUpdate();
        }
    }

    public boolean registerStudent(String courseId, String studentId) throws SQLException {
        int enrolledCount = getEnrolledCount(courseId);
        int courseCapacity = getCourseCapacity(courseId);
        if (enrolledCount >= courseCapacity) {
            System.out.println("Курс переполнен. Регистрация невозможна.");
            return false;
        }
        String checkSql = "SELECT 1 FROM course_student WHERE course_id = ? AND student_id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setString(1, courseId);
            checkStmt.setString(2, studentId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("Студент уже зарегистрирован на курс.");
                return false;
            }
        }

        String insertSql = "INSERT INTO course_student (course_id, student_id, enrolled_at) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setString(1, courseId);
            insertStmt.setString(2, studentId);
            insertStmt.setObject(3, LocalDateTime.now());
            insertStmt.executeUpdate();
            return true;
        }
    }

    public int getEnrolledCount(String courseId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM course_student WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getCourseCapacity(String courseId) throws SQLException {
        String sql = "SELECT capacity FROM course_sessions WHERE course_id = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("capacity");
            }
        }
        return 0;
    }
}
