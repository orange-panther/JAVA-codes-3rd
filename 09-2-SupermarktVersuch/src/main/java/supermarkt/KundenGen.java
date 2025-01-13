package supermarkt;

import java.util.LinkedList;
import java.util.Queue;

public class KundenGen extends Thread {
    private Queue<Kunde> queue;

    public KundenGen(Queue<Kunde> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Kundengenerierung startet");

        while(!isInterrupted()) {
            try {
                Kunde newKunde = Kunde.einkaufen();
                synchronized (queue) {
                    queue.offer(newKunde);
                    queue.notifyAll();
                }
                Thread.sleep((long)(Math.random() * 100));

            } catch (InterruptedException ignore) {
                break;
            }
        }

        System.out.println("Kundengenerierung endet");
    }
}
