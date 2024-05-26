package com.example.project_final;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUP {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button clearBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Button backBtn;

    @FXML
    void initialize()
    {
        signupBtn.setOnAction(actionEvent -> {
                handleSignup();
                handleBack();

        });

        clearBtn.setOnAction(actionEvent -> handleClear());
    }

    @FXML
    private void handleClear() {
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        emailField.clear();
        phoneField.clear();
    }

    @FXML
    private void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showAlert(AlertType.ERROR, "Form Error!", "Please fill in all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(AlertType.ERROR, "Password Mismatch", "Password and Confirm Password doesn't match");
            return;
        }

        if (saveUser(username, password, email, phone))
        {
            showAlert(AlertType.INFORMATION, "Signup Successful!", "User registered successfully");
            handleClear();
            openNewScene("Search.fxml");
        } else
        {
            showAlert(AlertType.ERROR, "Signup Failed", "There was an error registering the user");
        }
    }

    private boolean saveUser(String username, String password, String email, String phone) {
        String url = "jdbc:mysql://127.0.0.1:3306/projectfinal";
        String user = "root";
        String pass = "Momen2005@";

        String sql = "INSERT INTO newuser (username, password, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, phone);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleBack() {
        backBtn.getScene().getWindow().hide();
        openNewScene("LOGIN.fxml");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openNewScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
