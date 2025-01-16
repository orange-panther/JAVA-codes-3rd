package stock;

public class FIFOStockList extends StockListImpl {
    protected Node head;
    protected Node tail;

    public FIFOStockList() {
        head = null;
        tail = null;
    }

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        Node newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        // write to IngoingList
        ingoings.put(valuedStockMovement.clone());
    }

    @Override
    public void remove(StockMovement stockMovement) {
        // quit if stock is empty
        if (head == null) {
            return;
        }

        while (stockMovement.quantity > 0 && head != null) {
            if (stockMovement.quantity >= head.valuedStockMovement.quantity) {
                stockMovement.quantity -= head.valuedStockMovement.quantity;
                outgoings.put(new ValuedStockMovement(
                        stockMovement.date.clone(),
                        head.valuedStockMovement.quantity,
                        head.valuedStockMovement.pricePerUnit));
                head = head.next;

            } else {
                head.valuedStockMovement.quantity -= stockMovement.quantity;
                outgoings.put(new ValuedStockMovement(
                        stockMovement.date.clone(),
                        stockMovement.quantity,
                        head.valuedStockMovement.pricePerUnit));
                stockMovement.quantity = 0;
            }
        }
    }

    @Override
    public String getStockStatus() {
        if (head == null) {
            return "\n";
        } else {
            Node listToPrint = head;
            String toReturn = "";
            while (listToPrint != null) {
                toReturn += listToPrint.valuedStockMovement.toString();
                toReturn += "\n";
                listToPrint = listToPrint.next;
            }
            return toReturn;
        }
    }

    protected class Node {
        ValuedStockMovement valuedStockMovement;
        Node next;

        Node(ValuedStockMovement valuedStockMovement) {
            this.valuedStockMovement = valuedStockMovement;
            next = null;
        }
    }
}


