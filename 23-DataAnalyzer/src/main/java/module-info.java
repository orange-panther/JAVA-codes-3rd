module org.example.dataanalyzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.dataanalyzer to javafx.fxml;
    exports org.example.dataanalyzer;
}