package epam.lab4;

public abstract class BaseTour implements Tour {
    protected String name;
    protected int cost;
    protected int length;

    public BaseTour(String name, int cost, int length) {
        this.name = name;
        this.cost = cost;
        this.length = length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getLengthDays() {
        return length;
    }
}
