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
        System.out.println();
    }

    public static void printGiftMenu(int giftMenuPrice) {
        System.out.println(GIFT_MENU.getOutputMessage());

        if (giftMenuPrice == 0) {
            System.out.println(NOTHING.getOutputMessage() + "\n");
            return;
        }

        System.out.println("샴페인 1개\n");

    }

    public static void printBenefitDetails(List<String> benefitDetails) {
        System.out.println(BENEFIT_DETAILS.getOutputMessage());

        if (benefitDetails.isEmpty()) {
            System.out.println(NOTHING.getOutputMessage() + "\n");
            return;
        }

        benefitDetails.forEach(System.out::println);
        System.out.println();
    }

    public static void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getOutputMessage());

        if (totalBenefitAmount > 0) {
            totalBenefitAmount = -totalBenefitAmount;
        }

        String formattedNumber = String.format("%,d", totalBenefitAmount);
        System.out.println(formattedNumber + "원");
        System.out.println();
    }

    public static void printDiscountedPaymentAmount(int discountedPaymentAmount) {
        System.out.println(DISCOUNTED_PAYMENT_AMOUNT.getOutputMessage());

        String formattedNumber = String.format("%,d", discountedPaymentAmount);
        System.out.println(formattedNumber + "원");
        System.out.println();
    }

    public static void printDecemberEventBadge(Badge decemberEventBadge) {
    }
}
