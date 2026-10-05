package banking;

import java.util.ArrayList;

/**
 * Bank manages all customers and accounts.
 *
 * Search operations are O(n) because ArrayList is searched linearly.
 * Adding a customer/account is amortized O(1).
 * Space: O(C + A), where C = customers and A = accounts.
 */
public class Bank {
    private final ArrayList<Customer> customers;
    private final ArrayList<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
        nextAccountNumber = 1001;
    }

    // O(1) amortized time, O(1) extra space.
    public void createCustomer(String id, String name, String phone, String email) {
        if (findCustomerInternal(id) != null) {
            System.out.println("Customer ID already exists.");
            return;
        }

        customers.add(new Customer(id, name, phone, email));
        System.out.println("Customer created successfully.");
    }

    // O(C + A) because it searches for the customer and creates/links an account.
    public void createSavingsAccount(String customerId, double amount) {
        Customer customer = findCustomerInternal(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (amount < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        SavingsAccount account =
                new SavingsAccount(nextAccountNumber++, customer, amount);

        accounts.add(account);
        customer.addAccount(account);

        System.out.println("Savings Account created successfully.");
        System.out.println("Account Number: " + account.getAccountNumber());
    }

    // O(C + A), O(1) extra space.
    public void createCheckingAccount(String customerId, double amount) {
        Customer customer = findCustomerInternal(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (amount < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        CheckingAccount account =
                new CheckingAccount(nextAccountNumber++, customer, amount);

        accounts.add(account);
        customer.addAccount(account);

        System.out.println("Checking Account created successfully.");
        System.out.println("Account Number: " + account.getAccountNumber());
    }

    // O(A) search + O(1) account operation = O(A).
    public void deposit(int accountNumber, double amount)
            throws InvalidAccountException {
        Account account = findAccount(accountNumber);

        account.deposit(amount);
        System.out.println("Deposit successful.");
        System.out.printf("New Balance: ₹%.2f%n", account.getBalance());
    }

    // O(A) search + O(1) account operation = O(A).
    public void withdraw(int accountNumber, double amount)
            throws InvalidAccountException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);

        account.withdraw(amount);
        System.out.println("Withdrawal successful.");
        System.out.printf("New Balance: ₹%.2f%n", account.getBalance());
    }

    // O(A) for two account searches; balance operations are O(1).
    public void transfer(int fromNumber, int toNumber, double amount)
            throws InvalidAccountException, InsufficientBalanceException {

        if (fromNumber == toNumber) {
            throw new InvalidAccountException(
                    "Sender and receiver accounts cannot be the same.");
        }

        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);

        from.withdraw(amount);
        to.deposit(amount);

        from.getTransactionHistory().addTransaction(
                new Transaction(amount, "TRANSFER OUT", "To Account " + toNumber));
        to.getTransactionHistory().addTransaction(
                new Transaction(amount, "TRANSFER IN", "From Account " + fromNumber));

        System.out.println("Transfer successful.");
    }

    // O(A) time, O(1) extra space.
    public Account findAccount(int accountNumber)
            throws InvalidAccountException {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        throw new InvalidAccountException(
                "Account " + accountNumber + " does not exist.");
    }

    // O(C) time, O(1) extra space.
    public Customer findCustomer(String customerId)
            throws InvalidAccountException {
        Customer customer = findCustomerInternal(customerId);

        if (customer == null) {
            throw new InvalidAccountException(
                    "Customer " + customerId + " does not exist.");
        }

        return customer;
    }

    // O(C) time, O(1) extra space.
    private Customer findCustomerInternal(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}
