package epam.lab4;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TourManager {
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

    public void addTour(Tour tour)
    {
        tours.add(tour);
    }

    List<Tour> find(final List<Search> search, final List<Sort> sorting) {
        List<Tour> result = new ArrayList<Tour>();
        for(Tour tour : tours) {
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
                if(compare != field.compare)
                {
                    match = false;
                    break;
                }
            }
            if(match)
                result.add(tour);
        }
        if(!sorting.isEmpty()) {
            Collections.sort(result, new Comparator<Tour>() {
                @Override
                public int compare(Tour left, Tour right) {
                    int compare = 0;
                    for(Sort field : sorting) {
                        switch(field.field) {
                            case NAME:
                                compare = left.getName().compareTo(right.getName());
                                break;
                            case COST:
                                compare = Integer.compare(left.getCost(), right.getCost());
                                break;
                            case LENGTH:
                                compare = Integer.compare(left.getLengthDays(), right.getLengthDays());
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

    private List<Tour> tours = new ArrayList<Tour>();
}
