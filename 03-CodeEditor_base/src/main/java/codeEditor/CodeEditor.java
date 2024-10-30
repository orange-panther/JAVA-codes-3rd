package codeEditor;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.util.Arrays;
import java.util.List;

public class CodeEditor {

    private StringBuilder text;
    private CodeType codeType;

    public CodeEditor(CodeType type) {
        text = new StringBuilder();
        this.codeType = type;
    }

    public void setCodeType(CodeType type) {
        this.codeType = type;
    }

    public void appendLine(String line) {
        text.append(line);
        text.append("\n");
    }

    public void deleteAll() {
        text.delete(0, text.length());
    }

    public void print() {
        CodeFormatter Formatter;
        switch (codeType) {
            case FORMULA:
                Formatter = new FormulaFormatter();
                break;
            case HTML:
                Formatter = new HtmlFormatter();
                break;
            case JAVA:
                Formatter = new JavaFormatter();
                break;
            default:
                Formatter = new DefFormatter();
                break;
        }

        var textToPrint = Formatter.format(text.toString());
        System.out.println(textToPrint);
    }
}
