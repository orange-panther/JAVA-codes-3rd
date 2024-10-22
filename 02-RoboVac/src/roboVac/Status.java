package roboVac;

// ist ein eigener Datentyp, die genau diese drei Ausprägungen hat
public enum Status {
    WALL('#'),
    CLEAN(' '),
    DIRTY('.');


    // final = kann nicht überschrieben werden
    public final char label;

    //Konstruktor
    private Status(char label) {
        this.label = label;
    }

    public static Status valueOfLabel(char label) {
        for (Status s : Status.values()) {
            if (s.label == label) {
                return s;
            }
        }
        return null;
    }
}


