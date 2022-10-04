package com.example.myfxapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @javafx.fxml.FXML
    private TextField firstName;

    @javafx.fxml.FXML
    private TextField lastName;
    @javafx.fxml.FXML
    private TextField Email;
    @javafx.fxml.FXML
    private PasswordField passWord;
    @javafx.fxml.FXML
    private Button submitId;
    @javafx.fxml.FXML
    private Hyperlink already_registered;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submitId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !Email.getText().isEmpty() && !passWord.getText().isEmpty()) {
                    Service.signUpUser(event, firstName.getText(), lastName.getText(), Email.getText(), passWord.getText());
                } else {
                    System.out.println("Please fill required information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all required information");
                    alert.show();
                }
            }
        });

        already_registered.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Service.changeScene(event, "login_scene.fxml", "Log in", null);
            }
        });
    }
}
