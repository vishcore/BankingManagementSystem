BANKING MANAGEMENT SYSTEM
==========================

Language:
Java

Type:
Terminal / Command Line Project

Concepts demonstrated:
- Classes and Objects
- Constructors
- Encapsulation
- this and super
- Inheritance
- Method Overloading
- Method Overriding
- Runtime Polymorphism
- Abstraction
- Association
- Aggregation
- Composition
- Custom Exception Handling
- Packages
- Multithreading
- Shared Account Object
- Synchronization

PROJECT STRUCTURE
-----------------
banking/
    Main.java
    Bank.java
    Customer.java
    Account.java
    SavingsAccount.java
    CheckingAccount.java
    Transaction.java
    TransactionHistory.java
    InvalidAccountException.java
    InsufficientBalanceException.java
    TransactionProcessor.java

HOW TO RUN
----------
1. Open terminal inside the folder containing the banking folder.

2. Compile:
   javac banking/*.java

3. Run:
   java banking.Main

IMPORTANT MULTITHREADING IDEA
-----------------------------
The concurrent transaction demo creates two threads that receive the
same Account object. This is a shared object in memory.

The Account class uses synchronized deposit() and withdraw() methods
so only one thread can modify the balance at a time.

This prevents a race condition.

COMPLEXITY SUMMARY
------------------
Customer creation: O(1) amortized
Account creation: O(C) because the customer is searched
Find account: O(A)
Find customer: O(C)
Deposit/withdraw after finding account: O(1)
Transfer: O(A) because accounts are searched
Display transaction history: O(T)
Add transaction: O(1) amortized
Calculate interest: O(1)
Concurrent transaction operation: O(1), excluding scheduling

Space:
Customers: O(C)
Accounts: O(A)
Transactions: O(T)
Overall stored project data: O(C + A + T)

C = number of customers
A = number of accounts
T = total number of transaction records
