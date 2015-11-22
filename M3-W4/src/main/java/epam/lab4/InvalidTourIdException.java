package epam.lab4;

class InvalidTourIdException extends TourManagerException {
    private int id;

    public InvalidTourIdException(int id) {
        this.id = id;
    }

    public InvalidTourIdException(int id, String message) {
        super(message);
        this.id = id;
    }
}
