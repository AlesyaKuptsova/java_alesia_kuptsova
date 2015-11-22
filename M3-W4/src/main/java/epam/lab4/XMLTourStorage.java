package epam.lab4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.xstream.XStream;

class XMLTourStorage implements TourStorage {
    private String filename;

    public XMLTourStorage(String filename) {
        this.filename = filename;
    }

    public void store(List<Tour> tours) {
        XStream serializer = createSerializer();
        String data = serializer.toXML(tours);
        try {
            FileWriter fw = new FileWriter(new File(filename));
            fw.write(data);
            fw.close();
        }
        catch(IOException exc) {
            throw new TourStorageException(String.format("store failed: %s", exc.getMessage()));
        }
    }

    public List<Tour> load() {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get(filename)));
        }
        catch(IOException exc) {
            throw new TourStorageException(String.format("load failed: %s", exc.getMessage()));
        }
        return (List<Tour>)createSerializer().fromXML(data);
    }

    private static XStream createSerializer() {
        XStream xstream = new XStream();
        xstream.alias("cruise", Cruise.class);
        xstream.alias("treatment", Treatment.class);
        xstream.alias("shopping", Shopping.class);
        xstream.alias("rest", Rest.class);
        xstream.alias("excursions", Excursions.class);
        return xstream;
    }
}
