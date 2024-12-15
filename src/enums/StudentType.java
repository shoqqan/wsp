package enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StudentType {
    BACHELOR("B"), 
    MASTER("M"), 
    DOCTORATE("D"); 

    private final String code;

    StudentType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static StudentType fromCode(String code) {
        for (StudentType type : StudentType.values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid StudentType code: " + code);
    }
}
