package personal;
import java.util.Scanner;
class BankAccount 
{
    private double balance;
    public BankAccount(double initialBalance) 
    {
        balance = initialBalance;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void deposit(double amount) 
    {
        balance += amount;
    }

    public boolean withdraw(double amount) 
    {
        if (amount <= balance) 
        {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM 
{
    private BankAccount account;

    public ATM(BankAccount account) 
    {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() 
    {
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    public void checkBalance() 
    {
        System.out.println("Your account balance is: Rs." + account.getBalance());
    }

    public void deposit(double amount) 
    {
        account.deposit(amount);
        System.out.println("Deposit of Rs." + amount + " successful.");
        checkBalance();
    }

    public void withdraw(double amount) 
    {
        if (account.withdraw(amount))
        {
            System.out.println("Withdrawal of Rs." + amount + " successful.");
            checkBalance();
        }
        else 
        {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }
}

public class ATM_Interface
{
    public static void main(String[] args) 
    {
        BankAccount userAccount = new BankAccount(1000.0); 
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
