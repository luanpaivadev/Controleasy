/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleasy.application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author luanp
 */
public class Main extends Application {
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
    
    @Override
    public void start(Stage loginStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/br/com/controleasy/view/LoginFXML.fxml"));
            Image image = new Image(getClass().getResourceAsStream("/br/com/controleasy/images/favicon.png"));
            loginStage.getIcons().add(image);
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            loginStage.setResizable(false);
            loginStage.show();
            Main.setStage(loginStage);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     public static void start(){
    
    }

}
