package christmas.view;


import static christmas.domain.Menu.TAPAS;
import static christmas.domain.Menu.ZERO_COLA;
import static christmas.view.OutputView.*;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class OutputViewTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void beforeEach() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void afterEach() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("고객에게 메뉴판을 애피타이저, 메인, 디저트, 음료 순으로 출력한다.")
    void printAllMenuCorrectly() {
        // given, when
        printAllMenu();
        String result = outputStreamCaptor.toString();

        // then
        assertThat(result).containsSubsequence("<애피타이저>", "<메인>", "<디저트>", "<음료>");
    }

    @Test
    @DisplayName("주문 목록을 원하는 형식에 맞춰서 출력한다.")
    void printOrderList() {
        // given
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(ZERO_COLA, 1));
        orderList.add(new Order(TAPAS, 2));

        // when
        OutputView.printOrderList(orderList);
        String result = outputStreamCaptor.toString();

        // then
        assertThat(result).contains("<주문 메뉴>", "제로콜라 1개", "타파스 2개");
    }

    @Test
    @DisplayName("할인 전 총 결제 금액을 형식에 맞게 출력한다.")
    void printOriginalPaymentAmountCorrectly() {
        // given
        int totalPaymentAmount = 8500;

        // when
        printOriginalPaymentAmount(totalPaymentAmount);
        String result = outputStreamCaptor.toString();

        // then
        assertThat(result).contains("<할인 전 총주문 금액>", "8,500원");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("증정 메뉴를 정확하게 출력한다.")
    void printGiftMenuCorrectly(int giftMenuPrice, String expected) {
        // when
        printGiftMenu(giftMenuPrice);
        String result = outputStreamCaptor.toString();

        // then
        assertThat(result).containsSubsequence("<증정 메뉴>", expected);
    }

    static Stream<Arguments> printGiftMenuCorrectly() {
        return Stream.of(
                Arguments.of(0, "없음"),
                Arguments.of(25000, "샴페인 1개")
        );
    }

    @Test
    @DisplayName("혜택 내역이 없는 경우, 혜택 내역을 정확하게 출력한다.")
    void printBenefitDetailsCorrectlyWhenBenefitIsNothing() {
        // given
        List<String> benefitDetails = new ArrayList<>();

        // when
        printBenefitDetails(benefitDetails);
        String result = outputStreamCaptor.toString();

        // then
        assertThat(result).containsSubsequence("<혜택 내역>", "없음");
    }

    @Test
    @DisplayName("혜택 내역이 존재할 때, 혜택 내역을 정확하게 출력한다.")
    void printBenefitDetailsCorrectlyWhenBenefitIsExist() {
        List<String> benefitDetails = List.of(
                "크리스마스 디데이 할인: -1,200원",
                "평일 할인: -4,046원",
                "특별 할인: -1,000원",
                "증정 이벤트: -25,000원"
        );

        printBenefitDetails(benefitDetails);
        String result = outputStreamCaptor.toString();

        assertThat(result).contains("<혜택 내역>")
                .contains("크리스마스 디데이 할인: -1,200원")
                .contains("평일 할인: -4,046원")
                .contains("특별 할인: -1,000원")
                .contains("증정 이벤트: -25,000원");
    }
}