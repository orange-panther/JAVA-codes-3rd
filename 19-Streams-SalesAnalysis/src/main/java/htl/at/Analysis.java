package htl.at;

import org.w3c.dom.ls.LSOutput;

import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analysis {
    public static void main(String[] args) {
        List<Sale> sales = SaleMocks.getSaleMocks();

        // a) Wie viele Verkäufe wurden im Jänner 2024 getätigt und was war der Gesamtumsatz?
        System.out.println("=== a) Wie viele Verkäufe wurden im Jänner 2024?");
        taskA_AnzahlVerkaeufe(sales);

        // b) Welche Verkäufe von Artikel der Kategorie "Lights" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz
        System.out.println("=== b) Welche Verkäufe von Artikel der Kategorie \"Lights\" wurden im Jänner 2024 nach Italien getätigt, sortiert nach Umsatz?");
        taskB_Lights_Italien(sales);

        // c) Welche 3 Verkäufe haben den höchsten Umsatz?
        System.out.println("=== c) Welche 3 Verkäufe haben im Jänner 2024 den höchsten Umsatz?");
        taskC_Top3Umsatz(sales);

        // d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart "Advance Payment" nach Ungarn?
        System.out.println("=== d) Gab es im Jänner 2024 Verkäufe mit der Zahlungsart \"Advance Payment\" nach Ungarn?");
        taskD_AdvancePayment_Ungarn(sales);

        // e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?
        System.out.println("=== e) Wie hoch war der Umsatz im Jänner 2024, gruppiert nach Ländern?");
        taskE_Umsatz_NachLaender(sales);

        // f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?
        System.out.println("=== f) Welche Kreditkarten wurden im Jänner 2024 in Deutschland verwendet?");
        taskF_Kreditkarten_Deutschland(sales);

        // g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?
        System.out.println("=== g) Wie hoch ist der durchschnittliche Umsatz pro Verkauf im Jänner 2024?");
        taskG_DurchschnittlicherUmsatz(sales);

        // h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!
        System.out.println("=== h) Geben Sie 10 zufällige Verkäufe aus Österreich im Jänner 2024 aus, sortiert nach Umsatz!");
        taskH_10ZufaelligeVerkaeufe_Oesterreich(sales);

    }

    private static void taskA_AnzahlVerkaeufe(List<Sale> sales) {
        long count = sales.stream()
                .filter(Analysis::isJanuary2024)
                .count();
        System.out.println("Anzahl Verkäufe im Jänner 2024: " + count);

        double sum = sales.stream()
                .filter(Analysis::isJanuary2024)
                .mapToDouble(sale -> sale.amount())
                .sum();
        System.out.println("Gesamtumsatz Jänner 2024: " + sum + " EUR");
    }

    private static void taskB_Lights_Italien(List<Sale> sales) {
        sales.stream()
                .filter(Analysis::isJanuary2024)
                .filter(sale -> sale.category() == "Lights")
                .filter(sale -> sale.country() == "Italy")
                .sorted((s1, s2) -> Double.compare(s1.amount(), s2.amount()))
                .forEach(System.out::println);
    }

    private static void taskC_Top3Umsatz(List<Sale> sales) {
        sales.stream()
                .filter(Analysis::isJanuary2024)
                .sorted((s1, s2) -> Double.compare(s2.amount(), s1.amount()))
                .limit(3)
                .forEach(System.out::println);
    }

    private static void taskD_AdvancePayment_Ungarn(List<Sale> sales) {
        boolean contains = sales.stream()
                .filter(Analysis::isJanuary2024)
                .anyMatch(sale -> sale.payment() == "Advance Payment" && sale.country() == "Hungary");
        System.out.println(contains ? "ja" : "nein");
    }

    private static void taskE_Umsatz_NachLaender(List<Sale> sales) {
        sales.stream()
                .filter(Analysis::isJanuary2024)
                .collect(Collectors.groupingBy(Sale::country, Collectors.summingDouble(Sale::amount)))
                .forEach((c, s) -> System.out.printf("%-15s EUR %7.2f%n", c, s));

    }

    private static void taskF_Kreditkarten_Deutschland(List<Sale> sales) {
        sales.stream()
                .filter(Analysis::isJanuary2024)
                .filter(sale -> sale.country() == "Germany")
                .filter(sale -> sale.payment().startsWith("Credit Card")) // contains gäbs auch
                .map(sale -> sale.payment())
                .distinct()
                .forEach(System.out::println);
    }

    private static void taskG_DurchschnittlicherUmsatz(List<Sale> sales) {
        double avg = sales.stream()
                .filter(Analysis::isJanuary2024)
                .mapToDouble(Sale::amount)
                .average()
                .orElse(0);

        System.out.printf("Average: EUR %.2f%n",avg);
    }

    private static void taskH_10ZufaelligeVerkaeufe_Oesterreich(List<Sale> sales) {
        Random random = new Random();
        Stream.generate(() -> sales.get(random.nextInt(sales.size())))
                .filter(Analysis::isJanuary2024)
                .filter(sale -> sale.country().equals("Austria"))
                .limit(10)
                //.sorted(Comparator.comparing(sale -> sale.amount()))
                //.sorted((s1, s2) -> Double.compare(s2.amount(), s1.amount()))
                .forEach(System.out::println);

    }

    private static boolean isJanuary2024(Sale sale) {
        return sale.date().getMonth() == Month.JANUARY && sale.date().getYear() == 2024;
    }
}