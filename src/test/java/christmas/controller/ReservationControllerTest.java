package christmas.controller;

import static christmas.domain.Menu.*;
import static christmas.view.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

class ReservationControllerTest {
    private ReservationController reservationController;

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @BeforeEach
    public void beforeEach() {
        reservationController = new ReservationController();
    }

    @AfterEach
    public void afterEach() {
        Console.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-1"})
    @DisplayName("receiveOrder 메서드는 유저 입력값을 변환하여 주문 목록을 반환한다.")
    void receiveOrderShouldReturnOrderList(String input) {
        // given
        System.setIn(createUserInput(input));

        // when
        List<Order> result = reservationController.receiveOrder();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getMenu()).isEqualTo(TAPAS);
        assertThat(result.get(0).getCount()).isEqualTo(1);
        assertThat(result.get(1).getMenu()).isEqualTo(ZERO_COLA);
        assertThat(result.get(1).getCount()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스->1", "없는메뉴-1", "타파스-0", "타파스-1,타파스-1", "타파스-20,제로콜라-1", "제로콜라-1"})
    @DisplayName("receiveOrder 메서드는 유저가 잘못된 값을 입력할 경우 예외를 발생시킨다.")
    void receiveOrderShouldThrowIllegalArgumentExceptionWhenWrongValueInputted(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> reservationController.receiveOrder())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE.getErrorMessage());
    }

    @Test
    @DisplayName("receiveOrderUntilPass 메서드는 사용자가 잘못된 주문을 한 경우 다시 입력을 받는다.")
    void receiveOrderUntilPass() {
        // given
        System.setIn(createUserInput("메뉴-1\n타파스-1"));

        // when
        List<Order> orderList = reservationController.receiveOrderUntilPass();

        // then
        assertThat(orderList).hasSize(1);
        assertThat(orderList.get(0).getMenu()).isEqualTo(TAPAS);
        assertThat(orderList.get(0).getCount()).isEqualTo(1);
    }
}