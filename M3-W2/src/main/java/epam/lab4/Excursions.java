package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/2/2015.
 */
public class Excursions extends BaseTour {

    private Transport transport;
    private int excursionCount;
    private int excursionCost;

    public Excursions(String name, int cost, int length, Transport transport, int excursionCount, int excursionCost) {
        super(name, cost, length);
        this.transport = transport;
    }

    @Override
    public Transport getTransport() {
        return transport;
    }

    public int getExcursionCount() {
        return excursionCount;
    }

    public int getExcursionCost() {
        return excursionCost;
    }
}
