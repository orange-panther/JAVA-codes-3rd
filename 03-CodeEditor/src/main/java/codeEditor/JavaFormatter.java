package codeEditor;

import java.util.Arrays;
import java.util.List;

public class JavaFormatter implements CodeFormatter {
    private static final char EOF = (char) -1;
    private static final List<String> KEYWORDS = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
            "class", "const", "continue", "default", "do", "double", "else", "enum",
            "extends", "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native", "new",
            "package", "private", "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
    );

    private int pos;
    private char ch;
    private String text;


    @Override
    public String format(String text) {
        this.text = text;
        var formattedCode = new StringBuilder();
        pos = 0;
        nextChar();

        while(ch != EOF) {
            // if we are at the start of a word
            if (Character.isLetter(ch)) {
                var word = readWord();
                // we only change the color if the word is a Java-Keyword
                if (KEYWORDS.contains(word)) {
                    formattedCode.append(ConsoleColor.ANSI_BLUE);
                    formattedCode.append(word);
                    formattedCode.append(ConsoleColor.ANSI_RESET);
                } else {
                    formattedCode.append(word);
                }
            } else if (ch == '\"') {
                // we change the color of string literals
                formattedCode.append(ConsoleColor.ANSI_GREEN);
                formattedCode.append(readStringLiteral());
                formattedCode.append(ConsoleColor.ANSI_RESET);
            } else {
                formattedCode.append(ch);
                nextChar();
            }
        }
        return formattedCode.toString();
    }

    private void nextChar() {
        try {
            ch = text.charAt(pos++);
        } catch (StringIndexOutOfBoundsException e) {
            ch = EOF;
        }
    }

    private String readWord() {
        var word = new StringBuilder();
        while (Character.isLetter(ch)) {
            word.append(ch);
            nextChar();
        }
        return word.toString();
    }

    private String readStringLiteral() {
        var literal = new StringBuilder();
        // append the opening quote
        literal.append(ch);
        nextChar();

        // if the string is empty, it just skips this part
        while (ch != '\"') {
            literal.append(ch);
            nextChar();
        }

        // append the closing quote
        literal.append(ch);
        nextChar();
        return literal.toString();
    }
}
