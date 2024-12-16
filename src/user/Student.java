package user;

import enums.StudentType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.concurrent.atomic.AtomicInteger;

public class Student extends User {
    private int studyYear;
    @JsonProperty("studentType")
    private StudentType studentType;

    private static final AtomicInteger bachelorCounter = new AtomicInteger(0);
    private static final AtomicInteger masterCounter = new AtomicInteger(0);
    private static final AtomicInteger doctorateCounter = new AtomicInteger(0);

    public Student(String username, String password, StudentType studentType, int studyYear, int admissionYear) {
        super(username, password);
        this.studyYear = studyYear;
        this.studentType = studentType;

        int number;
        switch (studentType) {
            case BACHELOR:
                number = bachelorCounter.incrementAndGet();
                break;
            case MASTER:
                number = masterCounter.incrementAndGet();
                break;
            case DOCTORATE:
                number = doctorateCounter.incrementAndGet();
                break;
            default:
                throw new IllegalArgumentException("Invalid StudentType: " + studentType);
        }
        String formattedId = String.format("%02d%s%06d", admissionYear % 100, studentType.getCode(), number);
        this.setId(formattedId);
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }
}
