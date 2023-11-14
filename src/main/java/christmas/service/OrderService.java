package christmas.service;

import static christmas.domain.Category.BEVERAGE;
import static christmas.view.ErrorMessage.INVALID_ORDER_MESSAGE;

import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderService {
    public void validateOrderList(List<Order> orderList) {
        if (isDuplicateMenu(orderList) || isTotalCountCanNotPossible(orderList) || isOnlyBeverage(orderList)) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE.getErrorMessage());
        }
    }

    private boolean isDuplicateMenu(List<Order> orderList) {
        Set<Menu> menuSet = new HashSet<>();

        for (Order order : orderList) {
            menuSet.add(order.getMenu());
        }

        return orderList.size() != menuSet.size();
    }

    private boolean isTotalCountCanNotPossible(List<Order> orderList) {
        int totalCount = orderList.stream().mapToInt(Order::getCount).sum();

        return totalCount > 20;
    }

    private boolean isOnlyBeverage(List<Order> orderList) {
        long beverageCount =
                orderList.stream()
                        .filter(order -> order.getMenu().getCategory().equals(BEVERAGE))
                        .count();

        return orderList.size() == beverageCount;
    }
}
