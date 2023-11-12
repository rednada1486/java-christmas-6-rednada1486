package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.Or;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static christmas.domain.Menu.*;
import static org.assertj.core.api.Assertions.*;


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
        assertThat(result).contains("제로콜라 1개", "타파스 2개");
    }
}