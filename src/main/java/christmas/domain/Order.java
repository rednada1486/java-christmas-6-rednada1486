package christmas.domain;

import static christmas.view.ErrorMessage.INVALID_ORDER_MESSAGE;

import java.util.regex.Pattern;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public Order(String menuHyphenCount) {
        validate(menuHyphenCount);

        String[] splitInput = menuHyphenCount.split("(?=-\\d+$)");

        this.menu = Menu.findByName(splitInput[0]);
        this.count = Integer.parseInt(splitInput[1].substring(1));
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    public int calculateSubTotal() {
        return menu.getPrice() * count;
    }

    private void validate(String menuHyphenCount) {
        if (!checkMenuHyphenCount(menuHyphenCount)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE.getErrorMessage());
        }

        String[] splitInput = menuHyphenCount.split("(?=-\\d+$)");

        if (!checkMenu(splitInput[0]) || !checkCount(splitInput[1].substring(1))) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE.getErrorMessage());
        }
    }

    private boolean checkMenuHyphenCount(String menuHyphenCount) {
        String pattern = "^.+-\\d+";
        return Pattern.matches(pattern, menuHyphenCount);
    }

    private boolean checkMenu(String menuName) {
        return Menu.findByName(menuName) != null;
    }

    private boolean checkCount(String countStr) {
        int count;

        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            return false;
        }

        return count >= 1;
    }

    @Override
    public String toString() {
        return menu.getName() + " " + count + '개';
    }
}
