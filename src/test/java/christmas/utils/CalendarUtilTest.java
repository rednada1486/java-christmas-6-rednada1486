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
}