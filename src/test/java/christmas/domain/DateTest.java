package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.view.ErrorMessage.INVALID_DATE_MESSAGE;
import static org.assertj.core.api.Assertions.*;

class DateTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "15", "31"})
    @DisplayName("정상 입력값으로 Date 객체를 생성하면 아무런 예외가 발생하지 않는다.")
    void dateGeneratorShouldNotThrowExceptionWhenNormalValueInputted(String input) {
        // when, then
        assertThatCode(() -> new Date(input))
                .doesNotThrowAnyException();
    }


    @ParameterizedTest
    @ValueSource(strings = {"A", "1a", "*"})
    @DisplayName("숫자가 아닌 유저 입력값으로 Date 객체를 생성하면 예외가 발생한다.")
    void dateGeneratorShouldThrowIllegalArgumentExceptionWhenDayIsNotANumber(String input) {
        // when, then
        assertThatThrownBy(() -> new Date(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_MESSAGE.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    @DisplayName("정상 범위에 있지 않은 숫자 문자열로 Date 객체를 생성하면 예외가 발생한다.")
    void dateGeneratorShouldThrowIllegalArgumentExceptionWhenDayIsNotInCorrectRange(String input) {
        // when, then
        assertThatThrownBy(() -> new Date(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_MESSAGE.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "10", "11", "12", "13", "14", "17", "18", "19", "20", "21"})
    @DisplayName("isWeekday 메서드는 해당 일자가 평일이면 true를 반환한다.")
    void isWeekdayShouldReturnTrueWhenTheDayIsWeekday(String day) {
        // given
        Date date = new Date(day);

        // when
        boolean result = date.isWeekday();

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "8", "9", "15", "16", "22", "23", "29", "30"})
    @DisplayName("isWeekend 메서드는 해당 일자가 주말이면 true를 반환한다.")
    void isWeekendShouldReturnTrueWhenTheDayIsWeekend(String day) {
        // given
        Date date = new Date(day);

        // when
        boolean result = date.isWeekend();

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"24", "25", "26", "27", "28", "31"})
    @DisplayName("isWeekend 메서드는 해당 일자가 주말이 아니면 false를 반환한다.")
    void isWeekendShouldReturnFalseWhenTheDayIsNotWeekend(String day) {
        // given
        Date date = new Date(day);

        // when
        boolean result = date.isWeekend();

        // then
        assertThat(result).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "24", "25", "31"})
    @DisplayName("isStarday 메서드는 해당 일자에 별이 있으면 true를 반환한다.")
    void isStardayShouldReturnTrueWhenTheDayIsStarday(String day) {
        // given
        Date date = new Date(day);

        // when
        boolean result = date.isStarDay();

        // then
        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "4", "5", "6", "7", "8", "9", "11", "12", "13", "14", "15", "16", "18", "19"})
    @DisplayName("isStarday 메서드는 해당 일자에 별이 없으면 false를 반환한다.")
    void isStardayShouldReturnFalseWhenTheDayIsNotStarday(String day) {
        // given
        Date date = new Date(day);

        // when
        boolean result = date.isStarDay();

        // then
        assertThat(result).isEqualTo(false);
    }
}