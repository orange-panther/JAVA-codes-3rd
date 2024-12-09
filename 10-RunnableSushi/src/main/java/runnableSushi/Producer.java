package runnableSushi;

import java.util.List;
import java.util.stream.Collectors;

public class Producer extends Thread {

    private String name;
    private FoodType foodType;
    private final Belt belt;
    private int pos;
    private List<Food> producedFood;

    /**
     * Constructor
     *
     * @param name     name of the producer
     * @param foodType type of food produced
     * @param belt     belt to place the food on
     * @param pos      position on the belt to place the food
     */
    public Producer(String name, FoodType foodType, Belt belt, int pos) {
        this.name = name;
        this.foodType = foodType;
        this.belt = belt;
        this.pos = pos;
    }

    /**
     * Returns a string representation of all produced food
     *
     * @return a string representation of all produced food
     */
    public String getProducedFood() {
        return producedFood.stream()
                .map(Food::toString)
                .collect(Collectors.joining(" | "));
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.printf("Producer %s starts producing at position %d ...\n", name, pos);
                Thread.sleep((int) (1000 + Math.random() * 1001));
                int curId = producedFood.size();
                String foodId = name + "-" + curId;
                var food = new Food(foodId, foodType);
                synchronized (belt) {
                    if (belt.add(food, pos)) {
                        System.out.printf("*** %s placed %s at position $d\n", name, foodId, pos);
                    }
                }
                producedFood.add(food);
                // waiting until position is empty again
                belt.wait();
            } catch (InterruptedException ignore) {
            }
        }
        System.out.printf("Producer %s stopped\n", name);
        System.out.printf("Producer %s produced: %s\n", name, getProducedFood());
    }
}
