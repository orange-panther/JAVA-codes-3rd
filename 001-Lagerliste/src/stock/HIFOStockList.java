package stock;

public class HIFOStockList extends FIFOStockList {
    @Override
    public void store(ValuedStockMovement valuedStockMovement){
        HIFOStockList.Node newNode = new FIFOStockList.Node(valuedStockMovement.clone());
        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            Node temp = head;
            if(newNode.valuedStockMovement.pricePerUnit >= head.valuedStockMovement.pricePerUnit){
                head = newNode;
                head.next = temp;
            } else{
                Node prev = null;
                while(temp != null && temp.valuedStockMovement.pricePerUnit > valuedStockMovement.pricePerUnit){
                    prev = temp;
                    temp = temp.next;
                }
                if(temp == null) {
                    tail.next = newNode;
                    tail = newNode;
                } else {
                    prev.next = newNode;
                    newNode.next = temp;
                }
            }
        }
        ingoings.put(valuedStockMovement.clone());
    }
}