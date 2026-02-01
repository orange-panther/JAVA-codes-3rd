package org.example.studentmanager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student {
    public IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty firstname = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public Student() {

    }

    public Student(int id, String firstname, String surname) {
        setId(id);
        setFirstname(firstname);
        setSurname(surname);
        updateCourses();
    }

    public void setValues(Student other) {
        setId(other.getId());
        setFirstname(other.getFirstname());
        setSurname(other.getSurname());
    }

    public void updateCourses(){
        StudentRepository studentRepository = new StudentRepository();
        courses.addAll(studentRepository.getCoursesByStudent(this.getId()));
    }

    @Override
    public String toString() {
        return "#%d: %s %s".formatted(
                getId(),
                getFirstname(),
                getSurname()
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

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses = courses;
    }
}
