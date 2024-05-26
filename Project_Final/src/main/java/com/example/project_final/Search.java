package com.example.project_final;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search extends JAMControl {

    @FXML
    private Button backHomeBtn;

    @FXML
    private TableColumn<Flight, String> DateFrom;

    @FXML
    private TableColumn<Flight, String> DateWhere;

    @FXML
    private TableColumn<Flight, String> Fromrr;

    @FXML
    private TableColumn<Flight, Integer> IDFlight;

    @FXML
    private TextField idflight;  // ID of flight

    @FXML
    private Button nextBookingBtn;

    @FXML
    private TableView<Flight> tableFlight;   // whole table divided into columns

    @FXML
    private TableColumn<Flight, Integer> price;

    @FXML
    private TableColumn<Flight, String> Where;

    ObservableList<Flight> oblist = FXCollections.observableArrayList();   //When the ObservableList is updated, the ListView automatically reflects these changes.

    @FXML
    void initialize() {
        showData();     // method to show data

        nextBookingBtn.setOnAction(actionEvent ->
        {
            String id = idflight.getText();
            if (!id.isEmpty() && checkFlightIDExists(id))
            {
                nextBookingBtn.getScene().getWindow().hide();
                openNewScene("Reservation.fxml");      // go to "Reservation" window
            }
            else
            {
                Shake idEmpty = new Shake(idflight);    // animation if ID is empty or doesn't exist
                idEmpty.playAnim();
            }
        });

        backHomeBtn.setOnAction(actionEvent -> {
            backHomeBtn.getScene().getWindow().hide();
            openNewScene("JAM.fxml");      // go to "main" window
        });
    }

    private void showData() {         // method that will show data from the database
        try {
            DatabaseHandler dbHandler = new DatabaseHandler();
            Connection conn = dbHandler.getDbConnection();
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM search11;");

            while (result.next()) {
                oblist.add(new Flight(result.getString("Fromrr"), result.getString("Where"),
                        result.getString("DateFrom"), result.getString("DateWhere"),
                        result.getInt("Price"), result.getInt("IDflight")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Fromrr.setCellValueFactory(new PropertyValueFactory<>("Fromrr"));
        Where.setCellValueFactory(new PropertyValueFactory<>("Where"));
        DateFrom.setCellValueFactory(new PropertyValueFactory<>("DateFrom"));
        DateWhere.setCellValueFactory(new PropertyValueFactory<>("DateWhere"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        IDFlight.setCellValueFactory(new PropertyValueFactory<>("IDflight"));
        tableFlight.setItems(oblist);
    }

    private boolean checkFlightIDExists(String id)
    {
        String sql = "SELECT IDflight FROM search11 WHERE IDflight = ?";
        try (Connection conn = new DatabaseHandler().getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while accessing the database.");
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openNewScene(String window) {        // method that will open a new window
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
