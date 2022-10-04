module com.example.myfxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.myfxapp to javafx.fxml;
    exports com.example.myfxapp;
}