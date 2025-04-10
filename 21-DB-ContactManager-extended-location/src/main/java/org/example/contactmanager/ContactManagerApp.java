package org.example.contactmanager;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.contactmanager.view.ContactPresenter;

public class ContactManagerApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ContactPresenter.show(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
