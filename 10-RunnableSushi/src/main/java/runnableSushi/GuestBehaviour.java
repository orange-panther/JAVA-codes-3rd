package runnableSushi;

import java.util.List;

public class GuestBehaviour implements ConsumerBehaviour {
    @Override
    public void consume(List<Food> foodList, Belt belt, int pos, String name) {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep((int) (1000 + Math.random() * 5001));
                synchronized (belt) {
                    while (belt.isFreeAtPosition(pos)) {
                        belt.wait();
                    }
                    var food = belt.remove(pos);
                    foodList.add(food);

                    System.out.printf("*** %s consumed %s at position %d\n", name, food.getId(), pos);
                }
            } catch (InterruptedException ignore) {
                break;
            }
        }
    }
}