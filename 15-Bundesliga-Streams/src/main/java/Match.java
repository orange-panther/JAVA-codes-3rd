import java.time.LocalDate;
import java.time.LocalTime;

// Eigenschaften (Gruppe, Spieltag, Datum, ...) würde man in Java immer klein schreiben
public record Match(String Gruppe,
                    int Spieltag,
                    LocalDate Datum,
                    LocalTime Uhrzeit,
                    String Heimmannschaft,
                    String Gastmannschaft,
                    int Heimtore,
                    int Gasttore,
                    int HZHeimtore,
                    int HZGasttore
                    ) {
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Gruppe: " + Gruppe + "\n")
                .append("Spieltag: " + Spieltag + "\n")
                .append("Datum: " + Datum + "\n")
                .append("Uhrzeit: " + Uhrzeit + "\n")
                .append("--------------------------------\n")
                .append("Heimmannschaft: " + Heimmannschaft + "\n")
                .append("Heimtore Halbzeit: " + HZHeimtore + "\n")
                .append("Heimtore gesamt: " + Heimtore + "\n")
                .append("Gastmannschaft: " + Gastmannschaft + "\n")
                .append("Gasttore Halbzeit: " + HZGasttore + "\n")
                .append("Gasttore gesamt: " + Gasttore + "\n");

        return string.toString();
    }

    public String getWinner(){
        String winner = "unentschieden";

        if(Heimtore > Gasttore){
            winner = Heimmannschaft;
        } else if (Gasttore > Heimtore){
            winner = Gastmannschaft;
        }

        return winner;
    }
}
