package christmas.domain;

public enum Benefit {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 100),
    WEEKDAY_DISCOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT("주말 할인", 2023),
    STARDAY_DISCOUNT("특별 할인", 1000),
    GIFT_EVENT("증정 이벤트", Menu.CHAMPAGNE.getPrice());

    private final String name;
    private final int discountAmount;

    Benefit(String name, int discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
    }

    public String getName() {
        return name;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
