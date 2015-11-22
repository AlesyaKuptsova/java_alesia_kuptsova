package epam.lab4;

import java.util.List;

public interface TourStorage {
    public void store(List<Tour> tours);
    public List<Tour> load();
}
