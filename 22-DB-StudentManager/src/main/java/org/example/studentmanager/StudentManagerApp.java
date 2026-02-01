package org.example.studentmanager;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.studentmanager.view.StudentPresenter;

public class StudentManagerApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StudentPresenter.show(stage);
    }

    public static void main(String[] args){
        launch();
    }
}