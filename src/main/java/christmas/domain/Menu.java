package christmas.domain;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.Category.*;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MAIN_COURSE),
    BARBECUE_RIBS("바비큐립", 54000, MAIN_COURSE),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN_COURSE),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN_COURSE),
    CHOCOLATE_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),
    ZERO_COLA("제로콜라", 3000, BEVERAGE),
    RED_WINE("레드와인", 60000, BEVERAGE),
    CHAMPAGNE("샴페인", 25000, BEVERAGE);

    private final String name;
    private final int price;
    private final Category category;

    private static final Map<String, Menu> BY_NAME = new HashMap<>();

    static {
        for (Menu menu : values()) {
            BY_NAME.put(menu.name, menu);
        }
    }

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + '(' + price + ')';
    }

    public static Menu findByName(String name) {
        return BY_NAME.get(name);
    }
}
