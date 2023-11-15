package christmas.domain;

import christmas.service.BenefitCalculator;

import java.util.List;

public class Bill {
    private final BenefitCalculator benefitCalculator = new BenefitCalculator();

    private final List<Order> orderList;


    public Bill(Date date, List<Order> orderList) {
        this.orderList = orderList;
    }

    private int calculateOriginalPaymentAmount(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(Order::calculateSubTotal)
                .sum();
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
