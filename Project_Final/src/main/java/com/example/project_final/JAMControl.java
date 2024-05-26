package com.example.project_final;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class JAMControl {

    @FXML
    private Button clearBtn;

    @FXML
    private Button contactsBtn;

    @FXML
    private DatePicker dateOfFly_picker;

    @FXML
    private TextField from_field;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField where_field;


    @FXML
    void initialize() {

        dateOfFly_picker.setOnAction(actionEvent ->
        {
            LocalDate time = dateOfFly_picker.getValue();       //data picker of flight
            System.out.println(time);
        });

        searchBtn.setOnAction(event ->
        {
            sortData();
            searchBtn.getScene().getWindow().hide();
            openNewScene("Search.fxml");              //method that will see if text field empty and then go to search window
        });

        contactsBtn.setOnAction(event -> {
            contactsBtn.getScene().getWindow().hide();   // hide this window
            openNewScene("Contact.fxml");  // view contacts window
        });

        clearBtn.setOnAction(actionEvent -> {
            from_field.clear();         //clear both text fields
            where_field.clear();
        });
    }


    public void sortData()      //future improvement | data in array "dataFlight" will sort data in table in "SearchController"
    {
        String from = from_field.getText();
        String where = where_field.getText();
        String date = String.valueOf(dateOfFly_picker.getValue());
        String[] dataFlight = {from, where, date};

        for (String s : dataFlight) {
            System.out.println(s);
        }
    }
    private void openNewScene(String window)        //method that will open new window
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


