package christmas.domain;

import static christmas.view.ErrorMessage.*;

import christmas.utils.CalendarUtil;

import java.util.List;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String userInput) {
        this.year = 2023;
        this.month = 12;

        validate(userInput);
        this.day = Integer.parseInt(userInput);
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

    private void validate(String userInput) {
        if (!isIntegerNum(userInput))
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE.getErrorMessage());

        int tempDay = Integer.parseInt(userInput);

        if (!checkDayInRange(tempDay)) {
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

    public boolean isWeekend() {
        return CalendarUtil.isFriday(year, month, day) || CalendarUtil.isSaturday(year, month, day);
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isStarDay() {
        List<Integer> statDayList = List.of(25);
        return CalendarUtil.isSunday(year, month, day) || statDayList.contains(day);
    }

    @Override
    public String toString() {
        return month +
                "월 " + day +
                "일";
    }
}
