package christmas.domain;

import static christmas.view.ErrorMessage.INVALID_ORDER_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    @DisplayName("유저입력값으로 Order 객체를 생성할 때, 정상 입력값일 경우 예외를 발생시킨다.")
    void orderGeneratorNotThrowIllegalArgumentExceptionWhenNumberIsLagerThanZero() {
        // given
        String userInput = "제로콜라-1";

        // when, then
        assertThatCode(() -> new Order(userInput)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유저입력값으로 Order 객체를 생성할 때, 유저입력값이 문자-숫자가 형태가 아니라면 예외를 발생시킨다.")
    void orderGeneratorThrowIllegalArgumentExceptionWhenArgumentFormatIsNotStringHyphenNumber() {
        // given
        String[] userInputArr = {"-", "제로콜라-", "-1", "제로콜라>1", "제로콜라->1", "제로콜라=1", "제로콜라,1", "제로-콜라1"};

        // when, then
        for (String userInput : userInputArr) {
            assertThatThrownBy(() -> new Order(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_ORDER_MESSAGE.getErrorMessage());
        }
    }

    @Test
    @DisplayName("유저입력값으로 Order 객체를 생성할 때, 유저입력값의 메뉴가 존재하지 않는 메뉴라면 예외를 발생시킨다.")
    void orderGeneratorThrowIllegalArgumentExceptionWhenUnavailableMenuIsInputted() {
        // given
        String userInput = "제로콜리-1";

        // when, then
        assertThatThrownBy(() -> new Order(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE.getErrorMessage());
    }

    @Test
    @DisplayName("유저입력값으로 Order 객체를 생성할 때, 유저입력값의 숫자가 1보다 작은 경우 예외를 발생시킨다.")
    void orderGeneratorThrowIllegalArgumentExceptionWhenCountIsSmallerThanOne() {
        // given
        String userInput = "제로콜라-0";

        // when, then
        assertThatThrownBy(() -> new Order(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE.getErrorMessage());
    }
}