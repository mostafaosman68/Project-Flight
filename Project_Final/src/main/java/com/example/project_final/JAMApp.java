package com.example.project_final;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JAMApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Adjust the path to your FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LOGIN.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JAM International");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
