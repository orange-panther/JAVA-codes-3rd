module org.example.javafxdecodeencode {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxdecodeencode to javafx.fxml;
    exports org.example.javafxdecodeencode;
}