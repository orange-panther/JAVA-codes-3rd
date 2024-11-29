package supermarkt;

import java.util.Queue;

public class KundenGen extends Thread {

    private Queue<Kunde> queue;

    public KundenGen(Queue<Kunde> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Kunde k;
        try {
            while(!isInterrupted()) {
                synchronized(queue) {
                    k = Kunde.einkaufen();
                    queue.offer(k);
                    queue.notifyAll();// falls sich andere Threads mit Lock
                    // auf queue im Wartezustand befinden, dann werden sie
                    // nun informiert, dass es einen neuen Kunden gibt
                }

                Thread.sleep((long)(Math.random() * 100));
            }
        } catch (InterruptedException ignore) {
        }
        System.out.println("Kundengenerierung beendet");
    }

}
