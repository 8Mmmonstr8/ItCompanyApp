package ua.hubanov.application.entity.enums;

public enum Role {
    DEVELOPER(Values.DEVELOPER),
    QA(Values.QA),
    BA(Values.BA),
    PM(Values.PM);

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String DEVELOPER = "DEVELOPER";
        public static final String QA = "QA";
        public static final String BA = "BA";
        public static final String PM = "PM";
    }
}
