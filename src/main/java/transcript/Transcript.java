package transcript;

import assessment.Assessment;
import semester.Semester;

import java.util.List;

public class Transcript {
    private Semester semester;
    private List<Assessment> assessments;

    public Transcript(Semester semester, List<Assessment> assessments) {
        this.semester = semester;
        this.assessments = assessments;
    }

    public Semester getSemester() {
        return semester;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public double getSemesterGpa() {
        double totalGpa = 0;
        int totalCredits = 0;
        for (Assessment assessment : assessments) {
            totalGpa += assessment.getGpa() * assessment.getCourse().getCredits();
            totalCredits += assessment.getCourse().getCredits();
        }
        return totalCredits > 0 ? totalGpa / totalCredits : 0.0;
    }
}