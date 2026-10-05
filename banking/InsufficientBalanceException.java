package banking;

/**
 * Custom exception when an account cannot cover a withdrawal.
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
