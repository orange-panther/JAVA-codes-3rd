package org.example.studentmanager;

import org.example.studentmanager.database.Database;
import org.example.studentmanager.model.StudentRepository;

public class DBTest {
    public static void main(String[] args) {
        Database database = Database.getInstance();

        StudentRepository studentRepository = new StudentRepository();
        studentRepository.addStudent("Kathi", "Einzenberger");
        studentRepository.addStudent("Flora", "Dallinger");
        studentRepository.addStudent("Lena", "Graßauer");

        System.out.println(studentRepository.getAllStudents());
    }
}

