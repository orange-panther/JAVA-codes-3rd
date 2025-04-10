package org.example.contactmanager.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.example.contactmanager.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactPresenter {

    private final ContactView view;

    private Contact detailedContact = new Contact();

    private final ContactRepository contactRepository;
    private final ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private final LocationRepository locationRepository;
    private final ObservableList<Location> locationList = FXCollections.observableArrayList();

    private ContactPresenter(ContactView view) {
        this.view = view;

        this.contactRepository = new ContactRepository();
        this.locationRepository = new LocationRepository();
        bindViewToModel();
        attachEvents();
        addListeners();
        init();
    }

    private void init() {
        // load contacts from db
        reloadContactList();
        loadLocationList();
        setEditMode(view.getBtnSave(), false);
        clearFields();
    }

    private void bindViewToModel() {
        view.getTfId().textProperty().bindBidirectional(detailedContact.idProperty(), new NumberStringConverter());
        view.getTfName().textProperty().bindBidirectional(detailedContact.nameProperty());
        view.getTfAddress().textProperty().bindBidirectional(detailedContact.phoneProperty());
        view.getTfPhone().textProperty().bindBidirectional(detailedContact.addressProperty());
        view.getCbContactType().setItems(FXCollections.observableArrayList(ContactType.values()));
        view.getCbLocation().setItems(locationList);
    }

    private void buildTreeView() {
        TreeItem<Object> root = new TreeItem<>(null); // Dummy-Root

        // Group contact by type
        Map<ContactType, List<Contact>> contactsByType = contactList.stream()
                .collect(Collectors.groupingBy(Contact::getType));

        contactsByType.forEach((type, contacts) -> {
            // create a Node for each type
            TreeItem<Object> typeNode = new TreeItem<>(type);

            contacts.forEach(contact -> {
                // create a Node for each contact
                TreeItem<Object> contactNode = new TreeItem<>(contact);
                typeNode.getChildren().add(contactNode);
            });

            typeNode.setExpanded(true);
            root.getChildren().add(typeNode);
        });

        // set root node and hide it
        view.getTvContacts().setRoot(root);
        view.getTvContacts().setShowRoot(false);
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
        view.getTvContacts().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection.getValue() instanceof Contact newContact) {
                setEditMode(view.getBtnSave(), false);
                setDetailedContact(newContact);
                view.getCbContactType().setValue(detailedContact.getType());
                view.getCbLocation().setValue(detailedContact.getLocation());
            }
        });
    }

    private void reloadContactList() {
        contactList.clear();
        contactList.addAll(contactRepository.getAllContact());
        buildTreeView();
    }

    private void loadLocationList() {
        locationList.clear();
        locationList.addAll(locationRepository.getAllLocation());
    }

    private void searchContact() {
        setEditMode(view.getBtnSave(), false);
        String searchText = view.getTfSearchText().getText().toLowerCase();
        if (!searchText.isEmpty()) {
            for (Contact contact : contactList) {
                if (contact.getName().toLowerCase().contains(searchText)) {
                    selectContactInTreeView(contact);
                    // set the contact in the detailed contact view
                    setDetailedContact(contact);
                    //TODO: schauen obma des brauchen
                    view.getCbContactType().setValue(detailedContact.getType());
                    view.getCbLocation().setValue(detailedContact.getLocation());
                    break;
                }
            }
        }
    }

    private void setDetailedContact(Contact contact) {
        setEditMode(new Node[]{
                view.getTfId(),
                view.getTfName(),
                view.getTfPhone(),
                view.getTfAddress(),
                view.getCbLocation(),
                view.getCbContactType()}, false);

        detailedContact.setValues(contact);
    }

    private void clearFields() {
        // clear Contact
        detailedContact.setId(-1);
        detailedContact.setName("");
        detailedContact.setPhone("");
        detailedContact.setAddress("");
        detailedContact.setType(ContactType.NONE);

        // clear view Contact
        view.getTfId().clear();
        view.getTfName().clear();
        view.getTfPhone().clear();
        view.getTfAddress().clear();
        view.getCbLocation().setValue(null);
        view.getCbContactType().setValue(ContactType.NONE);
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

        setEditMode(new Node[]{
                view.getTfName(),
                view.getTfPhone(),
                view.getTfAddress(),
                view.getCbLocation(),
                view.getCbContactType(),
                view.getBtnSave()}, true);
        setEditMode(view.getTfId(), false);
    }

    private void editContact() {
        setEditMode(new Node[]{
                view.getTfName(),
                view.getTfPhone(),
                view.getTfAddress(),
                view.getCbLocation(),
                view.getCbContactType(),
                view.getBtnSave()}, true);
        setEditMode(view.getTfId(), false);
    }

    private void saveContact() {
        try {
            int id = Integer.parseInt(view.getTfId().getText());
            String name = view.getTfName().getText();
            String phone = view.getTfPhone().getText();
            String address = view.getTfAddress().getText();
            Location location = view.getCbLocation().getValue();
            ContactType type = view.getCbContactType().getValue();

            if (name == null || name.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()
                    || address == null || address.trim().isEmpty()
                    || type == null
                    || location == null) {
                System.out.println("Contact Input was empty.");
                return;
            } else if (type == ContactType.NONE) {
                System.out.println("Contact type can not be null; please select.");
                return;
            }

            Contact newContact = new Contact(id, name, phone, address, location, type);

            if (contactRepository.existsContact(id)) {
                contactRepository.updateContact(newContact);
            } else {
                contactRepository.addContact(name, phone, address, location, type);
            }

            reloadContactList();
            selectContactInTreeView(newContact);

            setEditMode(new Node[]{
                    view.getTfId(),
                    view.getTfName(),
                    view.getTfPhone(),
                    view.getTfAddress(),
                    view.getCbLocation(),
                    view.getCbContactType(),
                    view.getBtnSave()}, false);

        } catch (Exception e) {
            System.out.println("Contact Input in the wrong format.");
        }
    }

    private void selectContactInTreeView(Contact targetContact) {
        TreeItem<Object> root = view.getTvContacts().getRoot();

        if (root == null) return;

        for (TreeItem<Object> typeNode : root.getChildren()) {
            if (typeNode.getValue() instanceof ContactType contactType
                    && contactType == targetContact.getType()) {

                for (TreeItem<Object> contactNode : typeNode.getChildren()) {
                    if (contactNode.getValue() instanceof Contact contact
                            && contact.getId() == targetContact.getId()) {

                        typeNode.setExpanded(true);
                        view.getTvContacts().getSelectionModel().select(contactNode);
                        view.getTvContacts().scrollTo(view.getTvContacts().getRow(contactNode));
                        return;
                    }
                }
            }
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
