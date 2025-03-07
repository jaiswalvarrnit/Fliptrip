import java.util.List;

public class FlightBookingManager {
    private FlightBookingSystem flightBookingSystem;

    public FlightBookingManager() {
        this.flightBookingSystem = FlightBookingSystem.getInstance();
    }

    public void addAirline(String name, boolean servesMeals) {
        Airline airline = new Airline(name, servesMeals);
        flightBookingSystem.addAirline(airline);
    }

    public void addFlight(String flightNumber, String airlineName, String origin, String destination, double cost) {
        Airline airline = null;
        for (Airline a : flightBookingSystem.getAirlines().values()) {
            if (a.getName().equalsIgnoreCase(airlineName)) {
                airline = a;
                break;
            }
        }

        if (airline == null) {
            throw new IllegalArgumentException("Airline not found.");
        }

        Flight flight = new Flight(flightNumber, airline, origin, destination, cost);
        flightBookingSystem.addFlight(flight);
    }

    public void addUser(String name, String email) {
        User user = new User(name, email);
        flightBookingSystem.addUser(user);
    }

    public Booking bookFlight(String flightId, String userId, double amount) {
        return flightBookingSystem.bookFlight(flightId, userId, amount);
    }

    public List<Flight> getCheapestFlight(String origin, String destination) {
        return flightBookingSystem.getCheapestFlight(origin, destination);
    }

    public List<Flight> getMinHopsFlight(String origin, String destination) {
        return flightBookingSystem.getMinHopsFlight(origin, destination);
    }

    public List<Flight> searchFlights(String origin, String destination, boolean requiresMealService) {
        return flightBookingSystem.searchFlights(origin, destination, requiresMealService);
    }

}
