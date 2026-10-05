package banking;

import java.time.LocalDateTime;

/**
 * Abstract parent class for all account types.
 *
 * Encapsulation: balance and account information are private.
 * Composition: every Account creates and owns one TransactionHistory.
 *
 * Time:
 * deposit/withdraw/checkBalance = O(1)
 * add transaction = O(1) amortized
 *
 * Space:
 * O(T) per account, where T = number of transactions.
 */
public abstract class Account {
    private final int accountNumber;
    private double balance;
    private final Customer accountHolder;

    // TransactionHistory belongs to the Account -> composition.
    private final TransactionHistory transactionHistory;

    protected Account(int accountNumber, Customer accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.transactionHistory = new TransactionHistory();
    }

    /**
     * synchronized protects the shared balance when multiple threads
     * access the SAME Account object.
     *
     * Time: O(1) excluding thread scheduling.
     */
    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be greater than zero.");
        }

        balance += amount;

        Transaction transaction = new Transaction();
        // Method overloading: processTransaction(amount, type)
        transaction.processTransaction(amount, "DEPOSIT");
        transaction.setDescription("Money deposited");
        transactionHistory.addTransaction(transaction);
    }

    /**
     * synchronized prevents a race condition during withdrawal.
     *
     * Time: O(1) excluding thread scheduling.
     */
    public synchronized void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal must be greater than zero.");
        }

        if (!canWithdraw(amount)) {
            throw new InsufficientBalanceException(
                    "Insufficient balance for withdrawal of ₹" + amount);
        }

        balance -= amount;

        Transaction transaction = new Transaction();
        // Another overloaded call.
        transaction.processTransaction(amount, "WITHDRAWAL");
        transaction.setDescription("Money withdrawn");
        transactionHistory.addTransaction(transaction);
    }

    // O(1) time and O(1) extra space.
    protected boolean canWithdraw(double amount) {
        return amount <= balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountType() {
        return getClass().getSimpleName();
    }

    // Runtime polymorphism: subclasses provide their own implementation.
    public abstract double calculateInterest();
}
