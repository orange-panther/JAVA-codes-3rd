package stock;

public class StockMovementList {
    private Node head;
    private Node tail;

    public StockMovementList() {
        head = null;
        tail = null;
    }

    public void put(ValuedStockMovement valuedStockMovement) {
        Node newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    @Override
    public String toString() {
        String result = "";
        Node node = head;
        while(node != null) {
            result += node.valuedStockMovement.toString() + "\n";
            node = node.next;
        }
        return result;
    }

    private class Node {
        ValuedStockMovement valuedStockMovement;
        Node next;

        Node(ValuedStockMovement valuedStockMovement) {
            this.valuedStockMovement = valuedStockMovement;
            next = null;
        }
    }
}
