package christmas.domain;

import christmas.service.BenefitCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static christmas.domain.Badge.*;

public class Bill {
    private final List<Order> orderList;
    private final int originalPaymentAmount;
    private final int giftMenuPrice;
    private final List<String> benefitDetails;
    private final int totalBenefitAmount;
    private final int discountedPaymentAmount;
    private final Badge decemberEventBadge;

    public Bill(Date date, List<Order> orderList) {
        this.orderList = orderList;
        originalPaymentAmount = calculateOriginalPaymentAmount(orderList);

        BenefitCalculator benefitCalculator = new BenefitCalculator();
        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        giftMenuPrice = calculateGiftMenuPrice(appliedBenefit);
        benefitDetails = makeBenefitDetails(appliedBenefit);
        totalBenefitAmount = calculateTotalBenefitAmount(appliedBenefit);

        discountedPaymentAmount = originalPaymentAmount - totalBenefitAmount + giftMenuPrice;

        decemberEventBadge = calculateDecemberEventBadge(totalBenefitAmount);
    }

    private int calculateOriginalPaymentAmount(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(Order::calculateSubTotal)
                .sum();
    }

    private List<String> makeBenefitDetails(Map<Benefit, Integer> appliedBenefit) {
        List<String> benefitDetails = new ArrayList<>();

        for (Benefit benefit : Benefit.values()) {
            String benefitName = benefit.getName();
            int amount = appliedBenefit.get(benefit);
            if (amount == 0) {
                continue;
            }
            String formattedNumber = String.format("%,d", amount);
            benefitDetails.add(benefitName + ": -" + formattedNumber + "원");
        }

        return benefitDetails;
    }

    private int calculateTotalBenefitAmount(Map<Benefit, Integer> appliedBenefit) {
        return appliedBenefit.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int calculateGiftMenuPrice(Map<Benefit, Integer> appliedBenefit) {
        if (appliedBenefit.get(Benefit.GIFT_EVENT) == 0) {
            return 0;
        }
        return Menu.CHAMPAGNE.getPrice();
    }

    private Badge calculateDecemberEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return SANTA;
        }

        if (totalBenefitAmount >= 15000) {
            return TREE;
        }

        if (totalBenefitAmount >= 10000) {
            return STAR;
        }

        return NOTHING;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public int getOriginalPaymentAmount() {
        return originalPaymentAmount;
    }

    public int getGiftMenuPrice() {
        return giftMenuPrice;
    }

    public List<String> getBenefitDetails() {
        return benefitDetails;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getDiscountedPaymentAmount() {
        return discountedPaymentAmount;
    }

    public Badge getDecemberEventBadge() {
        return decemberEventBadge;
    }
}
