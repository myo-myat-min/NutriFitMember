package MemberGUIScenes;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HealthyTipsController implements Initializable {
    @FXML
    private AnchorPane scrollbar;

    @FXML
    private Label label;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private ImageView image6;

    @FXML
    private ImageView image8;

    @FXML
    private ImageView image7;

    @FXML
    private ImageView image9;

    @FXML
    private ImageView image10;

    @FXML
    private ImageView image11;

    @FXML
    private ImageView image12;

    @FXML
    private ImageView image13;

    @FXML
    private ImageView image14;

    @FXML
    private ImageView image15;

    @FXML
    private ImageView image16;

    @FXML
    private ImageView image17;

    @FXML
    private ImageView image18;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;
    
    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;
    
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
