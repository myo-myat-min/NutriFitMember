package MemberGUIScenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WaterIntakeController implements Initializable {

    @FXML
    private Rectangle recthree;

    @FXML
    private JFXButton btncalculate;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXRadioButton radiobtnmale;

    @FXML
    private ToggleGroup toggleradiobutton;

    @FXML
    private JFXRadioButton radiobtnfemale;

    @FXML
    private JFXComboBox<Integer> comboboxage;

    @FXML
    private Label lblage;

    @FXML
    private SVGPath backArrowShape;

    @FXML
    void onaction(ActionEvent event) {
        if (radiobtnmale.isSelected()) {
            Integer age = comboboxage.getSelectionModel().getSelectedItem();
            if (age >= 1 && age <= 3) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 1.3 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 4 && age <= 8) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 1.7 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 9 && age <= 13) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 2.4 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 14 && age <= 18) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 3.3 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 19 && age <= 100) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 3.7 letre per day", ButtonType.OK).showAndWait();
            }

        }

        if (radiobtnfemale.isSelected()) {
            Integer age = comboboxage.getSelectionModel().getSelectedItem();
            if (age >= 1 && age <= 3) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 1.3 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 4 && age <= 8) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 1.7 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 9 && age <= 13) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 2.1 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 14 && age <= 18) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 2.3 letre per day", ButtonType.OK).showAndWait();
            } else if (age >= 19 && age <= 100) {
                new Alert(Alert.AlertType.INFORMATION,
                        "You should drink 2.7 letre per day", ButtonType.OK).showAndWait();
            }

        }

    }

    @FXML
    void backArrowMouseEnteredAction(MouseEvent event) {
        backArrowShape.setFill(Color.web("#c4ffc4"));
    }

    @FXML
    void backArrowMouseExitedAction(MouseEvent event) {
        backArrowShape.setFill(Color.WHITE);
    }

    @FXML
    void backArrowMousePressedAction(MouseEvent event) {
        backArrowShape.setFill(Color.web("#35ae50"));
    }

    @FXML
    void backArrowMouseReleasedAction(MouseEvent event) throws IOException {
        Parent sceneparent = FXMLLoader.load(getClass().getResource("FXMLAllCalculators.fxml"));
        Scene scenescene = new Scene(sceneparent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scenescene);
        app_stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        for (int i = 1; i < 101; i++) {
            list.addAll(i);
        }
        comboboxage.setItems(list);
        comboboxage.getSelectionModel().selectFirst();
    }

}
