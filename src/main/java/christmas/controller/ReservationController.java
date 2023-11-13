package christmas.controller;

import static christmas.view.InputView.*;
import static christmas.view.OutputView.*;

import christmas.domain.Order;
import christmas.service.OrderService;
import christmas.service.PaymentService;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    public ReservationController() {
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
    }

    public void play() {
        List<Order> orderList = receiveOrder();
        timeSleep(1500);
        int originalPaymentAmount = calculateAndShowOriginalPaymentAmount(orderList);
    }

    public List<Order> receiveOrder() {
        String userInput = readOrderList();

        List<Order> orderList = userInputToOrderList(userInput);

        orderService.validateOrderList(orderList);

        timeSleep(1000);
        printOrderList(orderList);

        return orderList;
    }

    public int calculateAndShowOriginalPaymentAmount(List<Order> orderList) {
        int originalPaymentAmount = paymentService.calculateOriginalPaymentAmount(orderList);
        printOriginalPaymentAmount(originalPaymentAmount);
        return originalPaymentAmount;
    }

    private List<Order> userInputToOrderList(String userInput) {
        List<Order> orderList = new ArrayList<>();

        String[] menuHyphenCountArr = userInput
                .replace(" ", "")
                .split(",");

        for (String menuHyphenCount : menuHyphenCountArr) {
            orderList.add(new Order(menuHyphenCount));
        }

        return orderList;
    }

    public void timeSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
