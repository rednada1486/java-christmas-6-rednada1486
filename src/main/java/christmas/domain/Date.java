package christmas.domain;

import static christmas.view.ErrorMessage.*;

import christmas.utils.CalendarUtil;

public class Date {
    private final int year = 2023;
    private final int month = 12;
    private final int day;

    public Date(int day) {
        this.day = day;
    }

    public Date(String userInput) {
        validate(userInput);
        this.day = Integer.parseInt(userInput);
    }

    private void validate(String userInput) {
        if (!isIntegerNum(userInput))
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE.getErrorMessage());

        int tempDay = Integer.parseInt(userInput);

        if (checkDayInRange(tempDay)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isIntegerNum(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkDayInRange(int userInput) {
        return CalendarUtil.isDayInCorrectRange(year, month, userInput);
    }

    private boolean isWeekend() {
        return true;
    }

    private boolean isStarDay() {
        return true;
    }

    public int getYear() {
        return year;
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
