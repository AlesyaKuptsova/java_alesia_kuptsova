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
}
