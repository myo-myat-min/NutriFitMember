package MemberGUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLAllCalculatorsController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXButton bmical;

    @FXML
    private JFXButton bmrcal;

    @FXML
    private JFXButton caloriecal;

    @FXML
    private JFXButton idealweightcal;

    @FXML
    private JFXButton proteincal;

    @FXML
    private JFXButton watercal;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent sceneBMRparent = FXMLLoader.load(getClass().getResource("FXMLBMR.fxml"));
        Scene sceneBMRscene = new Scene(sceneBMRparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(sceneBMRscene);
        app_stage.show();
    }

    @FXML
    private void handleButtonAction1(ActionEvent event) throws IOException {
        Parent sceneBMIparent = FXMLLoader.load(getClass().getResource("FXMLBMI.fxml"));
        Scene sceneBMIscene = new Scene(sceneBMIparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(sceneBMIscene);
        app_stage.show();
    }

    @FXML
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent sceneproteinparent = FXMLLoader.load(getClass().getResource("FXMLProtein.fxml"));
        Scene sceneproteinscene = new Scene(sceneproteinparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(sceneproteinscene);
        app_stage.show();
    }

    @FXML
    private void handleButtonAction3(ActionEvent event) throws IOException {
        Parent scenecalorieparent = FXMLLoader.load(getClass().getResource("FXMLCalorie.fxml"));
        Scene scenecaloriescene = new Scene(scenecalorieparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scenecaloriescene);
        app_stage.show();
    }

    @FXML
    private void handleButtonAction4(ActionEvent event) throws IOException {
        Parent sceneidealweightparent = FXMLLoader.load(getClass().getResource("FXMLIdealweight.fxml"));
        Scene sceneidealweightscene = new Scene(sceneidealweightparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(sceneidealweightscene);
        app_stage.show();
    }
    
    @FXML
    private void handleButtonAction5(ActionEvent event) throws IOException {
        Parent sceneidealweightparent = FXMLLoader.load(getClass().getResource("WaterIntakeScene.fxml"));
        Scene sceneidealweightscene = new Scene(sceneidealweightparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(sceneidealweightscene);
        app_stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MemberMyAccountController trainerMyAccControl = new MemberMyAccountController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(trainerMyAccControl.adminMenuScene(titleRecordTrainerAtt.getText()));
            } catch (IOException ex) {
                Logger.getLogger(ViewScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        adminMenu.setOnMouseReleased((event) -> {
            burgerTask.setRate(burgerTask.getRate() * -1);
            burgerTask.play();

            if (adminDrawer.isShown()) {
                adminDrawer.close();
            } else {
                adminDrawer.open();
            }
        });
    }

}
