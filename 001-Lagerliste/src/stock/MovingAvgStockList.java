package stock;

public class MovingAvgStockList extends StockListImpl {

    // aktueller Lagerbestand
    private ValuedStockMovement stock;

    public MovingAvgStockList() {
        stock = null;
    }

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        // process ingoing
        if (stock == null) {
            // WICHTIG: Im Lager wird ein neues Objekt abgelegt, damit es keine
            // Wechselwirkungen mid den übergeordneten Programmsystemen gibt,
            // da wir vorhaben Eigenschaften des Objekt (z. B. Menge) zu verändern.
            stock = valuedStockMovement.clone();
        } else {
            // GLD Berechnung
            double totalQuantity = stock.quantity + valuedStockMovement.quantity;
            // man könnte hier im ValueStockMovement eine Methode dafür machen die quantity
            // mit priceperUnit multipliziert
            double totalValue = stock.getValue() + valuedStockMovement.getValue();
            double avgPricePerUnit = totalValue / totalQuantity;

            stock.quantity = totalQuantity;
            stock.pricePerUnit = avgPricePerUnit;
            stock.date = valuedStockMovement.date.clone();
        }

        // write to IngoingList
        ingoings.put(valuedStockMovement.clone());
    }

    @Override
    public void remove(StockMovement stockMovement) {
        // quit if stock is empty
        if (stock == null) {
            return;
        }

        ValuedStockMovement outgoing = new ValuedStockMovement(
                stockMovement.date.clone(),
                0,
                stock.pricePerUnit); // Bewertung mit dem aktuellen GLD

        if (stock.quantity <= stockMovement.quantity) {
            // Fall 1: Gesamte Lagermenge wird entnommen
            outgoing.quantity = stock.quantity;
            stock = null;
        } else {
            // Fall 2: Ein Teil der Lagermenge wird entnommen; eine Restmenge verbleibt im Lager
            outgoing.quantity = stockMovement.quantity;
            stock.quantity -= stockMovement.quantity;
        }

        outgoings.put(outgoing);
    }

    @Override
    public String getStockStatus() {
        if(stock == null) {
            return "\n";
        } else {
            return stock.toString() + "\n";
        }
    }
}
