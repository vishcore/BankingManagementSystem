package banking;

/**
 * SavingsAccount inherits common functionality from Account.
 *
 * Inheritance: SavingsAccount IS-A Account.
 * Method overriding: calculateInterest() is implemented differently.
 */
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 4.0;

    public SavingsAccount(int accountNumber, Customer holder, double balance) {
        super(accountNumber, holder, balance);
    }

    // O(1) time and O(1) extra space.
    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE / 100.0;
    }
}
