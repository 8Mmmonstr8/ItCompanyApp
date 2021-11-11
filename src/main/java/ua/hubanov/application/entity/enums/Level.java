package ua.hubanov.application.entity.enums;

public enum Level {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int value;

    private Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Level of(int value) {
        return Level.values()[value-1];
    }
}
