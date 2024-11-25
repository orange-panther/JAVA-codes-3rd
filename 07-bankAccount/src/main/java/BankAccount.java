import java.util.concurrent.TimeUnit;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    // Einzahlung auf Konto
    public void deposit(double amount) {
        try {
            System.out.println("Einzahlung: Warten auf System...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (this) {
            double origBalance = this.balance;
            this.balance += amount;
            System.out.printf("Alter Kontostand: %.0f, Einzahlung: %.0f, Neuer Kontostand: %.0f%n",
                    origBalance,
                    amount,
                    balance);
        }
    }

    // Abheben vom Konto
    public void withdraw(double amount) {
        try {
            System.out.println("Auszahlung: Warten auf System...");
            TimeUnit.SECONDS.sleep(1); // wie Thread.sleep(1000)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (this) {
            double origBalance = this.balance;
            if (amount <= this.balance) {
                this.balance -= amount;
                System.out.printf("Alter Kontostand: %.0f, Auszahlung: %.0f, Neuer Kontostand: %.0f%n",
                        origBalance,
                        amount,
                        this.balance);
            } else {
                System.out.printf("Alter Kontostand: %.0f, Auszahlung: %.0f, Guthaben nicht ausreichend%n",
                        origBalance,
                        amount);
            }
        }
    }
}
