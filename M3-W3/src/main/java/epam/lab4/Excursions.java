package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/2/2015.
 */
//TODO Add Tour to name of class
public class Excursions extends BaseTour {

    private Transport transport;
    private int excursionCount;
    private int excursionCost;

    public Excursions(String name, int cost, int length, Transport transport, int excursionCount, int excursionCost) {
        super(name, cost, length);
        this.transport = transport;
        //TODO Add initializing of excursionCount and excursionCost
    }

    public static Tour load(String[] values) {
        try {
            return new Excursions(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), Integer.valueOf(values[4]), Integer.valueOf(values[5]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
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

    public TourType getType() {
        return TourType.EXCURSION;
    }

    public String[] store() {
        return new String[]{name, Integer.toString(cost), Integer.toString(length), transport.toString(),
            Integer.toString(excursionCount), Integer.toString(excursionCost)};
    }
}
