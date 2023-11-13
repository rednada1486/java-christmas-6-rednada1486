package christmas.service;

import static christmas.domain.Menu.*;
import static christmas.view.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void BeforeEach() {
        orderService = new OrderService();
    }

    @Test
    @DisplayName("validateOrderList() 메서드는 주문리스트가 정상인 경우 아무런 예외도 발생시키지 않는다.")
    void validateOrderListShouldNotThrowExceptionWhenOrderListIsNormal() {
        // given
        List<Order> orderList = new ArrayList<>();

        orderList.add(new Order(TAPAS, 1));
        orderList.add(new Order(ZERO_COLA, 2));

        // when, then
        assertThatCode(() -> orderService.validateOrderList(orderList))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("validateOrderList() 메서드는 주문리스트에 중복메뉴가 있는 경우 예외를 발생시킨다.")
    void validateOrderListShouldThrowIllegalArgumentExceptionWhenOrderListHasDuplicateMenu() {
        // given
        List<Order> orderList = new ArrayList<>();

        orderList.add(new Order(TAPAS, 1));
        orderList.add(new Order(TAPAS, 2));

        // when, then
        assertThatThrownBy(() -> orderService.validateOrderList(orderList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE.getErrorMessage());
    }
    
}