import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MainTask3 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "dog", "elephant", "fox",
                "grape", "house", "island", "jungle", "kite", "lemon",
                "mountain", "night", "ocean", "pencil", "queen", "river",
                "sun", "tree", "umbrella", "vase", "wolf", "xylophone",
                "yellow", "zebra", "cloud", "desk", "flower", "garden");

        System.out.println("make strings uppercase:");
        words = transformStrings(words, s -> s.toUpperCase());
        System.out.println(words);

        System.out.println("make strings lowercase:");
        words = transformStrings(words, s -> s.toLowerCase());
        System.out.println(words);

        System.out.println("append '!' to strings:");
        words = transformStrings(words, s -> s += '!');
        System.out.println(words);

        System.out.println("reverse strings:");
        words = transformStrings(words, s -> {
            StringBuilder newWord = new StringBuilder();
            for (int i = s.length() -1; i >= 0; i--) {
                newWord.append(s.charAt(i));
            }
            return newWord.toString();
        });
        System.out.println(words);

        System.out.println("print word depending on number of characters:");
        words = transformStrings(words, s -> {
            StringBuilder newWord = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                newWord.append(s);
                newWord.append(" ");
            }
            return newWord.toString();
        });
        System.out.println(words);

    }

    public static List<String> transformStrings(List<String> strings, Function<String, String> transformer) {
        for (int i = 0; i < strings.size(); i++) {
            String value = strings.get(i);
            strings.set(i, transformer.apply(value));
        }
        return strings;
    }
}
