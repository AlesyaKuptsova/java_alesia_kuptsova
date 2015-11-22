package epam.lab4;

public class InputException extends RuntimeException {
    private String input;

    public InputException(String input) {
        this.input = input;
    }

    public InputException(String input, String message) {
        super(message);
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
