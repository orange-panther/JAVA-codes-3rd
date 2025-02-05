module org.example.javafxintroduction {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxintroduction to javafx.fxml;
    exports org.example.javafxintroduction;
}