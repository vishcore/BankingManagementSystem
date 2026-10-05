package banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores one transaction record.
 *
 * Demonstrates method overloading through processTransaction().
 */
public class Transaction {
    private double amount;
    private String type;
    private String description;
    private final LocalDateTime dateTime;

    public Transaction() {
        this.dateTime = LocalDateTime.now();
    }

    public Transaction(double amount, String type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Overloaded method 1.
     *
     * Time: O(1)
     * Space: O(1)
     */
    public void processTransaction(double amount) {
        this.amount = amount;
        this.type = "GENERAL";
    }

    /**
     * Overloaded method 2.
     *
     * Time: O(1)
     * Space: O(1)
     */
    public void processTransaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getFormattedDetails() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%-20s %-15s ₹%-10.2f %s",
                dateTime.format(formatter),
                type,
                amount,
                description);
    }
}
