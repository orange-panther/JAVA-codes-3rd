package supermarkt;

public class Kunde {
    private int id;
    private double warenwert;

    private static int nextId = 1;

    private Kunde() {
        this.id = nextId++;
        this.warenwert = (double) (Math.round((Math.random() * 101) * 100)) / 100;
    }

    public double getWarenwert() {
        return this.warenwert;
    }

    public int getId() {
        return this.id;
    }

    public static synchronized Kunde einkaufen() {
        Kunde kunde = new Kunde();
        System.out.println("Einkauf von " + kunde.toString());
        return kunde;
    }

    @Override
    public String toString() {
        return "Kunde %d: Eur %7.2f".formatted(this.id, this.warenwert);
    }
}
