package christmas.view;

import static christmas.view.OutputMessage.*;

import christmas.domain.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printIntroduction() {
        System.out.println(INTRODUCE_PROGRAM.getOutputMessage() + "\n");
    }

    public static void printBenefitGuide(Date date) {
        System.out.println(date.toString() + PREVIEW_THE_EVENT_BENEFIT_IN_THE_DAY.getOutputMessage() + "\n");
    }


    public static void printAllMenu() {
        System.out.println("\n" + MENU.getOutputMessage());
        for (Category category : Category.values()) {
            printAllMenuInCategory(category);
            if (category != Category.BEVERAGE) {
                System.out.println();
            }
        }
        System.out.println(DIVIDER_LINE.getOutputMessage());
    }

    private static void printAllMenuInCategory(Category category) {
        System.out.println(category.toString());

        String result = Arrays.stream(Menu.values())
                .filter(menu -> menu.getCategory() == category)
                .map(Menu::toString)
                .collect(Collectors.joining(", "));

        System.out.println(result);
    }

    public static void printEventBenefitPreview(Bill bill) {
        printOrderList(bill.getOrderList());
        printOriginalPaymentAmount(bill.getOriginalPaymentAmount());
        printGiftMenu(bill.getGiftMenuPrice());
        printBenefitDetails(bill.getBenefitDetails());
        printTotalBenefitAmount(bill.getTotalBenefitAmount());
        printDiscountedPaymentAmount(bill.getDiscountedPaymentAmount());
        printDecemberEventBadge(bill.getDecemberEventBadge());
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

        filteredOrderList.forEach(order -> System.out.println("- " + order));
    }

    public static void printOriginalPaymentAmount(int originalPaymentAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getOutputMessage());
        String formattedNumber = String.format("%,d", originalPaymentAmount);
        System.out.println(formattedNumber + "원");
    }

    private static void printGiftMenu(int giftMenuPrice) {
    }

    private static void printBenefitDetails(List<String> benefitDetails) {
    }

    private static void printTotalBenefitAmount(int totalBenefitAmount) {
    }

    private static void printDiscountedPaymentAmount(int discountedPaymentAmount) {
    }

    private static void printDecemberEventBadge(Badge decemberEventBadge) {
    }
}
