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

    public static Tour load(String[] values) {
        try {
            return new Cruise(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    values[3], values[4], values[5]);
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
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

    public String[] store() {
        return new String[]{name, Integer.toString(cost), Integer.toString(length), roomType,
            foodType, city};
    }
}
