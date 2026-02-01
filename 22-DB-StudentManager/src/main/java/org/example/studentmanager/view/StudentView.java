package org.example.studentmanager.view;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.studentmanager.model.Student;

public class StudentView {
    // root
    private final VBox root = new VBox();

    // table
    private final TableView<Student> tvStudent = new TableView<Student>();
    private final TableColumn<Student, String> idCol = new TableColumn<>("Id");
    private final TableColumn<Student, String> firstnameCol = new TableColumn<>("First Name");
    private final TableColumn<Student, String> surnameCol = new TableColumn<>("Surname");

    public StudentView() {
        init();
    }

    private void init() {
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // table
        tvStudent.setPrefHeight(200);
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));

        firstnameCol.setMinWidth(100);
        firstnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));

        surnameCol.setMinWidth(100);
        surnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));

        tvStudent.getColumns().addAll(idCol, firstnameCol, surnameCol);

        // generate root view
        root.getChildren().addAll(tvStudent);
    }


    public VBox getRoot() {
        return root;
    }

    public TableView<Student> getTvStudent() {
        return tvStudent;
    }
}
