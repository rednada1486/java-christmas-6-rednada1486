package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class BillTest {
    List<Order> orderList;
    Date date;

    @Test
    @DisplayName("예제에 나온 상황1로 테스트 했을 때, 할인 전 총 주문 금액을 정확하게 계산한다.")
    void calculateOriginalPaymentAmountCorrectlyWhenTestCase1() {
        // given
        generateTestCase1();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getOriginalPaymentAmount()).isEqualTo(8500);
    }

    @Test
    @DisplayName("예제에 나온 상황2로 테스트 했을 때, 할인 전 총 주문 금액을 정확하게 계산한다.")
    void calculateOriginalPaymentAmountCorrectlyWhenTestCase2() {
        // given
        generateTestCase2();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getOriginalPaymentAmount()).isEqualTo(142000);
    }

    @Test
    @DisplayName("예제에 나온 상황1로 테스트 했을 때, 총 혜택 내역을 담은 List를 정확하게 반환한다.")
    void makeBenefitDetailsCorrectlyWhenTestCase1() {
        // given
        generateTestCase1();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getBenefitDetails())
                .hasSize(0);
    }

    @Test
    @DisplayName("예제에 나온 상황2로 테스트 했을 때, 총 혜택 내역을 담은 List를 정확하게 반환한다.")
    void makeBenefitDetailsCorrectlyWhenTestCase2() {
        // when
        generateTestCase2();

        // given
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getBenefitDetails())
                .hasSize(4)
                .containsSubsequence(
                        "크리스마스 디데이 할인: -1,200원",
                        "평일 할인: -4,046원",
                        "특별 할인: -1,000원",
                        "증정 이벤트: -25,000원"
                );
    }

    @Test
    @DisplayName("예제에 나온 상황1로 테스트 했을 때, 총 혜택 금액을 정확하게 계산한다.")
    void calculateTotalBenefitAmountCorrectlyWhenTestCase1() {
        // given
        generateTestCase1();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getTotalBenefitAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("예제에 나온 상황2로 테스트 했을 때, 총 혜택 금액을 정확하게 계산한다.")
    void calculateTotalBenefitAmountCorrectlyWhenTestCase2() {
        // given
        generateTestCase2();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getTotalBenefitAmount()).isEqualTo(31246);
    }

    @Test
    @DisplayName("예제에 나온 상황1로 테스트 했을 때, 증정 메뉴 금액을 정확하게 계산한다.")
    void calculateGiftMenuPriceCorrectlyWhenTestCase1() {
        // given
        generateTestCase1();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getGiftMenuPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("예제에 나온 상황2로 테스트 했을 때, 증정 메뉴 금액을 정확하게 계산한다.")
    void calculateGiftMenuPriceCorrectlyWhenTestCase2() {
        // given
        generateTestCase2();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getGiftMenuPrice()).isEqualTo(25000);
    }

    @Test
    @DisplayName("예제에 나온 상황1로 테스트 했을 때, 할인 후 예상 결제 금액을 정확하게 계산한다.")
    void calculateDiscountedPaymentAmountCorrectlyWhenTestCase1() {
        // given
        generateTestCase1();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getDiscountedPaymentAmount()).isEqualTo(8500);
    }

    @Test
    @DisplayName("예제에 나온 상황2로 테스트 했을 때, 할인 후 예상 결제 금액을 정확하게 계산한다.")
    void calculateDiscountedPaymentAmountCorrectlyWhenTestCase2() {
        // given
        generateTestCase2();

        // when
        Bill bill = new Bill(date, orderList);

        // then
        assertThat(bill.getDiscountedPaymentAmount()).isEqualTo(135754);
    }


    void generateTestCase1() {
        // 메뉴 : 타파스-1,제로콜라-1
        // 날짜 : 12월 26일
        orderList = new ArrayList<>();
        orderList.add(new Order("타파스-1"));
        orderList.add(new Order("제로콜라-1"));

        date = new Date("26");
    }

    void generateTestCase2() {
        // 메뉴 : 티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
        // 날짜 : 12월 3일
        orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        date = new Date("3");
    }
}