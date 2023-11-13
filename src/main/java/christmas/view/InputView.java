package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.view.InputMessage.WHAT_WOULD_YOU_LIKE_TO_ORDER;
import static christmas.view.OutputView.*;

public class InputView {
    public static String readOrderList() {
        System.out.println(WHAT_WOULD_YOU_LIKE_TO_ORDER.getInputMessage());
        printAllMenu();
        return readLine();
    }
}
