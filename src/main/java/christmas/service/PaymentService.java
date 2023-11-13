package christmas.service;

import christmas.domain.Order;

import java.util.List;

public class PaymentService {
    public int calculateOriginalPaymentAmount(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(Order::calculateSubTotal)
                .sum();
    }
}
