package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/2/2015.
 */
//TODO Add Tour to name of class
public class Treatment extends BaseTour {
    private int proceduresAmount;
    private Transport transport;

    public Treatment(String name, int cost, int length, Transport transport, int proceduresAmount) {
        super(name, cost, length);
        this.proceduresAmount = proceduresAmount;
        this.transport = transport;
    }

    public static Tour load(String[] values) {
        try {
            return new Treatment(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), Integer.valueOf(values[4]));
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
        return String.format("Treatment '%s', cost %d, length %d, %d procedures",
                getName(), getCost(), getLengthDays(), proceduresAmount);
    }

    public int getProceduresAmount() {
        return proceduresAmount;
    }

    public TourType getType() {
        return TourType.TREATMENT;
    }

    public String[] store() {
        return new String[]{name, Integer.toString(cost), Integer.toString(length), transport.toString(),
            Integer.toString(proceduresAmount)};
    }
}

