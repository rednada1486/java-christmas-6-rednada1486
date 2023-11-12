package christmas.view;

import christmas.domain.Category;
import christmas.domain.Menu;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {

    public static void printAllMenu() {
        for (Category category : Category.values()) {
            printAllMenuInCategory(category);
        }
    }

    private static void printAllMenuInCategory(Category category) {
        System.out.println(category.toString());

        String result = Arrays.stream(Menu.values())
                .filter(menu -> menu.getCategory() == category)
                .map(Menu::toString)
                .collect(Collectors.joining(", "));

        System.out.println(result);
        System.out.println();
    }
}
