package christmas.view;

public enum OutputMessage {
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
