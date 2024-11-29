package supermarkt;

public class Kunde {

    private int id;
    private double warenwert;

    private static int nextId = 1;

    private Kunde() {
        this.id = nextId++;

        // zufälliger Warenwert 0-100 Euro
        // Runde auf 2 Nachkommastellen genau
        this.warenwert = Math.round(100.0 * (Math.random() * 100.0)) / 100.0;
    }

    // factory
    public static synchronized Kunde einkaufen() {
        Kunde kunde = new Kunde();
        System.out.println(ConsoleColor.ANSI_RED + "Einkauf von " + kunde.toString() + ConsoleColor.ANSI_RESET);
        return kunde;
    }

    @Override
    public String toString() {
        return "Kunde %d: Eur %7.2f".formatted(this.id, this.warenwert);
    }

    public int getId() {
        return id;
    }

    public double getWarenwert() {
        return warenwert;
    }
}
