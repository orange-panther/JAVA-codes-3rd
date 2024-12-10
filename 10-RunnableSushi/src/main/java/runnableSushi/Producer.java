package runnableSushi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Producer extends Thread {

    private String name;
    private FoodType foodType;
    private final Belt belt;
    private int pos;
    private List<Food> producedFood;

    private int lastFoodId = 1;

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
        producedFood = new ArrayList<>();
    }

    /**
     * Returns a string representation of all produced food
     *
     * @return a string representation of all produced food
     */
    public String getProducedFood() {
        if (producedFood == null) {
            return "";
        }
        return producedFood.stream()
                .map(Food::toString)
                .collect(Collectors.joining(" | "));
    }

    @Override
    public void run() {
        System.out.printf("Producer %s starts producing at position %d ...\n", name, pos);
        while (!isInterrupted()) {
            try {
                var food = new Food(String.format("%s-%d", this.name, lastFoodId++), this.foodType);
                producedFood.add(food);

                synchronized (belt) {
                    // waiting until position is empty
                    while (!belt.isFreeAtPosition(pos)) {
                        belt.wait();
                    }

                    if (belt.add(food, pos)) {
                        System.out.printf("*** %s placed %s at position %d\n", name, food.getId(), pos);
                    }
                }
                Thread.sleep((int) (1000 + Math.random() * 1001));
            } catch (InterruptedException ignore) {
                break;
            }
        }
        System.out.printf("Producer %s stopped\n", name);
        System.out.printf("Producer %s produced: %s\n", name, getProducedFood());
    }
}
