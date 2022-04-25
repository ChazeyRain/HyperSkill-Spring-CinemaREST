package cinema.control;

import cinema.room.Seat;

import java.util.UUID;

public class Purchase {

    private final Seat ticket;
    private final UUID token;


    public Purchase(Seat seat, UUID token) {
        this.ticket = seat;
        this.token = token;
    }

    public Purchase(Seat seat) {
        this(seat, UUID.randomUUID());
    }

    public Purchase(UUID token) {
        this(null, token);
    }

    public Seat getTicket() {
        return ticket;
    }

    public UUID getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        Purchase purchase;

        try {
            purchase = (Purchase) o;

            return ticket == purchase.getTicket() && purchase.getToken().compareTo(token) == 0;

        } catch (ClassCastException e) {
            return false;
        }
    }
}
