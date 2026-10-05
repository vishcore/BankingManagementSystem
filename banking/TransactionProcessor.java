package banking;

/**
 * Demonstrates multithreading with a shared Account object.
 *
 * Both threads receive the SAME Account reference.
 * The Account's synchronized deposit/withdraw methods protect
 * the shared balance from race conditions.
 *
 * Time: O(1) transaction operation, excluding scheduling.
 * Space: O(1) extra space per thread.
 */
public class TransactionProcessor extends Thread {
    private final Account account;
    private final double amount;
    private final boolean deposit;

    public TransactionProcessor(Account account, double amount, boolean deposit) {
        this.account = account;
        this.amount = amount;
        this.deposit = deposit;
    }

    @Override
    public void run() {
        try {
            System.out.println(
                    Thread.currentThread().getName()
                    + " started transaction on Account "
                    + account.getAccountNumber());

            if (deposit) {
                account.deposit(amount);
                System.out.println(
                        Thread.currentThread().getName()
                        + " deposited ₹" + amount);
            } else {
                account.withdraw(amount);
                System.out.println(
                        Thread.currentThread().getName()
                        + " withdrew ₹" + amount);
            }

        } catch (InsufficientBalanceException e) {
            System.out.println(
                    Thread.currentThread().getName()
                    + " failed: " + e.getMessage());
        }
    }
}
