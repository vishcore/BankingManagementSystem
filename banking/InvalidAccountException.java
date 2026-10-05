package banking;

/**
 * Custom exception for invalid customer/account details.
 */
public class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}
