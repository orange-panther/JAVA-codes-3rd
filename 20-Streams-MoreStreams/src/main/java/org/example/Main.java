package org.example;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nPrimzahlen");
        System.out.println("=========================================================================================\n");

        System.out.println("Drucken Sie alle geraden Zahlen von 2 bis 10, getrennt durch Leerzeichen");
        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Drucken Sie alle Primzahlen von 1 – 100, getrennt durch Leerzeichen.");
        Stream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Main::isPrime)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Drucken Sie die ersten 100 Primzahlen, getrennt durch Leerzeichen.");
        Stream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(100)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Drucken Sie die Summe ersten 10 Primzahlen.");
        int sum = Stream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(10)
                .mapToInt(number -> number)
                .sum();
        System.out.println("Summe: " + sum + "\n");

        System.out.println("Nutzen Sie iterate, um ein int-Array mit den Primzahlen von 1 bis 59 zu erzeugen. Drucken Sie diese.");
        Stream.iterate(1, n -> n + 1)
                .limit(59)
                .filter(Main::isPrime)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Nutzen Sie range, um ein int-Array mit den Primzahlen von 1 bis 59 zu erzeugen. Drucken Sie diese.");
        int[] primes = IntStream.range(1, 60)
                .filter(Main::isPrime)
                .toArray();
        for (int prime : primes) {
            System.out.printf("%d ", prime);
        }
        System.out.println("\n");

        System.out.println("Nutzen Sie rangeClosed, um ein int-Array mit den Primzahlen von 1 bis 59 zu erzeugen. Drucken Sie diese");
        primes = IntStream.rangeClosed(1, 59)
                .filter(Main::isPrime)
                .toArray();
        for (int prime : primes) {
            System.out.printf("%d ", prime);
        }
        System.out.println("\n");

        System.out.println("\nZufallszahlen");
        System.out.println("=========================================================================================\n");
        Random random = new Random();

        System.out.println("Drucken Sie 10 Zufallszahlen von 1 bis 100 (Duplikate sind erlaubt). Nutzen Sie Math.random().");
        Stream.generate(() -> (int) (Math.random() * 100) + 1)
                .limit(10)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Drucken Sie 10 Zufallszahlen von 1 bis 100 (Duplikate sind erlaubt). Nutzen Sie die Random-Klasse.");
        Stream.generate(() -> random.nextInt(1, 100))
                .limit(10)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Drucken Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate).");
        Stream.generate(() -> random.nextInt(1, 100))
                .distinct()
                .limit(10)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Drucken Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate, in aufsteigender Reihenfolge sortiert).");
        Stream.generate(() -> random.nextInt(1, 100))
                .distinct()
                .limit(10)
                .sorted((n1, n2) -> n1 - n2)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Erzeugen Sie 10 Zufallszahlen von 1 bis 100 (ohne Duplikate) und drucken Sie nur jene, welche unter 50 sind in absteigender Reihenfolge.");
        Stream.generate(() -> random.nextInt(1, 100))
                .distinct()
                .limit(10)
                .filter(n -> n < 50)
                .sorted((n1, n2) -> n2 - n1)
                .forEach(n -> System.out.printf("%d ", n));
        System.out.println("\n");

        System.out.println("Erzeugen Sie 1000 Zufallszahlen von 1 bis 100 und geben Sie aus, wie häufig jede Zahl vorkommt.");
        Stream.generate(() -> (int) (Math.random() * 100 + 1))
                .limit(1000)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("\n");

        System.out.println("Erzeugen Sie 1000 Zufallszahlen von 1 bis 100 und drucken Sie die 3 am häufigsten vorkommenden Zahlen.");
        Stream.generate(() -> (int) (Math.random() * 100 + 1))
                .limit(1000)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .forEach((e) -> System.out.println(e.getKey() + ": " + e.getValue()));
        System.out.println("\n");

        System.out.println("\nAlphabet");
        System.out.println("=========================================================================================\n");

        System.out.println("Erzeugen Sie einen String, der alle Großbuchstaben (A bis Z) enthält. Drucken Sie diesen.");
        String alp = IntStream.rangeClosed('A', 'Z')
                .mapToObj(c ->  "" + (char) c)
                .reduce("", String::concat);
        System.out.println(alp);

    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}