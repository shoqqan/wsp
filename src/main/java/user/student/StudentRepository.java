package user.student;

import course.Period;
import assessment.Assessment;
import transcript.Transcript;
import semester.Semester;
import course.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentRepository {
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public Student findById(String studentId) throws SQLException {
        String sql = "SELECT s.student_id, s.year_study, s.is_expelled, s.school, " +
                "u.first_name, u.last_name, u.login, u.password, u.email, u.role " + // Добавлено поле email
                "FROM students s " +
                "JOIN users u ON s.user_id = u.id " +
                "WHERE s.user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getString("student_id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getInt("year_study"),
                        rs.getBoolean("is_expelled"),
                        rs.getString("school")
                );
            }
        }
        return null;
    }



    public List<Transcript> findTranscriptsByStudentId(String studentId) throws SQLException {
        String sql = "SELECT a.id AS transcript_id, s.name AS semester_name, c.id AS course_id, c.title AS course_title, " +
                "c.period, c.year, c.credits, a.grade, a.gpa " +
                "FROM assessments a " +
                "JOIN semesters s ON a.semester_id = s.id " +
                "JOIN courses c ON a.course_id = c.id " +
                "WHERE a.student_id = ?";

        List<Transcript> transcripts = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String semesterName = rs.getString("semester_name");
                String courseId = rs.getString("course_id");
                String courseTitle = rs.getString("course_title");
                String periodStr = rs.getString("period");
                Period period = Period.valueOf(periodStr.toUpperCase());
                int year = rs.getInt("year");
                int credits = rs.getInt("credits");
                double grade = rs.getDouble("grade");
                double gpa = rs.getDouble("gpa");

                Course course = new Course(courseId, courseTitle, period, year, credits);
                Semester semester = new Semester(semesterName);
                Assessment assessment = new Assessment(course, grade, gpa);
                List<Assessment> assessments = Collections.singletonList(assessment);
                Transcript transcript = new Transcript(semester, assessments);

                transcripts.add(transcript);
            }
        }
        return transcripts;
    }


}
