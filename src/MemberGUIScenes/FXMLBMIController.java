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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLBMIController implements Initializable {
    
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
    private JFXSlider sliderweight;

    @FXML
    private JFXTextField txtweight;

    @FXML
    private Rectangle normal1;

    @FXML
    private Rectangle normal2;

    @FXML
    private Rectangle normal3;

    @FXML
    private Rectangle normal4;

    @FXML
    private Rectangle normal5;

    @FXML
    private Rectangle normal6;

    @FXML
    private Rectangle normal7;

    @FXML
    private Rectangle normal8;

    @FXML
    private Text bmiresult;

    @FXML
    private Text fatresult;

    @FXML
    private Text idealweightresult;

    @FXML
    private SVGPath backArrowShape;

    @FXML
    private Label lblage;

    @FXML
    private JFXSlider sliderage;

    @FXML
    private JFXTextField txtage;

    @FXML
    private Label lblheight;

    @FXML
    private JFXSlider sliderheight;

    @FXML
    private JFXTextField txtheight;

    @FXML
    private JFXRadioButton male;

    @FXML
    private ToggleGroup gengen;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXButton buttoncalculate;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;
    
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

    private ToggleGroup genderToggleGroup;
    private Integer valueAge;
    private Double valueheight;
    private Double valueweight;

    @FXML
    void calculateMousePressedAction(MouseEvent event) {
        if (!txtheight.getText().isEmpty() && !txtweight.getText().isEmpty() && !txtage.getText().isEmpty()) {
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
        if (!txtheight.getText().isEmpty() && !txtweight.getText().isEmpty() && !txtage.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.GREEN && completeCircWeight.getFill() == Color.GREEN && completeCircAge.getFill() == Color.GREEN) {
                
                /*Parent scene2parent = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene scene2scene = new Scene(scene2parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2scene);
        app_stage.show();*/
                double idealweight = 0;
                double BMI = 0;
                double fat = 0;

                String input = txtage.getText();
                double age = Double.parseDouble(input);

                String input1 = txtheight.getText();
                double height = Double.parseDouble(input1);

                String input2 = txtweight.getText();
                double weight = Double.parseDouble(input2);

                if (male.isSelected()) {
                    idealweight = 22 * (height * 0.01) * (height * 0.01);
                    idealweightresult.setText(String.valueOf(idealweight));
                    idealweightresult.setText(String.format("%.1f", idealweight) + "kg");
                    BMI = weight / (height * 0.01 * height * 0.01);
                    bmiresult.setText(String.valueOf(BMI));
                    bmiresult.setText(String.format("%.1f", BMI));
                    fat = -44.988 + (0.503 * age) + (10.689 * 0) + (3.172 * BMI) - (0.026 * BMI * BMI)
                            + (0.181 * BMI * 0) - (0.02 * BMI * age) - (0.005 * BMI * BMI * 0) + (0.00021 * BMI * BMI * age);
                    fatresult.setText(String.valueOf(fat));
                    fatresult.setText(String.format("%.1f", fat));
                } else {
                    idealweight = 22 * (height * 0.01 - 0.1) * (height * 0.01 - 0.1);
                    idealweightresult.setText(String.valueOf(idealweight));
                    idealweightresult.setText(String.format("%.1f", idealweight) + "kg");
                    BMI = weight / (height * 0.01 * height * 0.01);
                    bmiresult.setText(String.valueOf(BMI));
                    bmiresult.setText(String.format("%.1f", BMI));
                    fat = -44.988 + (0.503 * age) + (10.689 * 1) + (3.172 * BMI) - (0.026 * BMI * BMI)
                            + (0.181 * BMI * 1) - (0.02 * BMI * age) - (0.005 * BMI * BMI * 1) + (0.00021 * BMI * BMI * age);
                    fatresult.setText(String.valueOf(fat));
                    fatresult.setText(String.format("%.1f", fat));

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
        if (!Pattern.matches("[0-9]{1,3}", txtheight.getText())) {
            completeCircHeight.setFill(Color.RED);
            fadeAnimationInvalidHeight.setNode(invalidHeight);
            fadeAnimationInvalidHeight.play();
        } else {
            completeCircHeight.setFill(Color.GREEN);
        }
    }

    public void invalidWeightCondition() {
        if (!Pattern.matches("[0-9]{1,3}", txtweight.getText())) {
            completeCircWeight.setFill(Color.RED);
            fadeAnimationInvalidWeight.setNode(invalidWeight);
            fadeAnimationInvalidWeight.play();
        } else {
            completeCircWeight.setFill(Color.GREEN);
        }
    }
    
    public void invalidAgeCondition() {
        if (!Pattern.matches("[0-9]{1,3}", txtage.getText())) {
            completeCircAge.setFill(Color.RED);
            fadeAnimationInvalidAge.setNode(invalidAge);
            fadeAnimationInvalidAge.play();
        } else {
            completeCircAge.setFill(Color.GREEN);
        }
    }
    
    public void heightCondition() {
        if (txtheight.getText().isEmpty()) {
            fadeAnimationHeight.setNode(heightCircle);
            fadeAnimationHeight.play();
        }
    }

    public void weightCondition() {
        if (txtweight.getText().isEmpty()) {
            fadeAnimationWeight.setNode(weightCircle);
            fadeAnimationWeight.play();
        }
    }
    
    public void ageCondition() {
        if (txtage.getText().isEmpty()) {
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sliderage.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtage.setText("" + (int) sliderage.getValue());
            }
        });
        sliderheight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtheight.setText("" + (int) sliderheight.getValue());
            }
        });
        sliderweight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtweight.setText("" + (int) sliderweight.getValue());
            }
        });
    }   
    
    @FXML
    void weightMousePressedAction(MouseEvent event) {
        txtweight.textProperty().addListener((ob, ov, nv) -> {
            if (!txtweight.getText().isEmpty()) {
                try {
                    sliderweight.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }

    @FXML
    void heightMousePressedAction(MouseEvent event) {
        txtheight.textProperty().addListener((ob, ov, nv) -> {
            if (!txtheight.getText().isEmpty()) {
                try {
                    sliderheight.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }
    
    @FXML
    void ageMousePressedAction(MouseEvent event) {
        txtage.textProperty().addListener((ob, ov, nv) -> {
            if (!txtage.getText().isEmpty()) {
                try {
                    sliderage.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }
    
}
