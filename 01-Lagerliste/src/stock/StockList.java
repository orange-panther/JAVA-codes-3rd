package stock;

public interface StockList {
    //hier sind diese Methoden public, nicht package private, weil wir uns in einem Interface befinden
    //jede Methode in diesem Interface MUSS im Laufe der Abhängigkeiten implementiert werden
    void store (ValuedStockMovement valuedStockMovement);
    void remove (StockMovement stockMovement);

    String getStockStatus();
    String getStockIngoings();
    String getStockOutgoings();
}
