package cinema.control;

import cinema.room.Seat;

public class Refund {

    private final Seat returned_ticket;

    public Refund(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }

}
