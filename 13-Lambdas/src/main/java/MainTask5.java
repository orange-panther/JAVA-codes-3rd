import java.util.*;
import java.util.function.Predicate;

public class MainTask5 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = -10; i <= 20; i++) {
            numbers.add(i);
        }

        System.out.println("Group by uneven and even numbers:");
        var map = groupByCondition(numbers, i -> i % 2 == 0);
        System.out.println(map);

        System.out.println("Group by less than 5 and greater or equal than 5:");
        map = groupByCondition(numbers, i -> i < 5);
        System.out.println(map);

        System.out.println("Group by negative and positive numbers:");
        map = groupByCondition(numbers, i -> i > 0);
        System.out.println(map);

        System.out.println("Group by prime numbers:");
        System.out.println("note: negative numbers are excluded from prime numbers");
        map = groupByCondition(numbers, i -> MainTask2.isPrime(i));
        System.out.println(map);

        System.out.println("Group by multiples of 5");
        map = groupByCondition(numbers, i -> i % 5 == 0);
        System.out.println(map);
    }

    public static Map<Boolean, List<Integer>> groupByCondition(List<Integer> numbers, Predicate<Integer> condition) {
        Map<Boolean, List<Integer>> map = new HashMap<>();
        List<Integer> matching = new ArrayList<>();
        List<Integer> nonMatching = new ArrayList<>();

        for (Integer number : numbers) {
            if (condition.test(number)) {
                matching.add(number);
            } else {
                nonMatching.add(number);
            }
        }

        map.put(true, matching);
        map.put(false, nonMatching);
        return map;
    }

}
