package enums;

public enum TeacherRank {
    TUTOR("T"), 
    LECTURER("L"), 
    SENIOR_LECTURER("S"), 
    PROFESSOR("P"); 

    private final String code;

    TeacherRank(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    
    public static TeacherRank fromCode(String code) {
        for (TeacherRank rank : TeacherRank.values()) {
            if (rank.getCode().equalsIgnoreCase(code)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Invalid TeacherRank code: " + code);
    }
}
