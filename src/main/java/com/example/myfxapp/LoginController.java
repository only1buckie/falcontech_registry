package com.example.myfxapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField passWord;

    @FXML
    private Button LoginId;
    @FXML
    private Hyperlink signup_id;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Service.logInUser(event, Email.getText(), passWord.getText());
            }
        });

        signup_id.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Service.changeScene(event, "sign_up_scene.fxml", "Sign Up", null);
            }
        });
    }


}