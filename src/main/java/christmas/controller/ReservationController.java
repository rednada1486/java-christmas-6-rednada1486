package christmas.controller;

import static christmas.view.InputView.*;
import static christmas.view.OutputView.*;

import christmas.domain.Date;
import christmas.domain.Order;
import christmas.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private final OrderService orderService;

    public ReservationController() {
        this.orderService = new OrderService();
    }

    public void play() {
        printIntroduction();

        Date date = registerReservationDateUntilPass();

        List<Order> orderList = receiveOrderUntilPass();

        printBenefitGuide(date);

        System.out.println();
    }

    public Date registerReservationDate() {
        String userInput = readReservationDate();
        return new Date(userInput);
    }

    public Date registerReservationDateUntilPass() {
        return receiveInputUntilPass(this::registerReservationDate);
    }

    public List<Order> receiveOrder() {
        String userInput = readOrderList();

        List<Order> orderList = userInputToOrderList(userInput);

        orderService.validateOrderList(orderList);

        printOrderList(orderList);

        return orderList;
    }

    public List<Order> receiveOrderUntilPass() {
        return receiveInputUntilPass(this::receiveOrder);
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

    public <T> T receiveInputUntilPass(ExceptionSupplier<T> inputMethod) {
        T result = null;

        while (true) {
            try {
                result = inputMethod.get();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }
}
