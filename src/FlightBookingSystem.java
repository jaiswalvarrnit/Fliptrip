import java.util.*;

public class FlightBookingSystem {
    private static FlightBookingSystem instance;
    private Map<String, List<Flight>> flights;
    private Map<String, User> users;
    private Map<String, Booking> bookings;
    private Map<String, Airline> airlines;

    private FlightBookingSystem() {
        flights = new HashMap<>();
        users = new HashMap<>();
        bookings = new HashMap<>();
        airlines = new HashMap<>();
    }

    public static FlightBookingSystem getInstance() {
        if (instance == null) {
            instance = new FlightBookingSystem();
        }
        return instance;
    }

    public void addAirline(Airline airline) {
        airlines.put(airline.getName(), airline);
    }

    public Map<String, Airline> getAirlines() {
        return airlines;
    }

    public void addFlight(Flight flight) {
        flights.computeIfAbsent(flight.getOrigin(), k -> new ArrayList<>()).add(flight);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Booking bookFlight(String flightId, String userId, double amount) {
        Flight flight = null;
        for (List<Flight> flightList : flights.values()) {
            for (Flight f : flightList) {
                if (f.getId().equals(flightId)) {
                    flight = f;
                    break;
                }
            }
            if (flight != null) break;
        }

        if (flight == null) {
            throw new IllegalArgumentException("Flight not found.");
        }

        Booking booking = new Booking(flightId, userId, amount);
        bookings.put(booking.getBookingId(), booking);
        return booking;
    }

    public List<Flight> getCheapestFlight(String origin, String destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(Node::getCost));
        Map<String, Double> minCost = new HashMap<>();
        pq.add(new Node(origin, 0, null, null));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String currentCity = current.getCity();
            double currentCost = current.getCost();

            if (currentCity.equals(destination)) {
                return reconstructPath(current);
            }

            if (currentCost > minCost.getOrDefault(currentCity, Double.MAX_VALUE)) {
                continue;
            }

            for (Flight flight : flights.getOrDefault(currentCity, new ArrayList<>())) {
                double newCost = currentCost + flight.getCost();
                String nextCity = flight.getDestination();

                if (newCost < minCost.getOrDefault(nextCity, Double.MAX_VALUE)) {
                    minCost.put(nextCity, newCost);
                    pq.add(new Node(nextCity, newCost, current, flight));
                }
            }
        }

        return new ArrayList<>();
    }

    public List<Flight> getMinHopsFlight(String origin, String destination) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Node(origin, 0, null, null));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentCity = current.getCity();

            if (currentCity.equals(destination)) {
                return reconstructPath(current);
            }

            if (visited.contains(currentCity)) {
                continue;
            }

            visited.add(currentCity);

            for (Flight flight : flights.getOrDefault(currentCity, new ArrayList<>())) {
                String nextCity = flight.getDestination();
                queue.add(new Node(nextCity, current.getHops() + 1, current, flight));
            }
        }

        return new ArrayList<>();
    }

    private List<Flight> reconstructPath(Node node) {
        List<Flight> path = new ArrayList<>();
        while (node.getPrevious() != null) {
            path.add(0, node.getFlight());
            node = node.getPrevious();
        }
        return path;
    }

    private static class Node {
        private String city;
        private double cost;
        private int hops;
        private Node previous;
        private Flight flight;

        public Node(String city, double cost, Node previous, Flight flight) {
            this.city = city;
            this.cost = cost;
            this.previous = previous;
            this.flight = flight;
        }

        public Node(String city, int hops, Node previous, Flight flight) {
            this.city = city;
            this.hops = hops;
            this.previous = previous;
            this.flight = flight;
        }

        public String getCity() {
            return city;
        }

        public double getCost() {
            return cost;
        }

        public int getHops() {
            return hops;
        }

        public Node getPrevious() {
            return previous;
        }

        public Flight getFlight() {
            return flight;
        }
    }

    public List<Flight> searchFlights(String origin, String destination, boolean requiresMealService) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights.getOrDefault(origin, new ArrayList<>())) {
            if (flight.getDestination().equalsIgnoreCase(destination) &&
                    (!requiresMealService || flight.getAirline().servesMeals())) {
                result.add(flight);
            }
        }
        return result;
    }

}
