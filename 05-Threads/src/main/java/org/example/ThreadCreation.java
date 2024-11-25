package org.example;

public class ThreadCreation {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        customThread.start();

        Runnable myRunnable = () -> {
            for (int i = 1; i <= 8; i++) {
                System.out.print(" 2 ");
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
         Thread myThread = new Thread(myRunnable);
         myThread.start();

         Thread myThread2 = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (int i = 1; i <= 4; i++) {
                     System.out.print(" 3 ");
                     try {
                         Thread.sleep(600);
                     } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                     }
                 }
             }
         });

         myThread2.start();

        try {
            // Hier gehen wir erst weiter wenn alle diese Threads fertig gearbeitet haben
            customThread.join();
            myThread.join();
            myThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 7; i++) {
            System.out.print(" 0 ");
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
