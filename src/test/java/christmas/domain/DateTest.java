package christmas.domain;

import static christmas.view.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



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

}