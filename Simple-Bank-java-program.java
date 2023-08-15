import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// SIMPLE BANK INTERFACE
//THIS PROGRAM MAINTAINS A COMMAND LINE INTERFACE OF A BANK
//CREATE A BANK ACCOUNT, DEPOSIT IN EXISTING BANK ACCOUNT, WITHDRAW FROM EXISTING ACCOUNT
//IT IS NOT CONNECTION TO ANY DATABASE SO CLOSING THE CODE RESULTS IN DELETION OF ANY EXISTING ACCOUNTS AND ITS DATA


class BankAccount {
    private double balance;
    private String name;


    public BankAccount(String name, double amount) {
        this.name = name;
        this.balance = amount;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Current balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount+1000) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: $" + balance);
        } else {
            System.out.println("Insufficient balance. Current balance: $" + balance);
        }
    }
}

public class rizzBank {
    public static void main(String[] args) {
        Map<String, BankAccount> accounts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("------------- WELCOME TO RIZZ BANK -----------------");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter base deposit amount (1000): $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    BankAccount account = new BankAccount(name, amount);
                    accounts.put(name, account);
                    System.out.println("Account created successfully.");
                }
                case 2 -> {
                    System.out.print("Enter account holder name: ");
                    String depositName = scanner.nextLine();
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    BankAccount depositAccount = accounts.get(depositName);
                    if (depositAccount != null) {
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter account holder name: ");
                    String withdrawName = scanner.nextLine();
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    BankAccount withdrawAccount = accounts.get(withdrawName);
                    if (withdrawAccount != null) {
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                }
                case 4 -> {
                    System.out.println("Thank you for using the bank program.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

