package MemberGUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.schedule;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewScheduleController implements Initializable {

    @FXML
    private TableView<schedule> memberSeeUpdateTable;

    @FXML
    private TableColumn<schedule, String> id;

    @FXML
    private TableColumn<schedule, String> content;

    @FXML
    private TableColumn<schedule, String> date;

    @FXML
    private TableColumn<schedule, String> trainerid;

    @FXML
    private TableColumn<schedule, String> trainername;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXTextField txtFieldSearchByTrainerName;

    @FXML
    private JFXButton updateButton;

    @FXML
    private Circle dateCircle;

    @FXML
    private JFXDatePicker dateForData;

    @FXML
    private Text emptyNoti;

    @FXML
    void updatePressedAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtFieldSearchByTrainerName.textProperty().addListener((observable, oldValue, newValue) -> {
            dateForData.setValue(null);
            txtFieldSearchByTrainerName.setText(newValue);
            if (newValue!=null) {

                ArrayList<db.schedule> list = db.schedule_table.searchbyname_ForViewSchedule(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {

                ArrayList<db.schedule> list = db.schedule_table.showall();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            }
        });

        dateForData.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldSearchByTrainerName.setText(null);
            dateForData.setValue(newValue);
            if (newValue != null) {
                txtFieldSearchByTrainerName.clear();
                ArrayList<db.schedule> list = db.schedule_table.searchbydate_ForViewSchedule(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {

                ArrayList<db.schedule> list = db.schedule_table.showall();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            }
        });

        txtFieldSearchByTrainerName.textProperty().addListener((ob, ov, nv) -> {
            dateForData.getEditor().clear();
            txtFieldSearchByTrainerName.setText(nv);
        });

        ArrayList<schedule> a = db.schedule_table.showall();

        id.setCellValueFactory(new PropertyValueFactory<>("sche_id"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        date.setCellValueFactory(new PropertyValueFactory<>("schedule_date"));
        trainerid.setCellValueFactory(new PropertyValueFactory<>("id"));
        trainername.setCellValueFactory(new PropertyValueFactory<>("name"));

        memberSeeUpdateTable.getItems().addAll(a);

        MemberMyAccountController memberMyAccControl = new MemberMyAccountController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(memberMyAccControl.adminMenuScene(titleRecordTrainerAtt.getText()));
            } catch (IOException ex) {
                Logger.getLogger(AllWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
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
