package org.example;

public class Main {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        Thread green = new Thread (
                () -> stopWatch.countDown(7),
                ThreadColor.ANSI_GREEN.name() //optionaler Parameter
        );

        Thread red = new Thread (
                () -> stopWatch.countDown(7),
                ThreadColor.ANSI_RED.name() //optionaler Parameter
        );

        Thread blue = new Thread (
                () -> stopWatch.countDown(7),
                ThreadColor.ANSI_BLUE.name()
        );

        green.start();
        red.start();
        blue.start();
    }
}

class StopWatch {
    // private int i;

    public StopWatch() {

    }

    public void countDown(int startCount) {
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName);
        } catch (IllegalArgumentException ignore) {

        }

        String color = threadColor.color();

        for (int i = startCount; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("%s%s: %d%n", color, threadName, i);
        }
    }
}