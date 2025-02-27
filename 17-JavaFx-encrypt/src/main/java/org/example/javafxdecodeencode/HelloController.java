package org.example.javafxdecodeencode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.javafxdecodeencode.model.Encrypter;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// sollte nur Vermittler zwischen Oberfläche und Geschäftslogik sein
// grafische Funktionen sollen hier sein, das encrypten selber nicht
public class HelloController {
    // TextField ist nur eine Zeile
    @FXML
    private TextField txtAlphabet, txtKey;
    // TextAre können mehrere Zeilen sein
    @FXML
    private TextArea txtEncryptedText, txtPlainText;

    private Encrypter encrypter = new Encrypter();

    // Standardmethode, die immer beim initializieren einer view aufgerufen wird
    public void initialize() {
        // listened, ob ein Text im txtPlainText selektiert (ausgewählt) wird
        txtPlainText.selectedTextProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // welcher text ist in txtPlainText selektiert
                    int start = txtPlainText.getSelection().getStart();
                    int end = txtPlainText.getSelection().getEnd();
                    // wir selektieren denselben text, der im plainText selektiert ist, auch im encryptedText
                    txtEncryptedText.selectRange(start, end);
                }
        );

        txtEncryptedText.selectedTextProperty().addListener(
                (observable, oldValue, newValue) -> {
                    int start = txtEncryptedText.getSelection().getStart();
                    int end = txtEncryptedText.getSelection().getEnd();
                    txtPlainText.selectRange(start, end);
                }
        );

        txtAlphabet.textProperty().bindBidirectional(encrypter.alphabetProperty());
        txtKey.textProperty().bindBidirectional(encrypter.keyProperty());
        txtPlainText.textProperty().bindBidirectional(encrypter.plainTextProperty());
        txtEncryptedText.textProperty().bindBidirectional(encrypter.encryptedTextProperty());

    }


    public void onStandardButtonClicked(ActionEvent actionEvent) {
        encrypter.setDefaultAlphabet();
    }

    public void onRecalcButtonClicked(ActionEvent actionEvent) {
        encrypter.generateRandomKey();
    }

    public void onSafeButtonClicked(ActionEvent actionEvent) {
        encrypter.safe();
    }


}