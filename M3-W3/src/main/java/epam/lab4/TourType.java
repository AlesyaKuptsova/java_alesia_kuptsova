package epam.lab4;

public enum TourType {
    CRUISE("cruise"),
    SHOPPING("shopping"),
    EXCURSION("excursion"),
    TREATMENT("treatment"),
    REST("rest");

    String type;

    TourType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
}
