package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/3/2015.
 */
//TODO Add Tour to name of class
public class Shopping extends BaseTour {

    private Transport transport;
    private String city;
    private String storeName;
    private int numberOfStores;

    public Shopping(String name, int cost, int length, Transport transport, String city, String storeName, int numberOfStores) {
        super(name, cost, length);
        this.transport = transport;
        this.city = city;
        this.storeName = storeName;
        this.numberOfStores = numberOfStores;
    }

    public static Tour load(String[] values) {
        try {
            return new Shopping(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), values[4], values[5], Integer.valueOf(values[6]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Transport getTransport() {
        return transport;
    }

    @Override
    public String toString() {
        return String.format("Shopping '%s', cost %d, length %d, store '%s' * %d",
                getName(), getCost(), getLengthDays(), storeName, numberOfStores);
    }

    public String getCity() {
        return city;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getNumberOfStores() {
        return numberOfStores;
    }

    public TourType getType() {
        return TourType.SHOPPING;
    }

    public String[] store() {
        return new String[]{name, Integer.toString(cost), Integer.toString(length), transport.toString(),
            city, storeName, Integer.toString(numberOfStores)};
    }
}
