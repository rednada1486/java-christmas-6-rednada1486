package christmas.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalendarUtilTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    @DisplayName("isDayInCorrectRange 메서드는 년, 월, 일을 입력했을 때 일이 가능한 범위에 있으면 true를 반환한다.")
    void isDayInCorrectRangeShouldReturnTrueWhenDayIsInCorrectRange(int day) {
        // when
        boolean result = CalendarUtil.isDayInCorrectRange(2023, 12, day);

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    @DisplayName("isDayInCorrectRange 메서드는 년, 월, 일을 입력했을 때 일이 가능한 범위에 있으면 true를 반환한다.")
    void isDayInCorrectRangeShouldReturnFalseWhenDayIsNotInCorrectRange(int day) {
        // when
        boolean result = CalendarUtil.isDayInCorrectRange(2023, 12, day);

        // then
        assertThat(result).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 8, 15, 22, 29})
    @DisplayName("isFriday 메서드는 해당 일자가 금요일이면 true를 반환한다.")
    void isFridayShouldReturnTrueWhenTheDayIsFriday(int day) {
        // when
        boolean result = CalendarUtil.isFriday(2023, 12, day);

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 7})
    @DisplayName("isSaturday 메서드는 해당 일자가 금요일이 아니면 false를 반환한다.")
    void isFridayShouldReturnFalseWhenTheDayIsNotFriday(int day) {
        // when
        boolean result = CalendarUtil.isFriday(2023, 12, day);

        // then
        assertThat(result).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 9, 16, 23, 30})
    @DisplayName("isSaturday 메서드는 해당 일자가 토요일이면 true를 반환한다.")
    void isSaturdayShouldReturnTrueWhenTheDayIsSaturday(int day) {
        // when
        boolean result = CalendarUtil.isSaturday(2023, 12, day);

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4, 5, 6, 7})
    @DisplayName("isSaturday 메서드는 해당 일자가 토요일이 아니면 false를 반환한다.")
    void isSaturdayShouldReturnFalseWhenTheDayIsNotSaturday(int day) {
        // when
        boolean result = CalendarUtil.isSaturday(2023, 12, day);

        // then
        assertThat(result).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31})
    @DisplayName("isSunday 메서드는 해당 일자가 일요일이면 true를 반환한다.")
    void isSundayShouldReturnTrueWhenTheDayIsSunday(int day) {
        // when
        boolean result = CalendarUtil.isSunday(2023, 12, day);

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7})
    @DisplayName("isSunday 메서드는 해당 일자가 일요일이 아니면 false를 반환한다.")
    void isSundayShouldReturnFalseWhenTheDayIsNotSunday(int day) {
        // when
        boolean result = CalendarUtil.isSunday(2023, 12, day);

        // then
        assertThat(result).isEqualTo(false);
    }


}