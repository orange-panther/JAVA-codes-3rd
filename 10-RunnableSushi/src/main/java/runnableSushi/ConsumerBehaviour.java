package runnableSushi;

import java.util.List;

public interface ConsumerBehaviour {
    public void consume(List<Food> foodList, Belt belt, int pos, String name);
}
