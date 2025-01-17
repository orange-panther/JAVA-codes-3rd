import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;

public class MainTask4 {
    public static void main(String[] args){

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }

        System.out.println("sum of all numbers:");
        Integer sum = processNumbers(numbers, i -> i != null, i -> i);
        System.out.println(sum.toString());

        System.out.println("sum of uneven numbers:");
        sum = processNumbers(numbers, i -> i % 2 == 1, i -> i);
        System.out.println(sum.toString());

        System.out.println("sum of doubled uneven numbers:");
        sum = processNumbers(numbers, i -> i % 2 == 1, i -> i * 2);
        System.out.println(sum.toString());

        System.out.println("sum of all multiples of three, that are multiplied by three:");
        sum = processNumbers(numbers, i -> i % 3 == 0, i -> i * 3);
        System.out.println(sum.toString());
    }

    public static Integer processNumbers(List<Integer> numbers, Predicate<Integer> filter, Function<Integer, Integer> transformer){
        Integer sum = 0;
        for(Integer number: numbers){
            if(filter.test(number)){
                sum += transformer.apply(number);
            }
        }
        return sum;
    }
}
