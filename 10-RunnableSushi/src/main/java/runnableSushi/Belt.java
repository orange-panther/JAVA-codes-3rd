package runnableSushi;

public class Belt extends Thread {

    private Food[] foodArr;

    /**
     * @param capacity The capacity of the belt, i.e. the number of positions on the belt
     */
    public Belt(int capacity) {
        foodArr = new Food[capacity];
    }

    /**
     * Checks if a position is valid on the belt
     *
     * @param pos A position on the belt
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(int pos) {
        return pos < foodArr.length || pos >= 0;
    }

    /**
     * Checks if a position is free on the belt, so that food can be placed there
     *
     * @param pos A position on the belt
     * @return true if the position is free, false otherwise
     */
    public boolean isFreeAtPosition(int pos) {
        return foodArr[pos] == null;
    }

    /**
     * Checks if the belt is empty, i.e. there is no food on it
     *
     * @return true if the belt is empty, false otherwise
     */
    public boolean isEmpty() {
        boolean isEmpty = true;
        for (Food food : foodArr) {
            if (food != null) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    /**
     * Adds food to the belt at a given position if the position is free
     *
     * @param food The food to be added
     * @param pos  The position on the belt where the food should be added
     * @return true if the food was added successfully, false otherwise
     */
    public synchronized boolean add(Food food, int pos) {
        boolean isAdded = false;
        if (isValidPosition(pos) && isFreeAtPosition(pos)) {
            foodArr[pos] = food;
            isAdded = true;
        }
        return isAdded;
    }

    /**
     * Removes food from the belt at a given position
     *
     * @param pos The position on the belt where the food should be removed
     * @return The food that was removed or null if there was no food at the given position
     */
    public synchronized Food remove(int pos) {
        Food toReturn = null;
        if (isValidPosition(pos) && !isFreeAtPosition(pos)) {
            toReturn = foodArr[pos];
            foodArr[pos] = null;
        }
        return toReturn;
    }

    /**
     * Moves all food on the belt one position to the right
     */
    public void move() {
        var movedFoodArr = new Food[foodArr.length];
        for (int i = 0; i < foodArr.length - 1; i++) {
            movedFoodArr[i + 1] = foodArr[i];
        }
        movedFoodArr[0] = foodArr[foodArr.length - 1];
        foodArr = movedFoodArr;

        // shorter version from chatGPT
        /*
        Food lastFood = foodArr[foodArr.length - 1];
        System.arraycopy(foodArr, 0, foodArr, 1, foodArr.length - 1);
        foodArr[0] = lastFood;
         */
    }

    /**
     * Returns a string representation of the belt
     *
     * @return A string representation of the belt
     */
    @Override
    public String toString() {
        var beltString = new StringBuilder();
        for (int i = 0; i < foodArr.length; i++) {
            String foodString = foodArr[i] == null ? "..." : foodArr[i].toString();
            beltString.append("-(")
                    .append(i)
                    .append(":")
                    .append(foodString)
                    .append(")");
        }
        return beltString.toString();
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                synchronized (this) {
                    move();
                    System.out.println(this.toString());
                    this.notifyAll();
                }
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
            }
        }
        System.out.println("Belt stopped");
    }
}
