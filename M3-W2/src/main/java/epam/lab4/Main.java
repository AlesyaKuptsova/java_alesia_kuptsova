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
        manager.addTour(new Cruise("cruise1", 100, 10, "DBL", "FB", "Zimbabwee"));
        manager.addTour(new Rest("rest1", 1000, 66, Transport.BUS, "super-hotel", 5, 100, true));
        manager.addTour(new Rest("rest11", 1005, 66, Transport.BUS, "super-hotel", 5, 100, true));
        manager.addTour(new Rest("rest111", 1001, 66, Transport.BUS, "super-hotel", 5, 100, true));
        manager.addTour(new Rest("rest2", 999, 10, Transport.PLANE, "super-mega-hotel", 5, 10, true));
        manager.addTour(new Shopping("shop1", 200, 3, Transport.BUS, "Phnom Penh", "super-store", 10));
        manager.addTour(new Cruise("cruse2", 150, 7, "SGL", "FB+", "Tallinn - Helsinki - Stockholm"));

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
                System.out.println("quit");
                System.out.println("-------------------");
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                //TODO Wrap all conditions / cycles with braces
                if(words.length == 0)
                    continue;
                //TODO Format braces according with Java Coding Standards
                if(words[0].equals("quit"))
                {
                    break;
                }
                //TODO Format braces according with Java Coding Standards
                else if(words[0].equals("search"))
                {
                    System.out.println("-------------------");
                    //TODO Develop separate method for printing
                    for(Tour tour : manager.find(search, sort)) {
                        System.out.println(tour.toString());
                    }
                }
                //TODO Format braces according with Java Coding Standards
                else if(words[0].equals("sort"))
                {
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
                            System.out.println("invalid field");
                            continue;
                        }
                        sort.add(new TourManager.Sort(field, true));
                        //TODO Add message about completed action
                    }
                    //TODO Add Error message if format is incorrect
                }
                //TODO Format braces according with Java Coding Standards
                else if(words[0].equals("cond"))
                {
                    try {
                        if(words.length >= 3) {
                            TourManager.Field field = TourManager.Field.NAME;
                            Object value = TourManager.Field.NAME;
                            int comparator = 0;
                            //TODO You can use switch here
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
                                throw new RuntimeException("invalid field");
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
                                        throw new RuntimeException("invalid compare operation");
                                }
                            }
                            search.add(new TourManager.Search(field, value, comparator));
                            //TODO Add message about completed action
                        }
                        //TODO Add Error message if format is incorrect
                    }
                    //TODO Catch only expected type of exception here
                    catch(Exception exc) {
                        System.out.println(String.format("%s", exc.getMessage()));
                    }
                }
                //TODO Format braces according with Java Coding Standards
                else if(words[0].equals("clear"))
                {
                    search.clear();
                    sort.clear();
                }
            }
        }
        catch(Exception exc) {
        }
        finally {
            scanner.close();
        }
    }
}
