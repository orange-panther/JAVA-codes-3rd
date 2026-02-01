package org.example.dataanalyzer.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import org.example.dataanalyzer.model.ColumnRepository;
import org.example.dataanalyzer.model.TableRepository;

import java.util.List;

public class DataPresenter {

    private final DataView view;

    private String url;
    private String user;
    private String password;

    private TableRepository tableRepository;
    private ColumnRepository columnRepository;
    private ObservableList<String> tableNames = FXCollections.observableArrayList();

    private DataPresenter(DataView view) {
        this.view = view;
        attachEvents();
        addListeners();
    }

    private void attachEvents() {
        view.getBtnConnect().setOnAction(event -> connect());
    }

    private void addListeners() {
        view.getTrvDbStructure().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null && newValue.isLeaf()) {
                        String value = newValue.getValue();
                        System.out.println(value);
                        buildTable(value);
                    }
                });
    }

    private void connect() {
        System.out.println("Trying to load tables from given database...");
        tableNames.clear();
        loadInfoToConnect();
        try {
            this.tableRepository = new TableRepository(this.url, this.user, this.password);
            tableNames.addAll(tableRepository.getAllTableNames());
            System.out.println(tableNames);
            System.out.println("Successfully loaded table names");
        } catch (Exception e) {
            System.out.println("The inputs were not valid.");
        }

        buildTreeView();
    }

    private void loadInfoToConnect() {
        this.url = view.getTfURL().getText();
        this.user = view.getTfUser().getText();
        this.password = view.getTfPassword().getText();
    }

    private void buildTreeView() {
        TreeItem<String> root = new TreeItem<>("Connection"); // Dummy-Root

        // create a Node for tables
        TreeItem<String> tableNode = new TreeItem<>("Tables");

        tableNames.forEach(table -> {
            // create a Node for each table
            TreeItem<String> tableInstanceNode = new TreeItem<>(table);
            tableNode.getChildren().add(tableInstanceNode);
        });

        tableNode.setExpanded(true);
        root.getChildren().add(tableNode);

        // TODO: create a Node for views

        // set root node and hide it
        root.setExpanded(true);
        view.getTrvDbStructure().setRoot(root);
    }

    private void buildTable(String tableName) {
        this.columnRepository = new ColumnRepository(this.url, this.user, this.password);
        List<Object[]> columns = columnRepository.getColumnData(tableName);
        for (Object[] column : columns) {
            view.getTavTable().getItems().add(column);
            System.out.println(column);
        }

    }

    public static void show(Stage stage) {
        DataView view = new DataView();
        DataPresenter controller = new DataPresenter(view);

        Scene scene = new Scene(view.getRoot());
        stage.setTitle("Data Analyzer");
        stage.setScene(scene);
        stage.show();
    }
}
