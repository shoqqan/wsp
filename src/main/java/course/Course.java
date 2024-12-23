package course;

public class Course {
    private final String id;
    private final String title;
    private final Period period;
    private final int year;
    private final int credits;
    private Integer teacherId;

    public Course(String id, String title, Period period, int year, int credits) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.year = year;
        this.credits = credits;
        this.teacherId = null;
    }

    public Course(String id, String title, Period period, int year, int credits, int teacherId) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.year = year;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    public Course(String courseTitle, int credits, double grade, double gpa, String id, String title, Period period, int year, int credits1) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.year = year;
        this.credits = credits1;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Period getPeriod() {
        return period;
    }


    
    public int getYear() {
        return year;
    }

    public int getCredits() {
        return credits;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

}
