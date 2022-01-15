/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemberGUIScenes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("MemberSignInScene.fxml"));
        Scene scene = new Scene(memberSignUpScene, 1360, 700);
        stage.setOnCloseRequest((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to exit?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                System.exit(0);
            } else {
                event.consume();
            }
        });
        stage.setTitle("NutriFit");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

