package com.example.project_final;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationControl {

    @FXML
    private Button addNewBtn;

    @FXML
    private Button backbtn;

    @FXML
    private Button clearBtn;

    @FXML
    private TextField emailAdress_field;

    @FXML
    private TextField first_name_field;

    @FXML
    private TextField last_name_field;

    @FXML
    private TextField middle_name_field;

    @FXML
    private TextField numPassportField;   // 7 numbers

    @FXML
    private Button registerBtn;

    @FXML
    void initialize()
    {
        registerBtn.setOnAction(actionEvent ->
        {
            //method that will create new user in mysql database
            registerBtn.getScene().getWindow().hide();
            openNewScene("DONE.fxml");  // success window
        });

        addNewBtn.setOnAction(actionEvent ->
        {
            handleSignup();
            handleClear();


        });

        clearBtn.setOnAction(actionEvent -> handleClear());

        backbtn.setOnAction(actionEvent ->
        {
            backbtn.getScene().getWindow().hide();      //to hide this scene
            openNewScene("Search.fxml");       //open search window
        });
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void handleClear() {
        first_name_field.clear();
        middle_name_field.clear();
        last_name_field.clear();
        emailAdress_field.clear();
        numPassportField.clear();
    }

    private void handleSignup() {
        String first_name = first_name_field.getText();
        String middle_name = middle_name_field.getText();
        String last_name = last_name_field.getText();
        String email_Address = emailAdress_field.getText();
        String num_passport = numPassportField.getText();

        if (first_name.isEmpty() || middle_name.isEmpty() || last_name.isEmpty() || email_Address.isEmpty() || num_passport.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
            return;
        }

        if (saveUser(first_name, middle_name, last_name, num_passport, email_Address))
        {
            showAlert(Alert.AlertType.INFORMATION, "Signup Successful!", "User registered successfully");
            handleClear();
        } else
        {
            showAlert(Alert.AlertType.ERROR, "Signup Failed", "There was an error registering the user");
        }
    }



    private boolean saveUser(String first_name, String middle_name, String last_name, String num_Passport, String email_Address) {



        String url = "jdbc:mysql://127.0.0.1:3306/projectfinal";
        String user = "root";
        String pass = "Momen2005@";

        String sql = "INSERT INTO booking (first_name, middle_name, last_name, num_Passport, email_Address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, first_name);
            stmt.setString(2, middle_name);
            stmt.setString(3, last_name);
            stmt.setString(4, num_Passport);
            stmt.setString(5, email_Address);


            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void openNewScene(String window)            //method that will open new window
    {
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

