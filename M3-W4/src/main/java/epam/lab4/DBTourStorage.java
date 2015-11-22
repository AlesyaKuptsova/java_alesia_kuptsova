package epam.lab4;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBTourStorage implements TourStorage {
    private String filename;

    public DBTourStorage(String filename) {
        this.filename = filename;
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS cruises(name TEXT, cost INTEGER, length INTEGER, room TEXT, food TEXT, city TEXT);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS excursions(name TEXT, cost INTEGER, length INTEGER, transport TEXT, excursion_count INTEGER, excursion_cost INTEGER);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rests(name TEXT, cost INTEGER, length INTEGER, transport TEXT, hotel TEXT, hotel_stars INTEGER, sea INTEGER, transfer INTEGER);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS shoppings(name TEXT, cost INTEGER, length INTEGER, transport TEXT, city TEXT, store TEXT, store_count INTEGER);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS treatments(name TEXT, cost INTEGER, length INTEGER, transport TEXT, procedures INTEGER);");
        }
        catch(SQLException exc) {
            throw new TourStorageException(String.format("database initialization failed: %s",
                        exc.getMessage()));
        }
    }

    public void store(List<Tour> tours) {
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM cruises;");
            statement.executeUpdate("DELETE FROM excursions;");
            statement.executeUpdate("DELETE FROM rests;");
            statement.executeUpdate("DELETE FROM shoppings;");
            statement.executeUpdate("DELETE FROM treatments;");
            for (Tour tour : tours) {
                switch (tour.getType()) {
                    case CRUISE:
                        {
                            Cruise cur = (Cruise)tour;
                            statement.addBatch(String.format("INSERT INTO cruises (name, cost, length, room, food, city) VALUES('%s', %d, %d, '%s', '%s', '%s');",
                                        cur.getName(), cur.getCost(), cur.getLengthDays(),
                                        cur.getRoomType(), cur.getFoodType(), cur.getCity()));
                            break;
                        }
                    case EXCURSION:
                        {
                            Excursions cur = (Excursions)tour;
                            statement.addBatch(String.format("INSERT INTO excursions (name, cost, length, transport, excursion_count, excursion_cost) VALUES('%s', %d, %d, '%s', %d, %d);",
                                        cur.getName(), cur.getCost(), cur.getLengthDays(),
                                    cur.getTransport().toString(), cur.getExcursionCount(), cur.getExcursionCost()));
                        }
                        break;
                    case REST:
                        {
                            Rest cur = (Rest)tour;
                            statement.addBatch(String.format("INSERT INTO rests (name, cost, length, transport, hotel, hotel_stars, sea, transfer) VALUES('%s', %d, %d, '%s', '%s', %d, %d, %d);",
                                        cur.getName(), cur.getCost(), cur.getLengthDays(),
                                    cur.getTransport().toString(), cur.getHotelName(), cur.getHotelStars(),
                                    cur.getMetersSea(), cur.isTransfer()?1:0));
                            break;
                        }
                    case SHOPPING:
                        {
                            Shopping cur = (Shopping)tour;
                            statement.addBatch(String.format("INSERT INTO shoppings (name, cost, length, transport, city, store, store_count) VALUES('%s', %d, %d, '%s', '%s', '%s', %d);",
                                        cur.getName(), cur.getCost(), tour.getLengthDays(), tour.getTransport().toString(), cur.getCity(), cur.getStoreName(), cur.getNumberOfStores()));
                            break;
                        }
                    case TREATMENT:
                        {
                            Treatment cur = (Treatment)tour;
                            statement.addBatch(String.format("INSERT INTO treatments (name, cost, length, transport, procedures) VALUES('%s', %d, %d, '%s', %d);",
                                        cur.getName(), cur.getCost(), cur.getLengthDays(), cur.getTransport().toString(), cur.getProceduresAmount()));
                            break;
                        }
                    default:
                        throw new TourStorageException("unknown tour type");
                }
            }
            statement.executeBatch();
        }
        catch(SQLException exc) {
            throw new TourStorageException(String.format("storage error: %s", exc.getMessage()));
        }
    }

    public List<Tour> load() {
        List<Tour> result = new ArrayList<Tour>();
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from cruises;");
            while(rs.next())
            {
                result.add(new Cruise(rs.getString("name"), rs.getInt("cost"), rs.getInt("length"),
                            rs.getString("room"), rs.getString("food"), rs.getString("city")));
            }
            rs = statement.executeQuery("SELECT * from rests;");
            while(rs.next())
            {
                result.add(new Rest(rs.getString("name"), rs.getInt("cost"), rs.getInt("length"),
                            Transport.valueOf(rs.getString("transport")), rs.getString("hotel"),
                            rs.getInt("hotel_stars"), rs.getInt("sea"), rs.getInt("transfer")>0));
            }
            rs = statement.executeQuery("SELECT * from shoppings;");
            while(rs.next()) {
                result.add(new Shopping(rs.getString("name"), rs.getInt("cost"), rs.getInt("length"),
                            Transport.valueOf(rs.getString("transport")), rs.getString("city"),
                            rs.getString("store"), rs.getInt("store_count")));
            }
            rs = statement.executeQuery("SELECT * from treatments;");
            while(rs.next()) {
                result.add(new Treatment(rs.getString("name"), rs.getInt("cost"), rs.getInt("length"),
                            Transport.valueOf(rs.getString("transport")), rs.getInt("procedures")));
            }
            rs = statement.executeQuery("SELECT * from excursions;");
            while(rs.next()) {
                result.add(new Excursions(rs.getString("name"), rs.getInt("cost"), rs.getInt("length"),
                            Transport.valueOf(rs.getString("transport")), rs.getInt("excursion_count"),
                            rs.getInt("excursion_cost")));
            }
        }
        catch(SQLException exc) {
            throw new TourStorageException(String.format("storage error: %s", exc.getMessage()));
        }
        catch(IllegalArgumentException exc) {
            throw new TourStorageException(String.format("storage error: %s", exc.getMessage()));
        }
        return result;
    }
    
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:sqlite:%s", filename));
    }
};
