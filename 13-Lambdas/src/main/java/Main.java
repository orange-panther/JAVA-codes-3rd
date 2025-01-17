import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers);

        // Alle Elemente um 1 erhöhen
        // Lösung mittels lokaler Klasse
        class AddOne implements MyFunction {
            @Override
            public Integer apply(Integer i) {
                return i + 1;
            }
        }
        AddOne addOne = new AddOne();
        update(numbers, addOne);
        System.out.println(numbers);

        // Lösung mittels anonymer Klasse
        MyFunction addOne2 = new MyFunction() {
            @Override
            public Integer apply(Integer i) {
                return i + 1;
            }
        };
        update(numbers, addOne2);
        System.out.println(numbers);

        // Lösung mittels anonymer Klasse, aber simpler
        update(numbers, new MyFunction() {
            @Override
            public Integer apply(Integer i) {
                return i + 1;
            }
        });
        System.out.println(numbers);

        update(numbers, i -> i + 1);
        System.out.println(numbers);
        update(numbers, i -> i * 2);
        System.out.println(numbers);
        update(numbers, i -> i -2);
        System.out.println(numbers);

        // Berechnung des Mittelwerts
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        final int mean = sum/numbers.size();
        update(numbers, i -> i - mean);
        System.out.println(numbers);
        update(numbers, i -> i * i);
        System.out.println(numbers);
    }

    public static void update(List<Integer> numbers, MyFunction function) {
        for (int i = 0; i < numbers.size(); i++) {
            int oldValue = numbers.get(i);
            int newValue = function.apply(oldValue);
            numbers.set(i, newValue);
        }
    }
}

interface MyFunction {
    Integer apply(Integer i);
}
