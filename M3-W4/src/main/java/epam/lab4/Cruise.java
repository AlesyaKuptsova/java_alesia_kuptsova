package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/3/2015.
 */
//TODO Add Tour to name of class
public class Cruise extends BaseTour {
    private String roomType;
    private String foodType;
    private String city;

    public Cruise(String name, int cost, int length, String roomType, String foodType, String city) {
        super(name, cost, length);
        this.roomType = roomType;
        this.foodType = foodType;
        this.city = city;
    }

    @Override
    public Transport getTransport() {
        return Transport.SHIP;
    }

    @Override
    public String toString() {
        return String.format("Cruise '%s', cost %d, length %d, roomType %s",
                getName(), getCost(), getLengthDays(), roomType);
    }

    public String getRoomType() {
        return roomType;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getCity() {
        return city;
    }

    public TourType getType() {
        return TourType.CRUISE;
    }
}
