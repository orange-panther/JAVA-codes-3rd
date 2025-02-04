import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Card> deck = Cards.fullDeck();

        for(Card card : deck) {
            System.out.print(card);
        }
        System.out.println();
        deck.forEach(c -> System.out.print(c));

        deck.forEach(System.out::print);

        // shuffle
        System.out.println("----------------");
        Collections.shuffle(deck);
        System.out.println("Deck");
        deck.forEach(System.out::print);
        System.out.println();

        // get hand
        System.out.println("Hand:");
        List<Card> hand = new ArrayList<>(deck.subList(0, 5));
        hand.forEach(System.out::print);
        System.out.println();

        // sort hand
        System.out.println("Sorted hand:");
        hand.sort((c1, c2) -> c1.compareTo(c2));
        hand.forEach(System.out::print);
        System.out.println();

        // Streams
        System.out.println("Streams");
        deck.stream()
                .limit(5)
                .forEach(System.out::print);
        System.out.println();

        deck.stream()
                .limit(10)
                .sorted((c1,c2) -> c1.compareTo(c2))
                .forEach(System.out::print);
        System.out.println();

        deck.stream()
                .limit(10)
                .sorted((c1, c2) -> c1.compareTo(c2))
                .filter(c -> c.getSuit().equals(Suit.HEARTS))
                .map(c -> "(" + c.getSuit() + " " + c.getRank() + ")")
                .forEach(System.out::print);

        Stream<String> stream = deck.stream()
                .limit(10)
                .sorted((c1, c2) -> c1.compareTo(c2))
                .filter(c -> c.getSuit().equals(Suit.HEARTS))
                .map(c -> "(" + c.getSuit() + " " + c.getRank() + ")")
                ;
        stream.forEach(System.out::println);
        System.out.println(stream.count()); // geht nicht weil streams nur einmalig konsomierbar sind
    }
}
