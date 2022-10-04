package com.example.myfxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Service {

    //Method to changescenes
    public static void changeScene(ActionEvent event, String fxml, String title, String email) {
        Parent root = null;

        //check if username/email is supplied or just switching scenes
        if (email != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Service.class.getResource(fxml));
                root = loader.load();
                //passing username n title to specified scene
                WelcomeController welcomeController = loader.getController();
                welcomeController.displayUserName(email);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Service.class.getResource(fxml));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    //method that handles registration/ connects to database
    public static void signUpUser(ActionEvent event, String firstName, String lastName, String email, String passWord) {
        final String DB_URL = "jdbc:mysql://localhost:3306/myjdbc";
        Connection connection = null;
        PreparedStatement insertUser = null;
        PreparedStatement checkUser = null;
        ResultSet resultSet = null;

        //Establish connection to database
        try {
            System.out.println("Connecting to database.....");
            connection = DriverManager.getConnection(DB_URL, "root", "Baller2023#");

            //query database
            checkUser = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            checkUser.setString(1, email);

            //execute query
            resultSet = checkUser.executeQuery();

            //check if username already exists and return error msg if true
            if (resultSet.isBeforeFirst()) {
                System.out.println("Username already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username already exists");
                alert.show();
            }
            //the else injects provided data into database and gives access to dasboard if succesfully registered
            else {
                insertUser = connection.prepareStatement("INSERT INTO user(firstName, lastName, email, passWord) " + "VALUES (?,?,?,?)");
                insertUser.setString(1, firstName);
                insertUser.setString(2, lastName);
                insertUser.setString(3, email);
                insertUser.setString(4, passWord);
                insertUser.executeUpdate();

                System.out.println("Updating database.....");

                changeScene(event, "welcome_scene.fxml", "Dashboard", email);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (insertUser != null) {
                try {
                    insertUser.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (checkUser != null) {
                try {
                    checkUser.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }

    }

    //method to handle logging and check provided detailas against database
    public static void logInUser(ActionEvent event, String email, String passWord) {
        final String DB_URL = "jdbc:mysql://localhost:3306/myjdbc";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, "root", "Baller2023#");
            preparedStatement = connection.prepareStatement("SELECT passWord FROM user WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Username does not exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Incorrect credentials");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String userPassword = resultSet.getString("passWord");
                    if (userPassword.equals(passWord)) {
                        changeScene(event, "welcome_scene.fxml", "Dasboard", email);
                    } else {
                        System.out.println("Password does not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Incorrect Password");
                        alert.show();
                    }
                }

            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }

            }
        }

    }
}