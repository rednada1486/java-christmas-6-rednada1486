package christmas.controller;

import static christmas.domain.Menu.*;
import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("receiveOrder 메서드는 유저 입력값을 변화하여 주문 목록을 반환한다.")
    void receiveOrderShouldReturnOrderList() {
        // given
        System.setIn(createUserInput("타파스-1,제로콜라-1"));

        // when
        List<Order> result = reservationController.receiveOrder();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getMenu()).isEqualTo(TAPAS);
        assertThat(result.get(0).getCount()).isEqualTo(1);
        assertThat(result.get(1).getMenu()).isEqualTo(ZERO_COLA);
        assertThat(result.get(1).getCount()).isEqualTo(1);
    }
}