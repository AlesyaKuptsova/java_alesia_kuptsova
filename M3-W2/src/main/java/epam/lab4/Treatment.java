package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/2/2015.
 */
public class Treatment extends BaseTour {
    private int proceduresAmount;
    private Transport transport;

    public Treatment(String name, int cost, int length, Transport transport, int proceduresAmount) {
        super(name, cost, length);
        this.proceduresAmount = proceduresAmount;
        this.transport = transport;
    }

    @Override
    public Transport getTransport() {
        return transport;
    }

    public int getProceduresAmount() {
        return proceduresAmount;
    }
}

