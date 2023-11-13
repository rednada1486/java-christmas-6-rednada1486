package christmas.domain;

public class Date {
    private final int month = 12;
    private final int date;

    public Date(int month, int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return month +
                "월 " + date +
                "일";
    }
}
