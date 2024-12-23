package transcript;

import user.student.Student;
import user.student.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class TranscriptService {

    private final StudentRepository studentRepository;

    public TranscriptService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(String studentId) throws SQLException {
        return studentRepository.findById(studentId);
    }

    public List<Transcript> getTranscriptsByStudentId(String studentId) throws SQLException {
        return studentRepository.findTranscriptsByStudentId(studentId);
    }
}
