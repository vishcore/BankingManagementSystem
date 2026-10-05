package banking;

import java.util.Scanner;

/**
 * Main class: terminal-based menu of the Banking Management System.
 *
 * Overall menu operation: O(1) per choice, excluding the selected operation.
 * Space: O(1) local space.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();
        boolean running = true;

        while (running) {
            System.out.println("\n==================================================");
            System.out.println("             BANKING MANAGEMENT SYSTEM");
            System.out.println("==================================================");
            System.out.println("1. Create Customer");
            System.out.println("2. Create Savings Account");
            System.out.println("3. Create Checking Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Transfer Money");
            System.out.println("7. Check Account Balance");
            System.out.println("8. View Transaction History");
            System.out.println("9. View Customer Details");
            System.out.println("10. Calculate Interest");
            System.out.println("11. Concurrent Transaction Demo");
            System.out.println("12. Exit");
            System.out.println("==================================================");

            System.out.print("Enter your choice: ");
            int choice = readInt();

            try {
                switch (choice) {
                    case 1:
                        createCustomer(bank);
                        break;
                    case 2:
                        createSavingsAccount(bank);
                        break;
                    case 3:
                        createCheckingAccount(bank);
                        break;
                    case 4:
                        deposit(bank);
                        break;
                    case 5:
                        withdraw(bank);
                        break;
                    case 6:
                        transfer(bank);
                        break;
                    case 7:
                        checkBalance(bank);
                        break;
                    case 8:
                        showHistory(bank);
                        break;
                    case 9:
                        showCustomer(bank);
                        break;
                    case 10:
                        calculateInterest(bank);
                        break;
                    case 11:
                        concurrentDemo(bank);
                        break;
                    case 12:
                        running = false;
                        System.out.println("Thank you for using the Banking Management System.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InvalidAccountException | InsufficientBalanceException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input or unexpected problem.");
            }
        }

        sc.close();
    }

    private static void createCustomer(Bank bank) {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        bank.createCustomer(id, name, phone, email);
    }

    private static void createSavingsAccount(Bank bank) {
        System.out.print("Enter Customer ID: ");
        String customerId = sc.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double amount = readDouble();

        bank.createSavingsAccount(customerId, amount);
    }

    private static void createCheckingAccount(Bank bank) {
        System.out.print("Enter Customer ID: ");
        String customerId = sc.nextLine();
        System.out.print("Enter Initial Deposit: ");
        double amount = readDouble();

        bank.createCheckingAccount(customerId, amount);
    }

    private static void deposit(Bank bank) throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        int accountNumber = readInt();
        System.out.print("Enter Deposit Amount: ");
        double amount = readDouble();

        bank.deposit(accountNumber, amount);
    }

    private static void withdraw(Bank bank)
            throws InvalidAccountException, InsufficientBalanceException {
        System.out.print("Enter Account Number: ");
        int accountNumber = readInt();
        System.out.print("Enter Withdrawal Amount: ");
        double amount = readDouble();

        bank.withdraw(accountNumber, amount);
    }

    private static void transfer(Bank bank)
            throws InvalidAccountException, InsufficientBalanceException {
        System.out.print("Enter Sender Account Number: ");
        int from = readInt();
        System.out.print("Enter Receiver Account Number: ");
        int to = readInt();
        System.out.print("Enter Transfer Amount: ");
        double amount = readDouble();

        bank.transfer(from, to, amount);
    }

    private static void checkBalance(Bank bank) throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        int accountNumber = readInt();

        Account account = bank.findAccount(accountNumber);
        System.out.printf("Current Balance: ₹%.2f%n", account.getBalance());
    }

    private static void showHistory(Bank bank) throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        int accountNumber = readInt();

        Account account = bank.findAccount(accountNumber);
        account.getTransactionHistory().displayHistory();
    }

    private static void showCustomer(Bank bank) throws InvalidAccountException {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();

        Customer customer = bank.findCustomer(id);
        customer.displayDetails();
    }

    private static void calculateInterest(Bank bank) throws InvalidAccountException {
        System.out.print("Enter Account Number: ");
        int accountNumber = readInt();

        Account account = bank.findAccount(accountNumber);
        double interest = account.calculateInterest();

        System.out.printf("Calculated Interest: ₹%.2f%n", interest);
    }

    private static void concurrentDemo(Bank bank)
            throws InvalidAccountException {
        System.out.print("Enter Account Number for demo: ");
        int accountNumber = readInt();
        Account account = bank.findAccount(accountNumber);

        System.out.println("\n--- Concurrent Transaction Demo ---");
        System.out.printf("Balance before threads: ₹%.2f%n", account.getBalance());
        System.out.println("Two threads will access the SAME Account object.");
        System.out.println("Synchronization protects the shared balance.");

        Thread t1 = new TransactionProcessor(account, 1000, false);
        Thread t2 = new TransactionProcessor(account, 1000, false);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread execution was interrupted.");
        }

        System.out.printf("Balance after threads: ₹%.2f%n", account.getBalance());
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine());
                if (value < 0) {
                    System.out.print("Amount cannot be negative. Enter again: ");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }
}
