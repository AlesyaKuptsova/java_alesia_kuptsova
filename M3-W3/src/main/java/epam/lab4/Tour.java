package epam.lab4;

public interface Tour {
    String getName();
    int getCost();
    int getLengthDays();
    Transport getTransport();

    TourType getType();

    String[] store();
}
