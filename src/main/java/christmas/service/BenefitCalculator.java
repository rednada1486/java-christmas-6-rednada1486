package christmas.service;

import static christmas.domain.Benefit.*;

import christmas.domain.Benefit;
import christmas.domain.Date;
import christmas.domain.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitCalculator {
    private Date date;
    private List<Order> orderList;
    private Map<Benefit, Integer> appliedBenefit;
    private int originalPaymentAmount;


    public Map<Benefit, Integer> makeAppliedBenefit(Date date, List<Order> orderList) {
        init(date, orderList);

        originalPaymentAmount = calculateOriginalPaymentAmount(orderList);

        if (!checkDate(date) || originalPaymentAmount < 10000) {
            return appliedBenefit;
        }

        calculateAllBenefit();

        return appliedBenefit;
    }

    private void calculateAllBenefit() {
        appliedBenefit.put(CHRISTMAS_D_DAY_DISCOUNT, calculateChristmasDDayDiscountAmount());
        appliedBenefit.put(WEEKDAY_DISCOUNT, calculateWeekdayDiscountAmount());
        appliedBenefit.put(WEEKEND_DISCOUNT, calculateWeekendDiscountAmount());
        appliedBenefit.put(STARDAY_DISCOUNT, calculateStardayDiscountAmount());
        appliedBenefit.put(GIFT_EVENT, calculateGiftEventAmount());
    }

    private boolean checkDate(Date date) {
        return date.getYear() == 2023 && date.getMonth() == 12;
    }

    private void init(Date date, List<Order> orderList) {
        this.date = date;
        this.orderList = orderList;

        appliedBenefit = new HashMap<>();

        for (Benefit benefit : Benefit.values()) {
            appliedBenefit.put(benefit, 0);
        }

        originalPaymentAmount = 0;
    }

    private int calculateChristmasDDayDiscountAmount() {
        return 0;
    }

    private int calculateWeekdayDiscountAmount() {
        return 0;
    }

    private int calculateWeekendDiscountAmount() {
        return 0;
    }

    private int calculateStardayDiscountAmount() {
        return 0;
    }

    private int calculateGiftEventAmount() {
        return 0;
    }


    public int calculateOriginalPaymentAmount(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(Order::calculateSubTotal)
                .sum();
    }
}