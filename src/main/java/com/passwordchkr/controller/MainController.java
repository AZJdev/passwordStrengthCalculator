package com.passwordchkr.controller;
import com.passwordchkr.service.EntropyCalculator;
import com.passwordchkr.service.PasswordGenerator;
import com.passwordchkr.service.PasswordStrengthService;
import com.passwordchkr.service.NistValidator;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    private boolean passwordVisible = false;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField visiblePasswordField;

    @FXML
    private Label entropyLabel;

    @FXML
    private Label strengthLabel;

    @FXML
    private ProgressBar strengthBar;

    @FXML
    private TextArea suggestionsArea;

    private String generateSuggestions(String password) {

        StringBuilder sb = new StringBuilder();

        var issues =
                NistValidator.validate(password);

        if (!password.matches(".*[A-Z].*"))
            sb.append("- Dodaj wielkie litery\n");

        if (!password.matches(".*[0-9].*"))
            sb.append("- Dodaj cyfry\n");

        if (!password.matches(".*[^a-zA-Z0-9].*"))
            sb.append("- Dodaj znaki specjalne\n");

        if (issues.isEmpty() && sb.isEmpty()) {

            sb.append(
                    "Hasło spełnia podstawowe zalecenia NIST.");

        } else {

            for (String issue : issues) {

                sb.append("• ")
                        .append(issue)
                        .append("\n");
            }
        }

        return sb.toString();
    }

    private void updateBarColor(double entropy) {

        if (entropy < 28) {

            strengthBar.setStyle(
                    "-fx-accent: red;");

        } else if (entropy < 60) {

            strengthBar.setStyle(
                    "-fx-accent: orange;");

        } else {

            strengthBar.setStyle(
                    "-fx-accent: green;");
        }
    }

    @FXML
    public void analyzePassword() {

        String password = passwordField.getText();

        double entropy =
                EntropyCalculator.calculateEntropy(password);

        String strength =
                PasswordStrengthService.evaluate(entropy);

        entropyLabel.setText(
                String.format("Entropia: %.2f bitów", entropy));

        strengthLabel.setText(
                "Siła hasła: " + strength);

        double progress = Math.min(entropy / 100.0, 1.0);

        strengthBar.setProgress(progress);

        updateBarColor(entropy);

        suggestionsArea.setText(generateSuggestions(password));
    }

    @FXML
    public void generatePassword() {

        String generated =
                PasswordGenerator.generate(16);

        passwordField.setText(generated);

        analyzePassword();
    }

    @FXML
    public void togglePasswordVisibility() {

        passwordVisible = !passwordVisible;

        if (passwordVisible) {

            visiblePasswordField.setText(
                    passwordField.getText());

            visiblePasswordField.setVisible(true);
            visiblePasswordField.setManaged(true);

            passwordField.setVisible(false);
            passwordField.setManaged(false);

        } else {

            passwordField.setText(
                    visiblePasswordField.getText());

            passwordField.setVisible(true);
            passwordField.setManaged(true);

            visiblePasswordField.setVisible(false);
            visiblePasswordField.setManaged(false);
        }
    }

    @FXML
    public void initialize() {

        visiblePasswordField.textProperty()
                .bindBidirectional(
                        passwordField.textProperty());

        passwordField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    analyzePassword();
                });

        suggestionsArea.setEditable(false);

        entropyLabel.setText("");
        strengthLabel.setText("");
        suggestionsArea.setText("");
    }

}