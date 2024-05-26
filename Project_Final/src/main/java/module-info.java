module Project_Final {
    requires javafx.controls;
    requires javafx.fxml;
    exports com.example.project_final;
    requires java.sql;


    opens com.example.project_final to javafx.fxml;

}