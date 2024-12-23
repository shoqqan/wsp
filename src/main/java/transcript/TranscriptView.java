package transcript;

import assessment.Assessment;
import course.Course;
import semester.Semester;
import user.student.Student;

import java.sql.SQLException;
import java.util.List;

public class TranscriptView {

    private final TranscriptService transcriptService;

    public TranscriptView(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    public void displayTranscript(String studentId) throws SQLException {
        Student student = transcriptService.getStudentById(studentId);
        if (student == null) {
            System.out.println("Студент с ID " + studentId + " не найден.");
            return;
        }

        System.out.println("\nТранскрипт студента: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("ID: " + student.getStudentId());
        System.out.println("Год обучения: " + student.getYearStudy());
        System.out.println("Школа: " + student.getSchool());

        List<Transcript> transcripts = transcriptService.getTranscriptsByStudentId(student.getStudentId());
        if (transcripts.isEmpty()) {
            System.out.println("Нет доступных данных о транскрипте.");
            return;
        }

        for (Transcript transcript : transcripts) {
            Semester semester = transcript.getSemester();
            System.out.println("\nСеместр: " + semester.getName());
            System.out.println("------------------------------------------------------------------------");
            System.out.printf("%-30s %-10s %-10s %-10s\n", "Курс", "Кредиты", "Оценка", "GPA");

            for (Assessment assessment : transcript.getAssessments()) {
                Course course = assessment.getCourse();
                System.out.printf(
                        "%-30s %-10d %-10.2f %-10.2f\n",
                        course.getTitle(),
                        course.getCredits(),
                        assessment.getGrade(),
                        assessment.getGpa()
                );
            }

            System.out.println("\nОбщий GPA за семестр: " + transcript.getSemesterGpa());
        }

        System.out.println("\n--- Конец транскрипта ---\n");
    }
}
