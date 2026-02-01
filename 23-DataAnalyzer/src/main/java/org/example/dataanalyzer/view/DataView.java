package org.example.dataanalyzer.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DataView {
    // root
    private final BorderPane root = new BorderPane();

    // connection bar
    private final GridPane connectionBar = new GridPane();
    private final TextField tfURL = new TextField();
    private final TextField tfUser = new TextField();
    private final TextField tfPassword = new TextField();
    private final Button btnConnect = new Button("Connect");

    // structure view
    private final VBox vBoxTable = new VBox();
    private final Label lbTable = new Label("Table Name");
    private final TreeView<String> trvDbStructure = new TreeView<>();

    // table view
    private final TableView<Object> tavTable = new TableView<>();

    public DataView() {
        init();
    }

    private void init() {
        tfURL.setPromptText("URL");
        tfURL.setPrefWidth(240);
        tfUser.setPromptText("User");
        tfPassword.setPromptText("Password");

        connectionBar.setHgap(10);
        connectionBar.setPadding(new Insets(20, 0, 10, 0));

        connectionBar.add(tfURL, 0, 0);
        connectionBar.add(tfUser, 1, 0);
        connectionBar.add(tfPassword, 2, 0);
        connectionBar.add(btnConnect, 3, 0);

        trvDbStructure.setStyle("-fx-border-color: #c8c8c8");
        trvDbStructure.setPrefWidth(tfURL.getPrefWidth());

        tavTable.setPrefWidth(600);

        lbTable.setPrefWidth(tavTable.getPrefWidth());
        lbTable.setStyle(" -fx-text-alignment: center; -fx-alignment: center; -fx-font-size: 17");

        vBoxTable.setSpacing(5);
        vBoxTable.setPadding(new Insets(5, 10, 10, 10));
        vBoxTable.setStyle("-fx-background-color: white; -fx-border-color: #c8c8c8");
        vBoxTable.getChildren().addAll(lbTable, tavTable);

        root.setTop(connectionBar);
        root.setLeft(trvDbStructure);
        root.setCenter(vBoxTable);
    }


    public BorderPane getRoot() {
        return root;
    }

    public GridPane getConnectionBar() {
        return connectionBar;
    }

    public TextField getTfURL() {
        return tfURL;
    }

    public TextField getTfUser() {
        return tfUser;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public Button getBtnConnect() {
        return btnConnect;
    }

    public VBox getvBoxTable() {
        return vBoxTable;
    }

    public Label getLbTable() {
        return lbTable;
    }

    public TreeView<String> getTrvDbStructure() {
        return trvDbStructure;
    }

    public TableView<Object> getTavTable() {
        return tavTable;
    }
}
