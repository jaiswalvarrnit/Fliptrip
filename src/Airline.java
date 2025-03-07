import java.util.UUID;

public class Airline {
    private String id;
    private String name;
    private boolean servesMeals;

    public Airline(String name, boolean servesMeals) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.servesMeals = servesMeals;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean servesMeals() {
        return servesMeals;
    }

}
