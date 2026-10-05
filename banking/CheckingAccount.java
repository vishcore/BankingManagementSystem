package banking;

/**
 * Checking account with an overdraft limit.
 */
public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 2000.0;

    public CheckingAccount(int accountNumber, Customer holder, double balance) {
        super(accountNumber, holder, balance);
    }

    // Overriding parent behavior to support overdraft.
    // O(1) time and O(1) extra space.
    @Override
    protected boolean canWithdraw(double amount) {
        return amount <= getBalance() + OVERDRAFT_LIMIT;
    }

    // Checking account does not earn normal savings interest.
    // O(1) time and O(1) extra space.
    @Override
    public double calculateInterest() {
        return 0.0;
    }
}
