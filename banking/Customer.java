package banking;

import java.util.ArrayList;

/**
 * Represents a bank customer.
 *
 * A Customer can have multiple Account objects.
 * This demonstrates aggregation.
 *
 * Space per customer: O(Ac), where Ac = accounts owned by that customer.
 */
public class Customer {
    private final String customerId;
    private String name;
    private String phone;
    private String email;
    private final ArrayList<Account> accounts;

    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    // O(1) amortized time.
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // O(Ac) time, because all accounts may be displayed.
    public void displayDetails() {
        System.out.println("\n----------------------------------------");
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + name);
        System.out.println("Phone       : " + phone);
        System.out.println("Email       : " + email);
        System.out.println("Accounts:");

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println(
                        "  " + account.getAccountNumber()
                        + " - " + account.getAccountType()
                        + " - ₹" + String.format("%.2f", account.getBalance()));
            }
        }
        System.out.println("----------------------------------------");
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
