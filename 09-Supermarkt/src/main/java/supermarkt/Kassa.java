package supermarkt;

import java.util.Queue;

public class Kassa extends Thread {

    private int id;
    private double saldo;
    private Queue<Kunde> queue;

    private static int nextId = 1;

    public Kassa(Queue<Kunde> queue) {
        this.queue = queue;
        this.id = nextId++;
    }

    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                synchronized (queue) {
                    if(queue.isEmpty()) {
                        System.out.println("Kassa " + this.id + " wartet auf neuen Kunden");
                        queue.wait();
                    } else {
                        poll();
                        Thread.sleep(100);
                    }
                }
            }
        } catch (InterruptedException ignore) {
        }

        if(!queue.isEmpty()) {
            // arbeite Kunden ab, nachdem Kassa schließt
            synchronized (queue) {
                System.out.println("Supermarkt schließt, letzte Kunden der Kassa " + this.id + " werden abgearbeitet");
                while (!queue.isEmpty()) {
                   poll();
                }
            }
            System.out.println("Kassa schließt");
        }

    }

    private void poll() {
        Kunde polled = queue.poll();
        this.saldo += polled.getWarenwert();
        System.out.println(ConsoleColor.ANSI_GREEN + "Kassa " + this.id + ": Zahlung von " + polled.toString() + ConsoleColor.ANSI_RESET);
    }
}
