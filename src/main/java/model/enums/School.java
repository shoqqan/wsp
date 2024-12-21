package model.enums;

public enum School {
    SITE("School of Information Technology and Engineering"),
    SNSS("School of Natural and Social Sciences"),
    BS("Business School"),
    KMA("Kazakhstan Maritime Academy"),
    SAM("School of Applied Mathematics"),
    SG("School of Geology"),
    SEOGI("School of Energy and Oil & Gas Industry"),
    ISE("Institute of Science and Education"),
    SCE("School of Civil Engineering"),
    SMSGT("School of Mining and Smart Geotechnologies");

    private final String description;

    School(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
