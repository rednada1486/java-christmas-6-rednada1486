package christmas.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


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
        OutputView.printAllMenu();
        String result = outputStreamCaptor.toString();

        // then
        Assertions.assertThat(result).containsSubsequence("<애피타이저>", "<메인>", "<디저트>", "<음료>");
    }
}