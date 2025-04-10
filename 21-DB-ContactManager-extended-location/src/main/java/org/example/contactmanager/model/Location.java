package org.example.contactmanager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Location {
    private StringProperty plz = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();

    public Location() {

    }

    public Location(String plz, String name) {
        setPlz(plz);
        setName(name);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(
                getPlz(),
                getName()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return this.getPlz().equals(location.getPlz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(plz, name);
    }

    public String getPlz() {
        return plz.get();
    }

    public StringProperty plzProperty() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz.set(plz);
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
}
