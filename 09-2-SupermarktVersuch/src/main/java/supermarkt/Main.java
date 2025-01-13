package supermarkt;

import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Kunde> queue = new LinkedList<>();

        // Starte das Generieren von Kunden
        KundenGen kundenGen = new KundenGen(queue);
        kundenGen.start();

        // Öffne eine Kassa
        Kassa kassa1 = new Kassa(queue);
        kassa1.start();

        // Öffne eine zweite Kassa
        Kassa kassa2 = new Kassa(queue);
        kassa2.start();

        // warte Zeit (Öffnungszeit)
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            kundenGen.interrupt();
            kundenGen.join();

            kassa1.interrupt();
            kassa2.interrupt();

            kassa1.join();
            kassa2.join();
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        System.out.printf("Saldo Kassa 1: %7.2f EUR\n", kassa1.getSaldo());
        System.out.printf("Saldo Kassa 2: %7.2f EUR\n", kassa2.getSaldo());
        System.out.println("Supermarkt ist geschlossen");
    }
}
