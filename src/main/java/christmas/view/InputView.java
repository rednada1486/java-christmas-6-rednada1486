package christmas.view;

import christmas.utils.CalendarUtil;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.view.InputMessage.WHAT_WOULD_YOU_LIKE_TO_ORDER;
import static christmas.view.InputMessage.WHEN_IS_THE_EXPECTED_VISIT_DAY_IN_DECEMBER;
import static christmas.view.OutputMessage.DIVIDER_LINE;
import static christmas.view.OutputView.printAllMenu;

public class InputView {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    public static String readOrderList() {
        System.out.println(WHAT_WOULD_YOU_LIKE_TO_ORDER.getInputMessage());
        printAllMenu();
        return readLine();
    }

    public static String readReservationDate() {
        System.out.println(WHEN_IS_THE_EXPECTED_VISIT_DAY_IN_DECEMBER.getInputMessage());
        System.out.println(DIVIDER_LINE.getOutputMessage());

        String calendar = CalendarUtil.makeCalendar(YEAR, MONTH);
        System.out.println(calendar);

        System.out.println(DIVIDER_LINE.getOutputMessage());
        return readLine();
    }
}
