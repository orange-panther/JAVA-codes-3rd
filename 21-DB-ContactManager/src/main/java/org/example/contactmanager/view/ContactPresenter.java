package org.example.contactmanager.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.contactmanager.model.Contact;
import org.example.contactmanager.model.ContactRepository;

public class ContactPresenter {

    private final ContactView view;

    private Contact detailedContact = new Contact();

    private final ContactRepository contactRepository;
    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();

    private ContactPresenter(ContactView view) {
        this.view = view;

        this.contactRepository = new ContactRepository();
        bindViewToModel();
        attachEvents();
        addListeners();
        init();
    }

    private void init() {
        // load contacts from db
        reloadContactList();
        setEditMode(view.getBtnSave(), false);
    }

    private void bindViewToModel() {
        view.getLvContacts().setItems(contactList);
        // weitere Bindungen für Detailansicht
        updateDetailedViewBindings();
    }

    private void updateDetailedViewBindings() {
        view.getTfId().setText(String.valueOf(detailedContact.getId()));
        view.getTfName().setText(detailedContact.getName());
        view.getTfPhone().setText(detailedContact.getPhone());
        view.getTfAddress().setText(detailedContact.getAddress());
    }

    private void attachEvents() {
        view.getBtnSearch().setOnAction(event -> searchContact());
        view.getBtnNew().setOnAction(event -> newContact());
        view.getBtnEdit().setOnAction(event -> editContact());
        view.getBtnSave().setOnAction(event -> saveContact());
        view.getBtnDelete().setOnAction(event -> deleteContact());
    }

    private void addListeners() {
        // reagieren auf eine Selektion in der Liste
        view.getLvContacts().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection != oldSelection) {
                setDetailedContact(newSelection);
                updateDetailedViewBindings();
            }
        });
    }

    private void reloadContactList() {
        contactList.clear();
        contactList.addAll(contactRepository.getAllContact());
    }

    private void searchContact() {
        String searchText = view.getTfSearchText().getText().toLowerCase();
        if (!searchText.isEmpty()) {
            for (Contact contact : contactList) {
                if (contact.getName().toLowerCase().contains(searchText)) {
                    view.getLvContacts().getSelectionModel().select(contact);
                    view.getLvContacts().scrollTo(contact);
                    // set the contact in the detailed contact view
                    setDetailedContact(contact);
                    updateDetailedViewBindings();
                    break;
                }
            }
        }
    }

    private void setDetailedContact(Contact contact) {
        setEditMode(new TextField[]{view.getTfId(), view.getTfName(), view.getTfPhone(), view.getTfAddress()}, false);

        detailedContact.setId(contact.getId());
        detailedContact.setName(contact.getName());
        detailedContact.setPhone(contact.getPhone());
        detailedContact.setAddress(contact.getAddress());
    }

    private void clearFields() {
        // clear Contact
        detailedContact.setId(-1);
        detailedContact.setName("");
        detailedContact.setPhone("");
        detailedContact.setAddress("");

        // clear view Contact
        view.getTfId().clear();
        view.getTfName().clear();
        view.getTfPhone().clear();
        view.getTfAddress().clear();
    }

    private void setEditMode(Node[] nodes, boolean mode) {
        for (Node node : nodes) {
            setEditMode(node, mode);
        }
    }

    private void setEditMode(Node node, boolean mode) {
        node.setDisable(!mode);
        if (!mode) {
            node.setStyle("-fx-background-color: #d3d3d3;");
        } else {
            node.setStyle("");
        }
    }

    private void newContact() {
        clearFields();
        int nextId = contactRepository.getLastId() + 1;
        view.getTfId().setText(String.valueOf(nextId));
        setEditMode(new TextField[]{view.getTfName(), view.getTfPhone(), view.getTfAddress()}, true);
        setEditMode(view.getTfId(), false);
        setEditMode(view.getBtnSave(), true);
    }

    private void editContact() {
        setEditMode(new TextField[]{view.getTfName(), view.getTfPhone(), view.getTfAddress()}, true);
        setEditMode(view.getTfId(), false);
        setEditMode(view.getBtnSave(), true);
    }

    private void saveContact() {
        try {
            int id = Integer.parseInt(view.getTfId().getText());
            String name = view.getTfName().getText();
            String phone = view.getTfPhone().getText();
            String address = view.getTfAddress().getText();

            if (name == null || name.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()
                    || address == null || phone.trim().isEmpty()) {
                System.out.println("Contact Input was empty.");
                return;
            }

            Contact newContact = new Contact(id, name, phone, address);

            if (contactRepository.existsContact(id)) {
                contactRepository.updateContact(newContact);
            } else {
                contactRepository.addContact(name, phone, address);
            }

            reloadContactList();

            // Find the contact that was just saved (either updated or newly added)
            Contact selectedContact = null;
            for (Contact contact : contactList) {
                if (contact.getId() == newContact.getId()) {
                    selectedContact = contact;
                    break;
                }
            }

            if (selectedContact != null) {
                view.getLvContacts().getSelectionModel().select(selectedContact);
                view.getLvContacts().scrollTo(selectedContact);
            }

            setEditMode(new TextField[]{view.getTfId(), view.getTfName(), view.getTfPhone(), view.getTfAddress()}, false);
            setEditMode(view.getBtnSave(), false);

        } catch (Exception e) {
            System.out.println("Contact Input in the wrong format.");
        }
    }

    private void deleteContact() {
        int id = Integer.parseInt(view.getTfId().getText());
        if (contactRepository.existsContact(id)) {
            contactRepository.deleteContact(id);
        }

        clearFields();
        reloadContactList();
    }

    public static void show(Stage stage) {
        ContactView view = new ContactView();
        ContactPresenter controller = new ContactPresenter(view);

        Scene scene = new Scene(view.getRoot());
        stage.setTitle("Contact Manager");
        stage.setScene(scene);
        stage.show();
    }

}
