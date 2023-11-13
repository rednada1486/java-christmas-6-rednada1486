package christmas.view;

import static christmas.view.OutputMessage.*;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.Arrays;
import java.util.List;
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

    public static void printOrderList(List<Order> orderList) {
        System.out.println(ORDER_MENU.getOutputMessage());
        for (Category category : Category.values()) {
            printOrderInCategory(orderList, category);
            System.out.println();
        }
    }

    private static void printOrderInCategory(List<Order> orderList, Category category) {
        System.out.println("* " + category.getName());

        List<String> filteredOrderList = orderList.stream()
                .filter(order -> order.getMenu().getCategory().equals(category))
                .map(Order::toString)
                .toList();

        if (filteredOrderList.isEmpty()) {
            System.out.println(NOTHING.getOutputMessage());
            return;
        }

        filteredOrderList.forEach(System.out::println);
    }

    public static void printOriginalPaymentAmount(int originalPaymentAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getOutputMessage());
        String formattedNumber = String.format("%,d", originalPaymentAmount);
        System.out.println(formattedNumber+"원");
    }
}
