import java.util.UUID;

public class Booking {
    private String bookingId;
    private String flightId;
    private String userId;
    private double amount;

    public Booking(String flightId, String userId, double amount) {
        this.bookingId = UUID.randomUUID().toString();
        this.flightId = flightId;
        this.userId = userId;
        this.amount = amount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}
