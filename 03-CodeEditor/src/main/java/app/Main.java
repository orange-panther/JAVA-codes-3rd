package app;

import codeEditor.CodeEditor;
import codeEditor.CodeType;

public class Main {
    public static void main(String[] args) {

        // test formula
        System.out.println("CodeType: Formula");
        System.out.println("-----------------------------------------------------------------------");
        CodeEditor editor = new CodeEditor(CodeType.FORMULA);
        editor.appendLine("1: a^2 + b^2 = c^2");
        editor.appendLine("2: sin(x) + cos(x) = 1");
        editor.appendLine("3: y = 2*a*b*cos(gamma)");
        editor.appendLine("IV: A = r^2 * pi");
        editor.appendLine("V: f(x) = 1.245 * x^2 + 3.45");
        editor.print();

        // test default
        System.out.println("CodeType: Default");
        System.out.println("-----------------------------------------------------------------------");
        editor.setCodeType(CodeType.DEFAULT);
        editor.print();

        // test html
        System.out.println("CodeType: HTML");
        System.out.println("-----------------------------------------------------------------------");
        editor.deleteAll();
        editor.setCodeType(CodeType.HTML);
        editor.appendLine("<html lang=\"de\">");
        editor.appendLine("    <head>");
        editor.appendLine("        <meta charset=\"UTF-8\">");
        editor.appendLine("        <meta name=\"viewport\">");
        editor.appendLine("        <meta http-equiv=\"X-UA-Compatible\">");
        editor.appendLine("        <title>Meine kleine HTML-Seite</title>");
        editor.appendLine("    </head>");
        editor.appendLine("    <body>");
        editor.appendLine("        <h1>Willkommen auf meiner Seite</h1>");
        editor.appendLine("        <p>Hier steht ein bisschen Text.</p>");
        editor.appendLine("    </body>");
        editor.appendLine("</html>");
        editor.print();

        // test java
        System.out.println("CodeType: Java");
        System.out.println("-----------------------------------------------------------------------");
        editor.deleteAll();
        editor.setCodeType(CodeType.JAVA);
        editor.appendLine("public class TestProgramm {");
        editor.appendLine("    public static void main(String[] args) {");
        editor.appendLine("        // A loop that iterates from 1 to 10");
        editor.appendLine("        for (int i = 1; i <= 10; i++) {");
        editor.appendLine("            // Check if the number is even or odd");
        editor.appendLine("            if (i % 2 == 0) {");
        editor.appendLine("                System.out.println(i + \" is an even number.\");");
        editor.appendLine("            } else {");
        editor.appendLine("                System.out.println(i + \" is an odd number.\");");
        editor.appendLine("            }");
        editor.appendLine("        }");
        editor.appendLine("    }");
        editor.appendLine("}");
        editor.print();
    }
}