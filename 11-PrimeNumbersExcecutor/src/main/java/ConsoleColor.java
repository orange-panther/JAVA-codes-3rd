
public enum ConsoleColor {
    RESET("\u001B[0m"),
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m"),
    GREEN( "\u001B[32m"),
    PURPLE("\u001B[35m"),
    RED("\u001B[31m"),
    YELLOW("\u001b[33m");

    private final String ansiColor;

    ConsoleColor(String ansiColor) {
        this.ansiColor = ansiColor;
    }

    public String ansiColor() {
        return ansiColor;
    }

    public static ConsoleColor get(int i) {
        return ConsoleColor.values()[i];
    }
}