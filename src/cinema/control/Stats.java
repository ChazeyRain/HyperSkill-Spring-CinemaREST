package cinema.control;

public class Stats {

    private int current_income = 0;
    private int number_of_available_seats = 0;
    private int number_of_purchased_tickets = 0;


    public void changeIncome(int val) {
        current_income += val;
    }

    public void changeAvailableSeats(int val) {
        number_of_available_seats += val;
    }

    public void changePurchasedTicket(int val) {
        number_of_purchased_tickets += val;
    }


    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

}
