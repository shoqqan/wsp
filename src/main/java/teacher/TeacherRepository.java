package teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRepository {
    private final Connection connection;

    public TeacherRepository(Connection connection) {
        this.connection = connection;
    }

    public String getTeacherNameById(String teacherId) throws SQLException {
        String sql = "SELECT u.first_name, u.last_name " +
                "FROM teachers t " +
                "JOIN users u ON t.user_id = u.id " +
                "WHERE t.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("first_name") + " " + rs.getString("last_name");
            }
        }
        return "Unknown Teacher";
    }

}
