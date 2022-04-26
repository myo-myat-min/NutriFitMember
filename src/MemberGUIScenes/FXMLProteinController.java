package MemberGUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLProteinController implements Initializable {

    private FadeTransition fadeAnimationWeight = new FadeTransition();
    private FadeTransition fadeAnimationAge = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidWeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidAge = new FadeTransition();

    @FXML
    private Circle completeCircWeight;

    @FXML
    private Circle completeCircAge;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private SVGPath backArrowShape;

    @FXML
    private Rectangle recthree;

    @FXML
    private Label lblweight;

    @FXML
    private JFXSlider sliderWeight;

    @FXML
    private JFXTextField txtWeight;

    @FXML
    private Label lblage;

    @FXML
    private JFXSlider sliderAge;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXRadioButton radioMale;

    @FXML
    private ToggleGroup ggg;

    @FXML
    private JFXRadioButton radioFemale;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXComboBox<String> combo;

    @FXML
    private Circle ageCircle;

    @FXML
    private Circle weightCircle;

    @FXML
    private Text invalidWeight;

    @FXML
    private Text invalidAge;

    @FXML
    private Text emptyNoti;

    @FXML
    private Text lbl;

    @FXML
    private TableView<persontable> table;

    @FXML
    private TableColumn<persontable, String> tblLife;

    @FXML
    private TableColumn<persontable, String> tblPro;

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

    ObservableList<persontable> list2 = FXCollections.observableArrayList(
            new persontable("Age 1-3", "13"),
            new persontable("Age 4-8", "13"),
            new persontable("Age 9-13", "13"),
            new persontable("Age 14-18(Girl)", "13"),
            new persontable("Age 14-18(Boy)", "13"),
            new persontable("Age 19-70+(Woman)", "13"),
            new persontable("Age 19-70+(Man)", "13")
    );

    @FXML
    void calculateMousePressedAction(MouseEvent event) {

        if (!txtWeight.getText().isEmpty() && !txtAge.getText().isEmpty()) {
            if (completeCircWeight.getFill() == Color.RED || completeCircAge.getFill() == Color.RED) {
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }

    }

    @FXML
    void calculateMouseReleasedAction(MouseEvent event) {
        if (!txtWeight.getText().isEmpty() && !txtAge.getText().isEmpty()) {
            if (completeCircWeight.getFill() == Color.GREEN && completeCircAge.getFill() == Color.GREEN) {
                double result = 0;
                String inputAge = txtAge.getText();
                int age = Integer.parseInt(inputAge);

                String inputWeight = txtWeight.getText();
                int weight = Integer.parseInt(inputWeight);

                if (age >= 19 && age < 70 && ("Little or no exercise".equals(combo.getValue()) || "Exercises 1-3 times/week".equals(combo.getValue()))) {
                    result = weight * 0.36;
                    lbl.setText(String.format("You need at least %.0f grams per days", result));
                } else if (age >= 19 && age < 70 && ("Exercises 4-5 times/week".equals(combo.getValue()) || "Daily exercise or intense exercise 3-4 times/week".equals(combo.getValue()))) {
                    result = (weight / 2.2) * 1.4;
                    lbl.setText(String.format("You need at least %.0f grams per days", result));
                } else if (age >= 19 && age < 70 && ("Intense exercises 6-7 times/week".equals(combo.getValue()) || "Very intense exercise daily or physical job".equals(combo.getValue()))) {
                    result = (weight / 2.2) * 1.8;
                    lbl.setText(String.format("You need at least %.0f grams per days", result));
                } else if (age >= 70 && radioMale.isSelected()) {
                    lbl.setText("You need at least 56 grams per days");
                } else if (age >= 70 && radioFemale.isSelected()) {
                    lbl.setText("You need at least 46 grams per days");
                }

            } else {
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    @FXML
    void weightMousePressedAction(MouseEvent event) {
        txtWeight.textProperty().addListener((ob, ov, nv) -> {
            if (!txtWeight.getText().isEmpty()) {
                try {
                    sliderWeight.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }

    @FXML
    void ageMousePressedAction(MouseEvent event) {
        txtAge.textProperty().addListener((ob, ov, nv) -> {
            if (!txtAge.getText().isEmpty()) {
                try {
                    sliderAge.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }

    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationWeight);
        fadeAnimationItems(fadeAnimationAge);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidWeight);
        fadeAnimationItems(fadeAnimationInvalidAge);
    }

    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }

    public void invalidWeightCondition() {
        if (!Pattern.matches("[0-9]{1,3}", txtWeight.getText())) {
            completeCircWeight.setFill(Color.RED);
            fadeAnimationInvalidWeight.setNode(invalidWeight);
            fadeAnimationInvalidWeight.play();
        } else {
            completeCircWeight.setFill(Color.GREEN);
        }
    }

    public void invalidAgeCondition() {
        if (!Pattern.matches("[0-9]{1,3}", txtAge.getText())) {
            completeCircAge.setFill(Color.RED);
            fadeAnimationInvalidAge.setNode(invalidAge);
            fadeAnimationInvalidAge.play();
        } else {
            completeCircAge.setFill(Color.GREEN);
        }
    }

    public void weightCondition() {
        if (txtWeight.getText().isEmpty()) {
            fadeAnimationWeight.setNode(weightCircle);
            fadeAnimationWeight.play();
        }
    }

    public void ageCondition() {
        if (txtAge.getText().isEmpty()) {
            fadeAnimationAge.setNode(ageCircle);
            fadeAnimationAge.play();
        }
    }

    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    public void emptyControllThreads() {
        Thread tWeight = new Thread() {
            @Override
            public void run() {
                weightCondition();
            }
        };
        Thread tAge = new Thread() {
            @Override
            public void run() {
                ageCondition();
            }
        };
        tWeight.start();
        try {
            tWeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLBMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAge.start();
    }

    public void invalidControllThreads() {
        Thread tInvalidWeight = new Thread() {
            @Override
            public void run() {
                invalidWeightCondition();
            }
        };
        Thread tInvalidAge = new Thread() {
            @Override
            public void run() {
                invalidAgeCondition();
            }
        };
        tInvalidWeight.start();
        try {
            tInvalidWeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLBMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidAge.start();
    }

    @FXML
    void combo(ActionEvent event) {
        String s = combo.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Little or no exercise",
                "Exercises 1-3 times/week",
                "Exercises 4-5 times/week",
                "Daily exercise or intense exercise 3-4 times/week",
                "Intense exercises 6-7 times/week",
                "Very intense exercise daily or physical job");
        combo.setItems(list);
        combo.getSelectionModel().selectFirst();

        sliderAge.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtAge.setText("" + (int) sliderAge.getValue());
            }
        });

        sliderWeight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtWeight.setText("" + (int) sliderWeight.getValue());
            }
        });

        tblLife.setCellValueFactory(new PropertyValueFactory<persontable, String>("tblLife"));
        tblPro.setCellValueFactory(new PropertyValueFactory<persontable, String>("tblPro"));

        table.setItems(list2);
    }

}
