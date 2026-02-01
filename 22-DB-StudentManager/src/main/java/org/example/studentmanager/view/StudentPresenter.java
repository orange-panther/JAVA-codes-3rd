package org.example.studentmanager.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.model.StudentRepository;

import java.util.List;

public class StudentPresenter {

    private final StudentView view;

    private final StudentRepository studentRepository;
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    private StudentPresenter(StudentView view) {
        this.view = view;

        this.studentRepository = new StudentRepository();
        init();

    }

    private void init() {
        loadStudentList();
        showStudentTable();
    }

    private void loadStudentList() {
        studentList.clear();
        studentList.addAll(studentRepository.getAllStudents());
        showStudentTable();
    }

    private void showStudentTable() {
            TableView<Student> table = view.getTvStudent();
            table.setItems(studentList);
    }


    public static void show(Stage stage) {
        StudentView view = new StudentView();
        StudentPresenter controller = new StudentPresenter(view);

        Scene scene = new Scene(view.getRoot());
        stage.setTitle("Student Manager");
        stage.setScene(scene);
        stage.show();
    }

}
