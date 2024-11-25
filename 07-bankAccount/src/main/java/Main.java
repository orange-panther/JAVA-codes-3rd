public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(10000);

        Thread t1 = new Thread(() -> bankAccount.withdraw(2500));
        Thread t2 = new Thread(() -> bankAccount.deposit(5000));
        Thread t3 = new Thread(() -> bankAccount.withdraw(2500));
        Thread t4 = new Thread(() -> bankAccount.withdraw(5000));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Finales Guthaben: %.0f%n", bankAccount.getBalance());
    }
}
