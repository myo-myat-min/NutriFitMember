package MemberGUIScenes;

import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLBMRController implements Initializable {
    
    private FadeTransition fadeAnimationHeight = new FadeTransition();
    private FadeTransition fadeAnimationWeight = new FadeTransition();
    private FadeTransition fadeAnimationAge = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidHeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidWeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidAge = new FadeTransition();
    
    @FXML
    private Circle completeCircHeight;

    @FXML
    private Circle completeCircWeight;

    @FXML
    private Circle completeCircAge;
    
    @FXML
    private Circle ageCircle;

    @FXML
    private Circle heightCircle;

    @FXML
    private Circle weightCircle;

    @FXML
    private Text invalidWeight;

    @FXML
    private Text invalidHeight;

    @FXML
    private Text invalidAge;

    @FXML
    private Text emptyNoti;

    @FXML
    private Rectangle recthree;

    @FXML
    private Label lblweight;

    @FXML
    private JFXSlider sliderWeight;

    @FXML
    private JFXTextField txtWeight;

    @FXML
    private SVGPath backArrowShape;

    @FXML
    private Label lblage;

    @FXML
    private JFXSlider sliderAge;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private Label lblheight;

    @FXML
    private JFXSlider sliderHeight;

    @FXML
    private JFXTextField txtHeight;

    @FXML
    private JFXRadioButton radioMale;

    @FXML
    private ToggleGroup gg;
    
    @FXML
    private ToggleGroup ggg;

    @FXML
    private JFXRadioButton radioFemale;

    @FXML
    private JFXButton btn;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXRadioButton lb;

    @FXML
    private JFXRadioButton kg;

    @FXML
    private Text lblMsg;

    @FXML
    private Text lblBmr;

    @FXML
    private Text lblDay;

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

    @FXML
    void calculateMousePressedAction(MouseEvent event) {
        if (!txtHeight.getText().isEmpty() && !txtWeight.getText().isEmpty() && !txtAge.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.RED || completeCircWeight.getFill() == Color.RED || completeCircAge.getFill() == Color.RED) {
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
        if (!txtHeight.getText().isEmpty() && !txtWeight.getText().isEmpty() && !txtAge.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.GREEN && completeCircWeight.getFill() == Color.GREEN && completeCircAge.getFill() == Color.GREEN) {

                double bmr = 0;
                String inputAge = txtAge.getText();
                int age = Integer.parseInt(inputAge);

                String inputWeight = txtWeight.getText();
                int weight = Integer.parseInt(inputWeight);

                String inputHeight = txtHeight.getText();
                int height = Integer.parseInt(inputHeight);

                if (radioMale.isSelected()) {
                    if (kg.isSelected()) {
                        bmr = 10 * weight + 6.25 * height - 5 * age + 5;
                        lblBmr.setText("" + bmr);
                    }
                    if (lb.isSelected()) {
                        bmr = (10 * weight * 0.45 + 6.25 * height - 5 * age + 5) + 2;
                        lblBmr.setText("" + bmr);
                    }
                }
                if (radioFemale.isSelected()) {
                    if (kg.isSelected()) {
                        bmr = 10 * weight + 6.25 * height - 5 * age - 161;
                        lblBmr.setText("" + bmr);
                    }
                    if (lb.isSelected()) {
                        bmr = (10 * weight * 0.45 + 6.25 * height - 5 * age - 161) + 2;
                        lblBmr.setText("" + bmr);
                    }
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
    
    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationHeight);
        fadeAnimationItems(fadeAnimationWeight);
        fadeAnimationItems(fadeAnimationAge);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidHeight);
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
    
    public void invalidHeightCondition() {
        if (!Pattern.matches("[0-9]{1,3}", txtHeight.getText())) {
            completeCircHeight.setFill(Color.RED);
            fadeAnimationInvalidHeight.setNode(invalidHeight);
            fadeAnimationInvalidHeight.play();
        } else {
            completeCircHeight.setFill(Color.GREEN);
        }
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
    
    public void heightCondition() {
        if (txtHeight.getText().isEmpty()) {
            fadeAnimationHeight.setNode(heightCircle);
            fadeAnimationHeight.play();
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
        Thread tHeight = new Thread() {
            @Override
            public void run() {
                heightCondition();
            }
        };
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
        tHeight.start();
        try {
            tHeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLBMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWeight.start();
        try {
            tWeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLBMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAge.start();
    }
    
    public void invalidControllThreads() {
        Thread tInvalidHeight = new Thread() {
            @Override
            public void run() {
                invalidHeightCondition();
            }
        };
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
        tInvalidHeight.start();
        try {
            tInvalidHeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLBMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidWeight.start();
        try {
            tInvalidWeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLBMIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidAge.start();
    }

    public void initialize(URL url, ResourceBundle rb) {
        sliderAge.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtAge.setText("" + (int) sliderAge.getValue());
            }
        });
        
        sliderHeight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtHeight.setText("" + (int) sliderHeight.getValue());
            }
        });
        sliderWeight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtWeight.setText("" + (int) sliderWeight.getValue());
            }
        });
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
    void heightMousePressedAction(MouseEvent event) {
        txtHeight.textProperty().addListener((ob, ov, nv) -> {
            if (!txtHeight.getText().isEmpty()) {
                try {
                    sliderHeight.setValue(Double.parseDouble(nv));
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
    
}
