package org.example.contactmanager.model;

import javafx.beans.property.*;

public class Contact {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private ObjectProperty<Location> location = new SimpleObjectProperty<>();
    private ObjectProperty<ContactType> type = new SimpleObjectProperty<>();

    public Contact() {

    }

    public Contact(int id, String name, String phone, String address, Location location, ContactType type) {
        setId(id);
        setName(name);
        setPhone(phone);
        setAddress(address);
        setLocation(location);
        setType(type);
    }

    public void setValues(Contact other) {
        setId(other.getId());
        setName(other.getName());
        setAddress(other.getAddress());
        setLocation(other.getLocation());
        setPhone(other.getPhone());
        setType(other.getType());
    }

    @Override
    public String toString() {
        return "%d: %s (%s, %s, %s)".formatted(
                getId(),
                getName(),
                getPhone(),
                getAddress(),
                getLocation().toString()
        );
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public ContactType getType() {
        return type.get();
    }

    public ObjectProperty<ContactType> typeProperty() {
        return type;
    }

    public void setType(ContactType type) {
        this.type.set(type);
    }

    public Location getLocation() {
        return location.get();
    }

    public ObjectProperty<Location> locationProperty() {
        return location;
    }

    public void setLocation(Location location) {
        this.location.set(location);
    }
}
