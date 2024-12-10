package runnableSushi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Consumer extends Thread {

    private String name;
    private ConsumerBehaviour behaviour;
    private final Belt belt;
    private int pos;
    private List<Food> foodList;

    /**
     * Constructor
     *
     * @param consumerType the type of consumer
     * @param name         the name of the consumer
     * @param belt         the belt from which the consumer consumes food
     * @param pos          the position on the belt from which the consumer consumes food
     */
    public Consumer(ConsumerType consumerType, String name, Belt belt, int pos) {
        this.name = name;
        this.belt = belt;
        this.pos = pos;
        switch (consumerType) {
            case GUEST:
                behaviour = new GuestBehaviour();
                break;
            case CLEANER:
                behaviour = new CleanerBehaviour();
                break;
        }
        foodList = new ArrayList<>();
    }

    /**
     * Returns a string representation of all consumed food
     *
     * @return a string representation of all consumed food
     */
    public String getConsumedFood() {
        if (foodList == null) {
            return "";
        }
        return foodList.stream()
                .map(Food::toString)
                .collect(Collectors.joining(" | "));
    }

    @Override
    public void run() {
        System.out.printf("Consumer %s starts consuming at position %d ...\n", name, pos);

        behaviour.consume(foodList, belt, pos, name);

        System.out.printf("Consumer %s stopped.\n", name);
        System.out.printf("%s took: %s\n", name, getConsumedFood());
    }
}
