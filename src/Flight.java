import java.util.UUID;

public class Flight {
    private String id;
    private String flightNumber;
    private Airline airline;
    private String origin;
    private String destination;
    private double cost;

    public Flight(String flightNumber, Airline airline, String origin, String destination, double cost) {
        this.id = UUID.randomUUID().toString();
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airline getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getCost() {
        return cost;
    }
}
