package banking;

import java.util.ArrayList;

/**
 * Stores all transactions belonging to one Account.
 *
 * This object is created inside Account, demonstrating composition.
 *
 * addTransaction: O(1) amortized
 * displayHistory: O(T)
 * Space: O(T)
 */
public class TransactionHistory {
    private final ArrayList<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    // O(1) amortized time.
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // O(T) time because every transaction is displayed.
    public void displayHistory() {
        System.out.println("\n================ TRANSACTION HISTORY ================");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.printf(
                "%-20s %-15s %-14s %s%n",
                "Date & Time", "Type", "Amount", "Description");

        System.out.println(
                "-------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getFormattedDetails());
        }
    }

    public int size() {
        return transactions.size();
    }
}
