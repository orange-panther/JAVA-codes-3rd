package codeEditor;

public class HtmlFormatter implements CodeFormatter {
    private static final char EOF = (char) -1;

    private int pos;
    private char ch;
    private String text;

    @Override
    public String format(String text) {
        this.text = text;
        StringBuilder formattedCode = new StringBuilder();
        pos = 0;
        nextChar();

        while (ch != EOF) {
            if(ch == '<') {
                formattedCode.append(readAndFormatTag());
            } else {
                formattedCode.append(ch);
            }
            nextChar();
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

    private boolean isHtmlCharacter(char ch) {
        // we also check for digits (e.g. <h1> tag)
        return Character.isLetter(ch)
                || ch == '-'
                || Character.isDigit(ch);
    }


    private String readWord() {
        var word = new StringBuilder();
        while (isHtmlCharacter(ch)) {
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

    private String readAndFormatTag() {
        var tag = new StringBuilder();
        //append '<' and optional /
        tag.append(ch);
        nextChar();
        if(ch == '/') {
            tag.append(ch);
            nextChar();
        }
        // color tag name
        tag.append(ConsoleColor.ANSI_RED);
        tag.append(readWord());
        tag.append(ConsoleColor.ANSI_RESET);

        while (ch != '>') {
            if (isHtmlCharacter(ch)) {
                tag.append(ConsoleColor.ANSI_PURPLE);
                tag.append(readWord());
                tag.append(ConsoleColor.ANSI_RESET);
            } else if (ch == '\"') {
                tag.append(ConsoleColor.ANSI_GREEN);
                tag.append(readStringLiteral());
                tag.append(ConsoleColor.ANSI_RESET);
            } else {
                tag.append(ch);
                nextChar();
            }
        }
        // append the '>'
        tag.append(ch);

        return tag.toString();
    }

}
