import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlightBookingManager flightBookingManager = new FlightBookingManager();

        flightBookingManager.addAirline("JetAir", false);
        flightBookingManager.addAirline("IndiGo", true);
        flightBookingManager.addAirline("Delta", false);

        flightBookingManager.addFlight("AI-202", "JetAir", "DEL", "BLR", 5000);
        flightBookingManager.addFlight("AI-203", "JetAir", "DEL", "BOM", 4500);
        flightBookingManager.addFlight("IG-204", "IndiGo", "BLR", "DEL", 5500);
        flightBookingManager.addFlight("IG-205", "IndiGo", "DEL", "BOM", 4000);
        flightBookingManager.addFlight("DL-206", "Delta", "BLR", "NYC", 15000);
        flightBookingManager.addFlight("DL-207", "Delta", "DEL", "LON", 12000);
        flightBookingManager.addFlight("DL-208", "Delta", "LON", "NYC", 8000);

        flightBookingManager.addUser("Varrnit", "Varrnit@example.com");
        flightBookingManager.addUser("Pulkit", "Pulkit@example.com");

        System.out.println("Cheapest flight from DEL to NYC:");
        List<Flight> cheapestFlight = flightBookingManager.getCheapestFlight("DEL", "NYC");
        for (Flight flight : cheapestFlight) {
            System.out.println(flight.getOrigin() + " to " + flight.getDestination() + " via " + flight.getAirline().getName() + " for " + flight.getCost());
        }

        System.out.println("\nMinimum hops flight from DEL to NYC:");
        List<Flight> minHopsFlight = flightBookingManager.getMinHopsFlight("DEL", "NYC");
        for (Flight flight : minHopsFlight) {
            System.out.println(flight.getOrigin() + " to " + flight.getDestination() + " via " + flight.getAirline().getName() + " for " + flight.getCost());
        }

        System.out.println("\nFlights from DEL to BOM with meal service:");
        List<Flight> flightsWithMealService = flightBookingManager.searchFlights("DEL", "BOM", true);
        for (Flight flight : flightsWithMealService) {
            System.out.println(flight.getOrigin() + " to " + flight.getDestination() + " via " + flight.getAirline().getName() + " for " + flight.getCost());
        }
    }
}
