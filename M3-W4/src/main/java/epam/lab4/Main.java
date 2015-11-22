package epam.lab4;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Alesia Kuptsova on 11/3/2015.
 */
public class Main {
    public static void main(String[] args){
        TourManager manager = new TourManager();
        TourStorage storage = new DBTourStorage("tours.db");
        try {
            manager.loadTours(storage);
        }
        catch(TourManagerException exc) {
            System.err.println(String.format("load failed: %s", exc.getMessage()));
        }
        manager.addTour(new Cruise("cruise1", 100, 10, "DBL", "FB", "Zimbabwee"));
        manager.addTour(new Rest("rest1", 1000, 66, Transport.BUS, "super-hotel", 5, 100, true));
        manager.addTour(new Rest("rest11", 1005, 66, Transport.BUS, "super-hotel", 5, 100, true));
        manager.addTour(new Rest("rest111", 1001, 66, Transport.BUS, "super-hotel", 5, 100, true));
        manager.addTour(new Rest("rest2", 999, 10, Transport.PLANE, "super-mega-hotel", 5, 10, true));
        manager.addTour(new Shopping("shop1", 200, 3, Transport.BUS, "Phnom Penh", "super-store", 10));
        manager.addTour(new Cruise("cruse2", 150, 7, "SGL", "FB+", "Tallinn - Helsinki - Stockholm"));
        manager.addTour(new Treatment("treatment1", 95, 7, Transport.BUS, 66));

        Scanner scanner = new Scanner(System.in);
        try {
            List<TourManager.Search> search = new ArrayList<TourManager.Search>();
            List<TourManager.Sort> sort = new ArrayList<TourManager.Sort>();
            while(true) {
                //TODO please do menu more informative, e.g. add options to select
                //TODO add printing current conditions and sorting
                System.out.println("-------------------");
                System.out.println("actions: ");
                System.out.println("cond <name> <value> [op]");// Example: cond cost 100 <
                System.out.println("sort <name>");//Example: sort length
                System.out.println("search"); // You should write "search" if you want to sort used
                System.out.println("clear");
                System.out.println("add <tour type> [tour arguments...]");
                System.out.println("remove <tour id>");
                System.out.println("quit");
                System.out.println("-------------------");
                String line = scanner.nextLine();
                try {
                    String[] words = line.split(" ");
                    if(words.length == 0) {
                        continue;
                    }
                    if(words[0].equals("quit")) {
                        break;
                    }
                    else if(words[0].equals("search")) {
                        System.out.println("-------------------");
                        //TODO Develop separate method for printing
                        List<TourManager.TourWithId> tours = manager.find(search, sort);
                        System.out.println(String.format("found %d tours:", tours.size()));
                        for(TourManager.TourWithId tour : tours) {
                            System.out.println(String.format("%d - %s", tour.id, tour.tour.toString()));
                        }
                    }
                    else if(words[0].equals("sort")) {
                        if(words.length == 2) {
                            TourManager.Field field = TourManager.Field.NAME;
                            if(words[1].equals("name")) {
                                field = TourManager.Field.NAME;
                            }
                            else if(words[1].equals("cost")) {
                                field = TourManager.Field.COST;
                            }
                            else if(words[1].equals("length")) {
                                field = TourManager.Field.LENGTH;
                            }
                            else {
                                throw new InputException(line, "invalid field");
                            }
                            sort.add(new TourManager.Sort(field, true));
                            //TODO Add message about completed action
                        }
                        //TODO Add Error message if format is incorrect
                    }
                    //TODO Format braces according with Java Coding Standards
                    else if(words[0].equals("cond")) {
                        if(words.length >= 3) {
                            TourManager.Field field = TourManager.Field.NAME;
                            Object value = TourManager.Field.NAME;
                            int comparator = 0;
                            //TODO You can use switch here
                            try {
                                if(words[1].equals("name")) {
                                    field = TourManager.Field.NAME;
                                    value = words[2];
                                }
                                else if(words[1].equals("cost")) {
                                    field = TourManager.Field.COST;
                                    value = Integer.parseInt(words[2]);
                                }
                                else if(words[1].equals("length")) {
                                    field = TourManager.Field.LENGTH;
                                    value = Integer.parseInt(words[2]);
                                }
                                else if(words[1].equals("transport")) {
                                    field = TourManager.Field.TRANSPORT;
                                    value = Integer.parseInt(words[2]);
                                    comparator = 1;
                                }
                                else {
                                    throw new InputException(line, "invalid field");
                                }
                                if(comparator == 0)
                                {
                                    if(words.length == 4)
                                    {
                                        if(words[3].equals("<"))
                                            comparator = -1;
                                        else if(words[3].equals(">"))
                                            comparator = 1;
                                        else if(words[3].equals("="))
                                            comparator = 0;
                                        else
                                            throw new InputException(line, "invalid compare operation");
                                    }
                                }
                                search.add(new TourManager.Search(field, value, comparator));
                            }
                            catch(NumberFormatException exc) {
                                throw new InputException(line, "invalid value format");
                            }
                            //TODO Add message about completed action
                        }
                        //TODO Add Error message if format is incorrect
                    }
                    else if(words[0].equals("clear")) {
                        search.clear();
                        sort.clear();
                    }
                    else if(words[0].equals("add")) {
                        if(words.length <= 1)
                            throw new InputException(line, "invalid add command");
                        String[] data = new String[words.length-2];
                        for(int i =2; i < words.length; ++i) {
                            data[i-2] = words[i];
                        }
                        try {
                            if(words[1].equals(TourType.CRUISE.getType())) {
                                manager.addTour(loadCruise(data));
                            }
                            else if(words[1].equals(TourType.SHOPPING.getType())) {
                                manager.addTour(loadShopping(data));
                            }
                            else if(words[1].equals(TourType.EXCURSION.getType())) {
                                manager.addTour(loadExcursion(data));
                            }
                            else if(words[1].equals(TourType.TREATMENT.getType())) {
                                manager.addTour(loadTreatment(data));
                            }
                            else if(words[1].equals(TourType.REST.getType())) {
                                manager.addTour(loadRest(data));
                            }
                            else {
                                throw new InputException(line, "invalid tour type");
                            }
                        }
                        catch(IllegalArgumentException exc) {
                            throw new InputException(line, "invalid tour arguments");
                        }
                    }
                    else if(words[0].equals("remove")) {
                        if(words.length == 2) {
                            int id = 0;
                            try {
                                id = Integer.parseInt(words[1]);
                            }
                            catch(NumberFormatException exc) {
                                throw new InputException(line, "invalid tour id format");
                            }
                            try {
                                manager.removeTour(id);
                            }
                            catch(InvalidTourIdException exc) {
                                throw new InputException(line, "invalid tour id");
                            }
                        }
                        else {
                            throw new InputException(line, "invalid remove command format");
                        }
                    }
                    else {
                        throw new InputException(line, "unknown command");
                    }
                }
                catch(InputException exc) {
                    System.out.println(String.format("%s in '%s'", exc.getMessage(), exc.getInput()));
                }
            }
        }
        catch(Exception exc) {
        }
        finally {
            scanner.close();
        }
        try {
            manager.storeTours(storage);
        }
        catch(TourManagerException exc) {
            System.err.println(String.format("store failed: %s", exc.getMessage()));
        }
    }

    private static Tour loadShopping(String[] values) {
        try {
            return new Shopping(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), values[4], values[5], Integer.valueOf(values[6]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

    private static Tour loadRest(String[] values) {
        try {
            return new Rest(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), values[4], Integer.valueOf(values[5]),
                    Integer.valueOf(values[6]), Boolean.valueOf(values[7]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

    private static Tour loadCruise(String[] values) {
        try {
            return new Cruise(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    values[3], values[4], values[5]);
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

    private static Tour loadTreatment(String[] values) {
        try {
            return new Treatment(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), Integer.valueOf(values[4]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

    private static Tour loadExcursion(String[] values) {
        try {
            return new Excursions(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), Integer.valueOf(values[4]), Integer.valueOf(values[5]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

}
