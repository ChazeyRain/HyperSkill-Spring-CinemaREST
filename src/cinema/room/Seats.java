package cinema.room;

import java.util.ArrayList;
import java.util.List;

public class Seats {

    private static Seats current = null;

    private final int total_rows;
    private final int total_columns;
    private final List<Seat> available_seats;

    private Seats(int total_rows, int total_columns) {
        this.total_columns = total_columns;
        this.total_rows = total_rows;

        available_seats = new ArrayList<>();

        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                available_seats.add(new Seat(
                        i + 1,
                        j + 1,
                        i <= 4 ? 10 : 8));
            }
        }

    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }


    public Seat bookSeat(Seat seat) {

        int bookIndex = available_seats.indexOf(seat);

        if (bookIndex == -1) {
            return null;
        }

        seat = available_seats.get(bookIndex);

        available_seats.remove(bookIndex);

        return seat;
    }


    public static void initSeats(int rows, int columns) {
        current = new Seats(rows, columns);
    }

    public static Seats getCurrent() {
        return current;
    }

}
