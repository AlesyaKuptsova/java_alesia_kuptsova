package epam.lab4;

//TODO Where is a customisation of exception?
class TourManagerException extends RuntimeException {
    public TourManagerException() {
    }

    public TourManagerException(String message) {
        super(message);
    }
}
