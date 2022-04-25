package cinema.control;

import cinema.control.Exceptions.ControllerError;
import cinema.room.Seat;
import cinema.room.Seats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    private final Seats seats = Seats.getCurrent();

    private final HashMap<UUID, Purchase> purchases = new HashMap<>();
    private final List<Refund> returns = new ArrayList<>();

    private final Stats stats = new Stats();

    static {
        Seats.initSeats(9, 9);
    }

    {
        stats.changeAvailableSeats(seats.getAvailable_seats().size());
    }


    @GetMapping("/seats")
    public Seats getSeats() {
        return seats;
    }


    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody Seat seat) {

        if (seat.getRow() < 0 ||
                seat.getColumn() < 0 ||
                seat.getRow() >= seats.getTotal_rows() ||
                seat.getColumn() >= seats.getTotal_columns()) {
            return new ResponseEntity<>(new ControllerError("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        Seat bookedSeat = seats.bookSeat(seat);

        if (bookedSeat == null) {
            System.out.println("hey");
            return new ResponseEntity<>(new ControllerError("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }

        Purchase currentPurchase = new Purchase(bookedSeat);

        purchases.put(currentPurchase.getToken(), currentPurchase);

        stats.changeAvailableSeats(-1);
        stats.changeIncome(bookedSeat.getPrice());
        stats.changePurchasedTicket(1);

        return new ResponseEntity<>(currentPurchase, HttpStatus.OK);
    }


    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody Purchase purchase) {

        UUID uuid = purchase.getToken();

        if (purchases.containsKey(uuid)) {

            Refund refund = new Refund(purchases.get(uuid).getTicket());

            purchases.remove(uuid);

            returns.add(refund);

            stats.changePurchasedTicket(-1);
            stats.changeAvailableSeats(1);
            stats.changeIncome(-refund.getReturned_ticket().getPrice());

            return new ResponseEntity<>(refund, HttpStatus.OK);

        } else {
            System.out.println("Hey");
            return new ResponseEntity<>(new ControllerError("Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/stats")
    public ResponseEntity<Object> postStats(@RequestParam(required = false) String password) {

        if (!"super_secret".equals(password)) {
            return new ResponseEntity<>(new ControllerError("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
