package date;

public class Date implements Cloneable {
    // ohne Modifikator = package private
    int year;
    int month;
    int day;

    public Date(int year, int month, int day) {
        // Konstruktoren werden durch das Keyword new beim erstellen eines neuen Objekts aufgerufen
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public Date clone() {
        return new Date(year, month, day);
    }

    @Override
    public String toString() {
        // % = Platzhalter beginnt
        // 0 = führende Nullen
        // 2 = wie viele Stellen
        // d = dezimal/Datentyp der zu erwarten ist
        // Parameteranzahl muss Platzhalteranzahl
        return String.format("%02d.%02d.%04d", day, month, year);
    }

    // get und set Methoden wenn die Felder private sind
    /*
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    */
}
