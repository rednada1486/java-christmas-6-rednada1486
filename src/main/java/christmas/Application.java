package christmas;

import christmas.controller.ReservationController;

public class Application {
    public static void main(String[] args) {
        ReservationController reservationController = new ReservationController();
        reservationController.play();
    }
}
