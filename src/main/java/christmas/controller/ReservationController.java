package christmas.controller;

import static christmas.view.InputView.*;

import christmas.domain.Order;
import christmas.service.OrderService;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private final OrderService orderService;

    public ReservationController() {
        this.orderService = new OrderService();
    }

    public List<Order> receiveOrder() {
        String userInput = readOrderList();

        List<Order> orderList = userInputToOrderList(userInput);

        orderService.validateOrderList(orderList);

        return orderList;
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
}
