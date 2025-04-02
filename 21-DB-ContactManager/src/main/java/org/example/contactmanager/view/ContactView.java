package org.example.contactmanager.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.contactmanager.model.Contact;

public class ContactView {
    // root
    private final VBox root = new VBox();

    // Search
    private final HBox hBoxSearch = new HBox();
    private final TextField tfSearchText = new TextField();
    private final Button btnSearch = new Button("Suchen");

    // Contact list
    private final ListView<Contact> lvContacts = new ListView<>();

    // Details
    private final GridPane gridPaneDetails = new GridPane();
    private final Label labelId = new Label("Id:");
    private final TextField tfId = new TextField();
    private final Label labelName = new Label("Name:");
    private final TextField tfName = new TextField();
    private final Label labelPhone = new Label("Phone:");
    private final TextField tfPhone = new TextField();
    private final Label labelAddress = new Label("Address:");
    private final TextField tfAddress = new TextField();

    // Editor Buttons
    private final HBox hBoxEditorButtons = new HBox();
    private final Button btnNew = new Button("New");
    private final Button btnEdit = new Button("Edit");
    private final Button btnSave = new Button("Save");
    private final Button btnDelete = new Button("Delete");

    public ContactView() {
        init();
    }

    private void init() {
        // Root
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Search
        hBoxSearch.setSpacing(10);
        hBoxSearch.getChildren().addAll(tfSearchText, btnSearch);

        // Contact List
        lvContacts.setPrefHeight(200);

        // Detail view
        gridPaneDetails.setHgap(10);
        gridPaneDetails.setVgap(10);
        gridPaneDetails.add(labelId, 0, 0);
        gridPaneDetails.add(tfId, 1, 0);
        gridPaneDetails.add(labelName, 0, 1);
        gridPaneDetails.add(tfName, 1, 1);
        gridPaneDetails.add(labelPhone, 0, 2);
        gridPaneDetails.add(tfPhone, 1, 2);
        gridPaneDetails.add(labelAddress, 0, 3);
        gridPaneDetails.add(tfAddress, 1, 3);

        // Editor Buttons
        hBoxEditorButtons.setSpacing(15);
        hBoxEditorButtons.getChildren().addAll(btnNew, btnEdit, btnSave, btnDelete);

        // generate root view
        root.getChildren().addAll(hBoxSearch, lvContacts, gridPaneDetails, hBoxEditorButtons);
    }

    public VBox getRoot() {
        return root;
    }

    public TextField getTfSearchText() {
        return tfSearchText;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public ListView<Contact> getLvContacts() {
        return lvContacts;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public Button getBtnNew() {
        return btnNew;
    }

    public TextField getTfAddress() {
        return tfAddress;
    }

    public TextField getTfPhone() {
        return tfPhone;
    }

    public TextField getTfName() {
        return tfName;
    }

    public TextField getTfId() {
        return tfId;
    }
}
