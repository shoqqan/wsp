package course;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        Period.valueOf(rs.getString("period")),
                        rs.getInt("year"),
                        rs.getInt("credits"),
                        rs.getInt("teacher_id")
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
            stmt.setInt(6, course.getTeacherId());
            stmt.executeUpdate();
        }
    }
}
