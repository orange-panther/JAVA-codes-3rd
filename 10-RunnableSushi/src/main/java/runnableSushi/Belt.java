package runnableSushi;

public class Belt {

    private Food[] foodArr;

    /**
     *
     * @param capacity The capacity of the belt, i.e. the number of positions on the belt
     */
    public Belt(int capacity) {
        // TODO
    }

    /**
     * Checks if a position is valid on the belt
     * @param pos A position on the belt
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(int pos) {
        // TODO
        return false;
    }

    /**
     * Checks if a position is free on the belt, so that food can be placed there
     * @param pos A position on the belt
     * @return true if the position is free, false otherwise
     */
    public boolean isFreeAtPosition(int pos) {
        // TODO
        return false;
    }

    /**
     * Checks if the belt is empty, i.e. there is no food on it
     * @return true if the belt is empty, false otherwise
     */
    public boolean isEmpty() {
        // TODO
        return false;
    }

    /**
     * Adds food to the belt at a given position if the position is free
     * @param food The food to be added
     * @param pos The position on the belt where the food should be added
     * @return true if the food was added successfully, false otherwise
     */
    public boolean add(Food food, int pos) {
        // TODO
        return false;
    }

    /**
     * Removes food from the belt at a given position
     * @param pos The position on the belt where the food should be removed
     * @return The food that was removed or null if there was no food at the given position
     */
    public Food remove(int pos) {
        // TODO
        return null;
    }

    /**
     * Moves all food on the belt one position to the right
     */
    public void move() {
        // TODO
    }

    /**
     * Returns a string representation of the belt
     * @return A string representation of the belt
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }

}
