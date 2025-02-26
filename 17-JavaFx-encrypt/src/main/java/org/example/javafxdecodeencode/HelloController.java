package org.example.javafxdecodeencode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloController {

    @FXML
    private TextField txtAlphabet, txtKey;
    @FXML
    private TextArea txtEncryptedText, txtPlainText;

    public void initialize() {

    }


    public void onStandardButtonClicked(ActionEvent actionEvent) {
        txtAlphabet.setText("abcdefghijklmnopqrstuvwxyzäöüß");
    }

    public void onRecalcButtonClicked(ActionEvent actionEvent) {
        String alphabet = txtAlphabet.getText();
        Random random = new Random();
        String key = Stream.generate(() -> alphabet.charAt(random.nextInt(alphabet.length())))
                .distinct()
                .limit(alphabet.length())
                .map(String::valueOf)
                .collect(Collectors.joining());

        txtKey.setText(key);

    }
}