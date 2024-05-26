package com.example.project_final;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.project_final.DatabaseHandler;

import java.io.IOException;
import java.sql.*;

public class LOGINcon {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Button contactsBtn;


    @FXML
    private void initialize() {
        loginBtn.setOnAction(actionEvent -> handleLogin());
        contactsBtn.setOnAction(actionEvent ->
        {
            contactsBtn.getScene().getWindow().hide();
            openNewScene("Contact.fxml");
        });
        signupBtn.setOnAction(actionEvent ->
        {
            signupBtn.getScene().getWindow().hide();
            openNewScene("SignUp.fxml");
        });
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (validateLogin(username, password)) {
            loginBtn.getScene().getWindow().hide();  // hide window
            openNewScene("JAM.fxml");       // open main window
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password");
        }
    }

    private boolean validateLogin(String username, String password) {
        String url = "jdbc:mysql://127.0.0.1:3306/projectfinal";
        String user = "root";
        String pass = "Momen2005@";

        String sql = "SELECT * FROM newuser WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
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
