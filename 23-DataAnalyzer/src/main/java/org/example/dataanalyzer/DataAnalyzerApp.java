package org.example.dataanalyzer;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.dataanalyzer.view.DataPresenter;

public class DataAnalyzerApp extends Application {
    @Override
    public void start(Stage stage){
        DataPresenter.show(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
