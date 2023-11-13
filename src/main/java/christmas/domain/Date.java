package christmas.domain;

public class Date {
    private final int month = 12;
    private final int day;

    public Date(int month, int date) {
        this.day = date;
    }

    public Date(String userInput) {
        validate(userInput);
        this.day = Integer.parseInt(userInput);
    }

    private void validate(String userInput) {

    }

    private boolean isIntegerNum(String userInput) {
        return false;
    }

    private boolean isInCorrectRange() {
        return false;
    }

    private boolean isWeekend() {
        return true;
    }

    private boolean isStarDay() {
        return true;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return month +
                "월 " + day +
                "일";
    }
}
