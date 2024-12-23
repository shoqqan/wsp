package transcript;

import student.Student;
import student.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class TranscriptService {

    private final StudentRepository studentRepository;

    public TranscriptService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(int studentId) throws SQLException {
        return studentRepository.findById(studentId);
    }

    public List<Transcript> getTranscriptsByStudentId(int studentId) throws SQLException {
        return studentRepository.findTranscriptsByStudentId(studentId);
    }
}