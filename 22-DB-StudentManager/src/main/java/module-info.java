module org.example.studentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;


    opens org.example.studentmanager to javafx.fxml;
    exports org.example.studentmanager;
}