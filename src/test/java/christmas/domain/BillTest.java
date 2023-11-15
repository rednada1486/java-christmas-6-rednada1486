package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BillTest {
    List<Order> orderList;
    Date date;

    @BeforeEach
    void beforeEach() {
        // 메뉴 : 티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
        // 날짜 : 12월 3일
        orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        date = new Date("3");
    }

    @Test
    @DisplayName("예제에 나온 상황으로 테스트 했을 때, 할인 전 총 주문 금액을 정확하게 계산한다.")
    void calculateOriginalPaymentAmountCorrectly() {
        // given, then
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getOriginalPaymentAmount()).isEqualTo(142000);
    }
}