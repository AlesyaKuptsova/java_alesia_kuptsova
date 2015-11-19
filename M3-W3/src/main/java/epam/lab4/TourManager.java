package epam.lab4;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TourManager {
    private TourStorage storage;
    private List<Tour> tours = new ArrayList<Tour>();

    public TourManager(TourStorage storage) {
        this.storage = storage;
        try {
            this.tours = storage.load();
        }
        catch(TourStorageException exc) {
            System.err.println(exc.getMessage());
        }
    }

    enum Field {
        NAME,
        COST,
        LENGTH,
        TRANSPORT
    }

    public static class Search {
        public Search(Field field, Object value, int compare) {
            this.field = field;
            this.value = value;
            this.compare = compare;
        }

        private Field field;
        private Object value;
        private int compare;
    }

    public static class Sort {
        public Sort(Field field, boolean ascending) {
            this.field= field;
            this.ascending = ascending;
        }

        private Field field;
        private boolean ascending;
    }

    public static class TourWithId {
        public int id;
        public Tour tour;

        public TourWithId(int id, Tour tour) {
            this.id = id;
            this.tour = tour;
        }
    }

    public void addTour(Tour tour) {
        //TODO Add tour to list only after adding to file
        tours.add(tour);
        try {
            storage.store(tours);
        }
        catch(TourStorageException exc) {
        }
    }

    void removeTour(int id) {
        if(id < 0 || id >= tours.size()) {
            throw new InvalidTourIdException(id, String.format("invalid tour id: %d", id));
        }
        tours.remove(id);
        try {
            storage.store(tours);
        }
        catch(TourStorageException exc) {
            System.err.println(exc.getMessage());
        }
    }

    List<TourWithId> find(final List<Search> search, final List<Sort> sorting) {
        List<TourWithId> result = new ArrayList<TourWithId>();
        for(int i = 0; i < tours.size(); i++) {
            Tour tour = tours.get(i);
            boolean match = true;
            for(Search field : search) {
                int compare = 0;
                switch(field.field) {
                    case NAME:
                        compare = tour.getName().compareTo((String)field.value);
                        break;
                    case COST:
                        compare = Integer.compare(tour.getCost(), (Integer)field.value);
                        break;
                    case LENGTH:
                        compare = Integer.compare(tour.getLengthDays(), (Integer)field.value);
                        break;
                    case TRANSPORT:
                        compare = (tour.getTransport() == (Transport)field.value)?1:0;
                        break;
                    default:
                        continue;
                }
                //TODO Format braces according with Java Coding Standards
                if(compare != field.compare)
                {
                    match = false;
                    break;
                }
            }
            //TODO Wrap all conditions / cycles with braces
            if(match)
                result.add(new TourWithId(i, tour));
        }
        if(!sorting.isEmpty()) {
            Collections.sort(result, new Comparator<TourWithId>() {
                @Override
                public int compare(TourWithId left, TourWithId right) {
                    int compare = 0;
                    for(Sort field : sorting) {
                        switch(field.field) {
                            case NAME:
                                compare = left.tour.getName().compareTo(right.tour.getName());
                                break;
                            case COST:
                                compare = Integer.compare(left.tour.getCost(), right.tour.getCost());
                                break;
                            case LENGTH:
                                compare = Integer.compare(left.tour.getLengthDays(), right.tour.getLengthDays());
                                break;
                            default:
                                break;
                        }
                        if(!field.ascending)
                            compare *= -1;
                        if(compare != 0)
                            break;
                    }
                    return compare;
                }
            });
        }
        return result;
    }
}
