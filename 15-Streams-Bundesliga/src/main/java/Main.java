import org.w3c.dom.ls.LSOutput;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("files/bundesliga_23_24.csv");
        final String LIEBLINGSVEREIN = "RB Salzburg";
        List<Match> matches = FileReader.readMatchesFromFile(filePath);

        // a) Drucken Sie alle Spiele des Lieblingsvereins!
        matches.stream()
                .filter(match -> Objects.equals(match.Heimmannschaft(), LIEBLINGSVEREIN) || Objects.equals(match.Gastmannschaft(), LIEBLINGSVEREIN))
                .forEach(System.out::println);

        // b) Drucken Sie alle Spiele Ihres Vereins in der Gesamtgruppe!
        matches.stream()
                .filter(match -> match.Heimmannschaft().equals(LIEBLINGSVEREIN) || match.Gastmannschaft().equals(LIEBLINGSVEREIN))
                .filter(match -> match.Gruppe().equals("Gesamtgruppe"));
        //.forEach(System.out::println);

        // c) Hat Ihr Verein in der Meistergruppe gespielt?
        /*
        System.out.println(matches.stream()
                .filter(match -> match.Heimmannschaft().equals(lieblingsverein) || match.Gastmannschaft().equals(lieblingsverein))
                .anyMatch(match -> match.Gruppe().equals("Meistergruppe")));
        */

        // d) Drucken Sie alle in der Gesamtgruppe gewonnene Spiele Ihres Vereins!
        matches.stream()
                .filter(match -> match.getWinner().equals(LIEBLINGSVEREIN))
                .filter(match -> match.Gruppe().equals("Gesamtgruppe"));
        //.forEach(System.out::println);

        // e) Drucken Sie alle in der Gesamtgruppe unentschieden gespielte Spiele Ihres Vereins!
        matches.stream()
                .filter(match -> match.Heimmannschaft().equals(LIEBLINGSVEREIN) || match.Gastmannschaft().equals(LIEBLINGSVEREIN))
                .filter(match -> match.Gruppe().equals("Gesamtgruppe"))
                .filter(match -> match.getWinner().equals("unentschieden"));
        //.forEach(System.out::println);

        // f) Drucken Sie alle 0:0-Spiele Ihres Vereins!
        matches.stream()
                .filter(match -> match.Heimmannschaft().equals(LIEBLINGSVEREIN) || match.Gastmannschaft().equals(LIEBLINGSVEREIN))
                .filter(match -> match.Heimtore() == 0 && match.Gasttore() == 0);
        //.forEach(System.out::println);

        // g) Wie viele Spiele hat Ihre Mannschaft insgesamt gewonnen?
        /*
        System.out.println(matches.stream()
                .filter(match -> match.getWinner().equals(lieblingsverein))
                .count());
         */

        // h) Wie viele Tore hat Ihre Mannschaft insgesamt erzielt?
        /*
        System.out.println(matches.stream()
                .mapToInt(match -> { // mapped to int; würde man nur map machen, würde es zu Stream<Integer> werden, was etwas aufwändiger für den Computer ist
                    if (match.Heimmannschaft().equals(lieblingsverein)) {
                        return match.Heimtore();
                    } else if(match.Gastmannschaft().equals(lieblingsverein)) {
                        return match.Gasttore();
                    } else {
                        return 0;
                    }
                })
                .sum());
         */


        // i) Wie ist die gesamte Torbilanz Ihrer Mannschaft?
        /*
        System.out.println(matches.stream()
                .mapToInt(match -> {
                    if (match.Heimmannschaft().equals(lieblingsverein)) {
                        return match.Heimtore() - match.Gasttore();
                    } else if (match.Gastmannschaft().equals(lieblingsverein)) {
                        return match.Gasttore() - match.Heimtore();
                    } else {
                        return 0;
                    }
                })
                .sum());
         */

        // Davids code dazu:
        /*
        System.out.println("===========I===========");
        var sumTakenGoals = matches.stream()
                .filter(m -> m.homeTeam().equals("TSV Hartberg") || m.visitingTeam().equals("TSV Hartberg"))
                .mapToInt(m -> {
                    int goals;
                    if (m.homeTeam().equals("TSV Hartberg")) {
                        goals = m.goalsVisiting();
                    } else {
                        goals = m.goalsHome();
                    }

                    return goals;
                })
                .sum();
        System.out.printf("Torbilanz: %d:%d%n%n", sumScoredGoals, sumTakenGoals);
         */


        // j) Wie viele Punkte hat ihre Mannschaft in der Gesamtgruppe erreicht?
        // für unentschieden gäbe es einen punkt, dass funktioniert nicht
        /*
        System.out.println((matches.stream()
                .filter(match -> match.getWinner().equals(lieblingsverein))
                .filter(match -> match.Gruppe().equals("Gesamtgruppe"))
                .count()) * 3
        );
         */

        // k) Welche Spiele wurden am 20. Spieltag ausgetragen?
        matches.stream()
                .filter(match -> match.Spieltag() == 20);
        //.forEach(System.out::println);

        // l) Welche Spiele wurden im April ausgetragen?
        matches.stream()
                .filter(match -> match.Datum().getMonth().toString().equals("APRIL"));
        //.forEach(System.out::println);

        // m) Bei welchen Spielen war die Tordifferenz größer als 5?
        matches.stream()
                .filter(match -> match.Heimtore() - match.Gasttore() > 5 || match.Gasttore() - match.Heimtore() > 5);
        //.forEach(System.out::println);

        // n) Bei wie vielen Spielen in der Gesamtgruppe hat eine Mannschaft mehr als 5 Tore erzielt?
        /*
        System.out.println(matches.stream()
                .filter(match -> match.Gruppe().equals("Gesamtgruppe"))
                .filter(match -> match.Heimtore() > 5 || match.Gasttore() > 5)
                .count());
         */

        // o) Bei welchen Spielen hat eine Mannschaft das Spiel nach der 1. Halbzeit gedreht?
        // Köcks lösung: Halbzeitstand subtrahieren und endstand subtrahieren => multiplizieren, wenn negativ, hat sich das Spiel gedreht
        /*
        System.out.println(matches.stream()
                .filter(match -> {
                    boolean heimFuehrteUndVerlor = match.HZHeimtore() > match.HZGasttore()
                            && match.getWinner().equals(match.Gastmannschaft());
                    boolean gastFuehrteUndVerlor = match.HZHeimtore() < match.HZGasttore()
                            && match.getWinner().equals(match.Heimmannschaft());
                    return heimFuehrteUndVerlor || gastFuehrteUndVerlor;
                })
                .count());
         */

        // p) Listen Sie alle Mannschaften der Qualifikationsgruppe auf!
        matches.stream()
                .filter(match -> match.Gruppe().equals("Qualifikationsgruppe"))
                .flatMap(match -> Stream.of(match.Gastmannschaft(), match.Heimmannschaft()))
                .distinct();
                //.forEach(System.out::println);

        // q) Listen Sie alle Mannschaften der Meistergruppe auf!
        matches.stream()
                .filter(match -> match.Gruppe().equals("Meistergruppe"))
                .flatMap(match -> Stream.of(match.Gastmannschaft(), match.Heimmannschaft()))
                .distinct();
                //.forEach(System.out::println);

        // r) Welche Mannschaft hat in der Gesamtgruppe die meisten Spiele gewonnen?
        matches.stream()
                .filter(match -> match.Gruppe().equals("Gesamtgruppe"))
                .map(Match::getWinner)
                        .filter(winner -> !winner.equals("unentschieden"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

    }
}
