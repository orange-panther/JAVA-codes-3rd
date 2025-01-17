import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainTask2 {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);

        System.out.println("Even numbers;");
        List<Integer> changedNumbers = filterNumbers(numbers, i -> i % 2 == 0);
        System.out.println(changedNumbers);

        System.out.println("Uneven numbers:");
        changedNumbers = filterNumbers(numbers, i -> i % 2 == 1);
        System.out.println(changedNumbers);

        System.out.println("Multiples of 4:");
        changedNumbers = filterNumbers(numbers, i -> i % 4 == 0);
        System.out.println(changedNumbers);

        System.out.println("Prime numbers:");
        changedNumbers = filterNumbers(numbers, i -> isPrime(i));
        System.out.println(changedNumbers);

        System.out.println("Dividends of 52:");
        changedNumbers = filterNumbers(numbers, i -> 52 % i == 0);
        System.out.println(changedNumbers);

    }

    public static List<Integer> filterNumbers(List<Integer> numbers, Predicate<Integer> condition) {
        List<Integer> newNumbers = new ArrayList<Integer>();
        for (int number : numbers) {
            if (condition.test(number)) {
                newNumbers.add(number);
            }
        }
        return newNumbers;
    }

    public static boolean isPrime(Integer i) {
        boolean isPrime = true;

        if (i == 1) {
            // 1 is not a prime number, but would be 1 if we used % 2
            isPrime = false;
        } else {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
        }

        return isPrime;
    }
}
