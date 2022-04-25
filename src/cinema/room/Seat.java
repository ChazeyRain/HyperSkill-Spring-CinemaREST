package cinema.room;

public class Seat {

    private final int row;
    private final int column;
    private int price = -1;

    Seat() {
        this(-1, -1);
    }

    Seat (int row, int column) {
        this(row, column, -1);
    }

    Seat (int row, int column, Integer price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        try {
            Seat seat = (Seat) o;

            return (row == seat.getRow()) && (column == seat.getColumn());

        } catch (ClassCastException e) {
            return false;
        }
    }
}
