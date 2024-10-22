package stock;

public abstract class StockListImpl implements StockList {
    // protected erlaubt Zugriff von unterhalb aber nicht von außen
    protected StockMovementList ingoings;
    protected StockMovementList outgoings;

    public StockListImpl() {
      ingoings = new StockMovementList();
      outgoings = new StockMovementList();
    }

    @Override
    public String getStockIngoings() {
        return ingoings.toString();
    }

    @Override
    public String getStockOutgoings() {
        return outgoings.toString();
    }
}
