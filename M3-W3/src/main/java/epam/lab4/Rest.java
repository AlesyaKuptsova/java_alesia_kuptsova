package epam.lab4;

/**
 * Created by Alesia Kuptsova on 11/2/2015.
 */
//TODO Add Tour to name of class
public class Rest extends BaseTour {
    private Transport transport;
    public String hotelName;
    public int hotelStars;
    public int metersSea;
    public boolean transfer;

    public Rest(String name, int cost, int length, Transport transport,
            String hotelName, int hotelStars, int metersSea, boolean transfer){
        super(name, cost, length);
        this.transport = transport;
        this.hotelName = hotelName;
        this.hotelStars = hotelStars;
        this.metersSea = metersSea;
        this.transfer = transfer;
    }

    public static Tour load(String[] values) {
        try {
            return new Rest(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]),
                    Transport.valueOf(values[3]), values[4], Integer.valueOf(values[5]),
                    Integer.valueOf(values[6]), Boolean.valueOf(values[7]));
        }
        catch(IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Transport getTransport() {
        return transport;
    }

    public String toString() {
        return String.format("Cruise '%s', cost %d, length %d, hotel %s within %d meters",
                getName(), getCost(), getLengthDays(), hotelName, metersSea);
    }

    public String getHotelName(){
        return hotelName;
    }

    public int getHotelStars(){
        return hotelStars;
    }

    public int getMetersSea(){
        return metersSea;
    }

    public boolean isTransfer(){
        return transfer;
    }

    public TourType getType() {
        return TourType.REST;
    }

    public String[] store() {
        return new String[]{name, Integer.toString(cost), Integer.toString(length), transport.toString(),
            hotelName, Integer.toString(hotelStars), Integer.toString(metersSea),
            Boolean.toString(transfer)};
    }
}
