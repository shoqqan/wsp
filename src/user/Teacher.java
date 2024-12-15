package user;

import java.util.concurrent.atomic.AtomicInteger;
import enums.*;

public class Teacher extends Employee {
    private String subject;
    private TeacherRank rank;

    private static final AtomicInteger tutorCounter = new AtomicInteger(0);
    private static final AtomicInteger lecturerCounter = new AtomicInteger(0);
    private static final AtomicInteger seniorLecturerCounter = new AtomicInteger(0);
    private static final AtomicInteger professorCounter = new AtomicInteger(0);

    public Teacher(String username, String password, TeacherRank rank, String subject, int year) {
        super(username, password);
        this.subject = subject;
        this.rank = rank;

        int number;
        switch (rank) {
            case TUTOR:
                number = tutorCounter.incrementAndGet();
                break;
            case LECTURER:
                number = lecturerCounter.incrementAndGet();
                break;
            case SENIOR_LECTURER:
                number = seniorLecturerCounter.incrementAndGet();
                break;
            case PROFESSOR:
                number = professorCounter.incrementAndGet();
                break;
            default:
                throw new IllegalArgumentException("Invalid teacher rank: " + rank);
        }
        String formattedId = String.format("%02d%s%06d", year % 100, rank.getCode(), number);
        this.setId(formattedId);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public TeacherRank getRank() {
        return rank;
    }

    public void setRank(TeacherRank rank) {
        this.rank = rank;
    }
}
