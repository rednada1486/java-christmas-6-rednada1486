package christmas.utils;

import java.util.Calendar;

public class CalendarUtil {
    private static final int[] END_DAY_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] DAY_OF_WEEK = {"일", "월", "화", "수", "목", "금", "토"};

    public static String makeCalendar(int year, int month) {
        StringBuilder sb = new StringBuilder();
        int startDayOfWeek = getStartDayOfWeek(year, month);
        int endDay = getEndDayOfMonth(year, month);

        appendTitle(sb, year, month);
        appendDayOfWeek(sb);
        appendBlankBeforeStartDay(sb, startDayOfWeek);
        appendDaysOfMonth(sb, startDayOfWeek, endDay);

        return sb.toString();
    }

    private static void appendTitle(StringBuilder sb, int year, int month) {
        String title = "<" + year + "년 " + month + "월>";
        String blankSpace = "\t   ";
        sb.append(blankSpace).append(title).append(blankSpace).append("\n");
    }

    private static void appendDayOfWeek(StringBuilder sb) {
        for (String day : DAY_OF_WEEK) {
            sb.append(day).append("\t");
        }
        sb.append("\n");
    }

    private static void appendBlankBeforeStartDay(StringBuilder sb, int startDayOfWeek) {
        for (int i = 1; i < startDayOfWeek; i++) {
            sb.append("\t");
        }
    }

    private static void appendDaysOfMonth(StringBuilder sb, int startDayOfWeek, int endDay) {
        for (int i = 1, dayOfWeek = startDayOfWeek; i <= endDay; i++, dayOfWeek++) {
            if (dayOfWeek % 7 == 1 && i > 1) {
                sb.append("\n");
            }
            sb.append(String.format("%02d", i)).append("\t");
        }
    }

    private static int getStartDayOfWeek(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    private static int getEndDayOfMonth(int year, int month) {
        if (isLeapYear(year) && month == 2) {
            return 29;
        }
        return END_DAY_OF_MONTH[month - 1];
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isInCorrectRange(int year, int month, int day) {
        int endDay = getEndDayOfMonth(year, month);
        return day >= 1 && day <= endDay;
    }

    public static boolean isFriday(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    public static boolean isSaturday(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }

    public static boolean isSunday(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }
}