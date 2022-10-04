package com.example.myfxapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @javafx.fxml.FXML
    private Button LogOutId;
    @javafx.fxml.FXML
    private Label welcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LogOutId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Service.changeScene(event, "login_scene.fxml", "Log in", null);
            }
        });
    }

    public void displayUserName(String email) {
        welcome.setText("Welcome " + email + "!");
    }

    @javafx.fxml.FXML
    public void logOutButton(ActionEvent actionEvent) {
    }

}
