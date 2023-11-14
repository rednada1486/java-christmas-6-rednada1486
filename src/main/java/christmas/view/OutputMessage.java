package christmas.view;

public enum OutputMessage {
    INTRODUCE_PROGRAM("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    PREVIEW_THE_EVENT_BENEFIT_IN_THE_DAY("에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    MENU("------------------------------------메뉴판------------------------------------"),
    DIVIDER_LINE("-----------------------------------------------------------------------------"),
    NOTHING("없음"),
    ORDER_MENU("<주문 메뉴>");

    private final String outputMessage;

    OutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public String getOutputMessage() {
        return outputMessage;
    }
}
