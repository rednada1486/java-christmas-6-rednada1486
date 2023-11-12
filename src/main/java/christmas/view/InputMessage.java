package christmas.view;

public enum InputMessage {
    WHAT_WOULD_YOU_LIKE_TO_ORDER("주문하실 메뉴와 그 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String InputMessage;

    InputMessage(String inputMessage) {
        InputMessage = inputMessage;
    }

    public String getInputMessage() {
        return InputMessage;
    }
}
