package christmas.service;

import static christmas.domain.Menu.*;

import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class PaymentServiceTest {
    private PaymentService paymentService;

    @BeforeEach
    void beforeEach() {
        paymentService = new PaymentService();
    }

    @Test
    @DisplayName("주문리스트로부터 결제금액을 정확하게 계산한다.")
    void calculatePaymentAmountCorrectly() {
        // given
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(TAPAS, 1));
        orderList.add(new Order(ZERO_COLA, 1));

        // when
        int result = paymentService.calculateOriginalPaymentAmount(orderList);

        // then
        Assertions.assertThat(result).isEqualTo(8500);
    }
}