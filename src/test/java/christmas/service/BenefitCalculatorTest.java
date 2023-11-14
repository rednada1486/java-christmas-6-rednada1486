package christmas.service;

import static christmas.domain.Benefit.*;
import static org.assertj.core.api.Assertions.*;

import christmas.domain.Benefit;
import christmas.domain.Date;
import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class BenefitCalculatorTest {
    private BenefitCalculator benefitCalculator;

    @BeforeEach
    void BeforeEach() {
        benefitCalculator = new BenefitCalculator();
    }

    @Test
    @DisplayName("12월 3일 기준, BenefitCalculator가 크리스마스 디데이 할인을 잘 계산하는 지 확인한다")
    void calculateChristmasDDayDiscountAmountCorrectlyWhenDayIsDecember3rd() {
        // given
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("3");

        // when
        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(1200);
    }

    @Test
    @DisplayName("12월 25일 기준, BenefitCalculator가 크리스마스 디데이 할인을 잘 계산하는 지 확인한다")
    void calculateChristmasDDayDiscountAmountCorrectlyWhenDayIsDecember24th() {
        // given
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("25");

        // when
        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(3400);
    }

    @ParameterizedTest
    @ValueSource(strings = {"26", "27", "28", "29", "30", "31"})
    @DisplayName("12월 25일이 지났을 때, BenefitCalculator가 크리스마스 디데이 할인을 잘 계산하는 지 확인한다")
    void calculateChristmasDDayDiscountAmountShouldReturnZeroWhenDayAfterDecember25th() {
        // given
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("25");

        // when
        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(3400);
    }

    @Test
    @DisplayName("평일이 아닌 경우 평일 할인이 적용되지 않는다.")
    void calculateWeekdayDiscountAmountShouldReturnZeroWhenDayIsNotWeekday() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("2"); // 주말

        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(WEEKDAY_DISCOUNT)).isEqualTo(0);
    }

    @Test
    @DisplayName("평일이고, 디저트 종류가 한가지 일 때, 평일 할인 금액을 정확하게 계산한다.")
    void calculateWeekdayDiscountAmountCorrectlyWhenDayIsWeekdayAndDesertKindIsOne() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("3"); // 평일

        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(WEEKDAY_DISCOUNT)).isEqualTo(4046);
    }

    @Test
    @DisplayName("평일이고, 디저트 종류가 두가지 일 때, 평일 할인 금액을 정확하게 계산한다.")
    void calculateWeekdayDiscountAmountCorrectlyWhenDayIsWeekdayAndDesertKindIsTwo() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("아이스크림-1"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("3"); // 평일

        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(WEEKDAY_DISCOUNT)).isEqualTo(6069);
    }

    @Test
    @DisplayName("주말이 아닌 경우 주말 할인이 적용되지 않는다.")
    void calculateWeekendDiscountAmountShouldReturnZeroWhenDayIsNotWeekend() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("3"); // 평일

        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(WEEKEND_DISCOUNT)).isEqualTo(0);
    }

    @Test
    @DisplayName("주말이고 메인코스의 종류가 한가지 일 때, 주말 할인 금액을 정확하게 계산한다.")
    void calculateWeekendDiscountAmountCorrectlyWhenDayIsWeekendAndMainKindIsOne() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("2"); // 주말

        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(WEEKEND_DISCOUNT)).isEqualTo(2023);
    }

    @Test
    @DisplayName("주말이고 메인코스의 종류가 두가지 일 때, 주말 할인 금액을 정확하게 계산한다.")
    void calculateWeekendDiscountAmountCorrectlyWhenDayIsWeekendAndMainKindIsTwo() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("티본스테이크-1"));
        orderList.add(new Order("바비큐립-2"));
        orderList.add(new Order("초코케이크-2"));
        orderList.add(new Order("제로콜라-1"));

        Date date = new Date("2"); // 주말

        Map<Benefit, Integer> appliedBenefit = benefitCalculator.makeAppliedBenefit(date, orderList);

        assertThat(appliedBenefit.get(WEEKEND_DISCOUNT)).isEqualTo(6069);
    }
}