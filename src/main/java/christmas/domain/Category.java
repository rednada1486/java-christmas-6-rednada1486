package christmas.domain;

public enum Category {
    APPETIZER("애피터이저"),
    MAIN_COURSE("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "<" + name + ">";
    }
}
