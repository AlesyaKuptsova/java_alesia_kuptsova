package epam.lab4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvTourStorage implements TourStorage {
    private String filename;

    public CsvTourStorage(String filename) {
        this.filename = filename;
    }

    public void store(List<Tour> tours) {
        List<String> lines = new ArrayList<String>();
        for(Tour tour : tours) {
            String[] data = tour.store();
            String[] values = new String[data.length+1];
            values[0] = tour.getType().getType();
            for(int i = 0; i < data.length; i++) {
                values[i+1] = data[i];
            }
            lines.add(join(values, ","));
        }
        try {
            Files.write(Paths.get(filename), lines, Charset.defaultCharset());
        }
        catch(IOException exc) {
            throw new TourStorageException(exc.getMessage());
        }
    }

    public List<Tour> load() {
        List<Tour> result = new ArrayList<Tour>();
        try {
            for (String line : Files.readAllLines(Paths.get(filename), Charset.defaultCharset())) {
                String[] values = line.split(",");
                if(values.length == 0)
                    throw new TourStorageException("invalid tour file");
                String[] data = new String[values.length-1];

                //TODO It will be better to use keys for values
                for(int i =1; i < values.length; ++i) {
                    data[i-1] = values[i];
                }
                if(values[0].equals(TourType.CRUISE.getType())) {
                    result.add(Cruise.load(data));
                }
                else if(values[0].equals(TourType.SHOPPING.getType())) {
                    result.add(Shopping.load(data));
                }
                else if(values[0].equals(TourType.EXCURSION.getType())) {
                    result.add(Excursions.load(data));
                }
                else if(values[0].equals(TourType.TREATMENT.getType())) {
                    result.add(Treatment.load(data));
                }
                else if(values[0].equals(TourType.REST.getType())) {
                    result.add(Rest.load(data));
                }
                else {
                    throw new TourStorageException("invalid tour type in storage");
                }
            }
        }
        catch(IOException exc) {
            throw new TourStorageException(exc.getMessage());
        }
        catch(IllegalArgumentException exc) {
            throw new TourStorageException(exc.getMessage());
        }
        return result;
    }

    private static String join(String[] values, String delim) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < values.length; i++) {
            builder.append(values[i]);
            if(i+1 < values.length) {
                builder.append(delim);
            }
        }
        return builder.toString();
    }
}
