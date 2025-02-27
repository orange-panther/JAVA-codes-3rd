package org.example.javafxdecodeencode.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Encrypter {

    private final String DEFAULT_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private StringProperty alphabet = new SimpleStringProperty("");
    private StringProperty key = new SimpleStringProperty("");
    private StringProperty plainText = new SimpleStringProperty("");
    private StringProperty encryptedText = new SimpleStringProperty("");

    public Encrypter() {
        alphabet.addListener((observable, oldValue, newValue) -> generateRandomKey());
        key.addListener((observable, oldValue, newValue) -> encrypt());
        plainText.addListener((observable, oldValue, newValue) -> encrypt());

        // set default values for view
        setDefaultAlphabet();
        setPlainText("Please enter your text");
    }

    // Getter and Setter ???


    public String getAlphabet() {
        return alphabet.get();
    }

    public StringProperty alphabetProperty() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet.set(alphabet);
    }

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getPlainText() {
        return plainText.get();
    }

    public StringProperty plainTextProperty() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText.set(plainText);
    }

    public String getEncryptedText() {
        return encryptedText.get();
    }

    public StringProperty encryptedTextProperty() {
        return encryptedText;
    }

    // Methoden
    public void setDefaultAlphabet() {
        setAlphabet(DEFAULT_ALPHABET);
    }

    public void generateRandomKey() {
        String alphabet = getAlphabet();

        Random random = new Random();
        String key = Stream.generate(() -> alphabet.charAt(random.nextInt(alphabet.length())))
                .distinct()
                .limit(alphabet.length())
                .map(String::valueOf)
                .collect(Collectors.joining());

        setKey(key);
    }

    private void encrypt() {
        String key = getKey();
        String alphabet = getAlphabet();
        String plainText = getPlainText().toLowerCase();

        StringBuilder newText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char oldChar = plainText.charAt(i);
            int indexOfChar = alphabet.indexOf(oldChar);

            if (indexOfChar == -1) {
                newText.append(oldChar);
            } else {
                newText.append(key.charAt(indexOfChar));
            }
        }

        encryptedTextProperty().set(newText.toString());
    }

    public void safe() {
        FileWriterUtil.writeToFile("files/alphabetAndKey.txt", getAlphabet() + "\n" + getKey());
        FileWriterUtil.writeToFile("files/plainText.txt", getPlainText() + "\n");
        FileWriterUtil.writeToFile("files/encryptedText.txt", getEncryptedText() + "\n");
    }
}
