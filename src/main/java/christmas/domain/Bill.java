package christmas.domain;

import christmas.service.BenefitCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bill {
    private final List<Order> orderList;
    private final int originalPaymentAmount;
    private final List<String> benefitDetails;

    public Bill(Date date, List<Order> orderList) {
        this.orderList = orderList;
        originalPaymentAmount = calculateOriginalPaymentAmount(orderList);

        BenefitCalculator benefitCalculator = new BenefitCalculator();
        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        benefitDetails = makeBenefitDetails(appliedBenefit);
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public int getOriginalPaymentAmount() {
        return originalPaymentAmount;
    }

    public List<String> getBenefitDetails() {
        return benefitDetails;
    }
}
