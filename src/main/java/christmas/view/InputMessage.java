package christmas.view;

public enum InputMessage {
    WHEN_IS_THE_EXPECTED_VISIT_DAY_IN_DECEMBER("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    WHAT_WOULD_YOU_LIKE_TO_ORDER("주문하실 메뉴와 그 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String InputMessage;

    InputMessage(String inputMessage) {
        InputMessage = inputMessage;
    }

    public String getInputMessage() {
        return InputMessage;
    }
}
