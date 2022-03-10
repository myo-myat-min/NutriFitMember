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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

public class FXMLIdealWeightController implements Initializable {
    
    private FadeTransition fadeAnimationHeight = new FadeTransition();
    private FadeTransition fadeAnimationInch = new FadeTransition();
    private FadeTransition fadeAnimationAge = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidHeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidInch = new FadeTransition();
    private FadeTransition fadeAnimationInvalidAge = new FadeTransition();
    
    @FXML
    private Circle completeCircHeight;
    
    @FXML
    private Circle completeCircInch;

    @FXML
    private Circle completeCircAge;
    
    @FXML
    private Circle ageCircle;

    @FXML
    private Circle heightCircle;
    
    @FXML
    private Circle inchCircle;

    @FXML
    private Text invalidHeight;
    
    @FXML
    private Text invalidInch;

    @FXML
    private Text invalidAge;

    @FXML
    private Text emptyNoti;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private SVGPath backArrowShape;

    @FXML
    private Rectangle recthree;

    @FXML
    private Label lblage;

    @FXML
    private JFXSlider sliAge;

    @FXML
    private JFXTextField textfieldAge;

    @FXML
    private Label lblheight;

    @FXML
    private JFXSlider sliHeight;

    @FXML
    private JFXTextField textfieldHeight;

    @FXML
    private JFXRadioButton radioMale;

    @FXML
    private ToggleGroup gender1;

    @FXML
    private JFXRadioButton radioFemale;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXTextField textfieldHeightForInch;

    @FXML
    private JFXRadioButton cm;

    @FXML
    private ToggleGroup height;

    @FXML
    private JFXRadioButton ft;

    @FXML
    private Text robinsonFormula;

    @FXML
    private Text millerFormula;

    @FXML
    private Text devineFormula;

    @FXML
    private Text hamwiFormula;

    @FXML
    private Text femaleAlert;

    @FXML
    private Text maleAlert;
    
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

    private double valueAge;
    private double valueHeight;
    private double valueFt;
    private double valueInch;
    private double finalValueHeight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (cm.isSelected()) {
            textfieldHeightForInch.setText("0");
            textfieldHeightForInch.setDisable(true);
        } else {
            textfieldHeightForInch.clear();
            textfieldHeightForInch.setDisable(false);
        }

        sliAge.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textfieldAge.setText("" + (int) sliAge.getValue());
                valueAge=Double.valueOf(String.valueOf(newValue));
            }
        });
        
        sliHeight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textfieldHeight.setText("" + (int) sliHeight.getValue());
                valueHeight=Double.valueOf(String.valueOf(newValue));
            }
        });
        
    }

    @FXML
    void actionSliderAge(MouseEvent event) {
//        sliAge.valueProperty().addListener((a, b, c) -> {
//            double ansAge = Math.round(Double.valueOf(String.valueOf(c)));
//            String textToSet = String.valueOf((int) ansAge);
//            textfieldAge.setText(textToSet);
//            valueAge = ansAge;
//            System.out.println("Age: " + valueAge);
//        });
    }

    @FXML
    void actionSliderHeight(MouseEvent event) {
//        sliHeight.valueProperty().addListener((a, b, c) -> {
//            double ansHeight = Math.round(Double.valueOf(String.valueOf(c)));
//            String textToSet = String.valueOf((int) ansHeight);
//            textfieldHeight.setText(textToSet);
//            valueHeight = ansHeight;
//            finalValueHeight = Math.round((ansHeight - 153) / 2.54);
//            System.out.println("Height: " + valueHeight);
//            System.out.println("finalValueHeight: " + finalValueHeight);
//        });
    }

    @FXML
    void actionTextfieldAge(ActionEvent event) {
        int ansAge = Integer.valueOf(textfieldAge.getText());
        sliAge.setValue(ansAge);
        valueAge = Double.valueOf(ansAge);
        System.out.println("Age: " + valueAge);
    }

    @FXML
    void actionTextfieldHeight(ActionEvent event) {
        if (cm.isSelected()) {
            textfieldHeightForInch.setEditable(false);
            int ansHeight = Integer.valueOf(textfieldHeight.getText());

            if (ansHeight < 154.0) {

                new Alert(Alert.AlertType.INFORMATION, "Can't calculate IBW of height under 154cm(5Ft 1Inch).", ButtonType.OK).showAndWait();
            } else {
                sliHeight.setValue(ansHeight);
                valueHeight = ansHeight;
                finalValueHeight = Math.round((ansHeight - 153) / 2.54);
                System.out.println("finalValueHeight: " + finalValueHeight);
                System.out.println("Height: " + valueHeight);
            }
        }
        if (ft.isSelected()) {
            textfieldHeightForInch.setEditable(true);
            valueFt = Double.valueOf(textfieldHeight.getText());
            valueInch = Double.valueOf(textfieldHeightForInch.getText());
            finalValueHeight = valueInch;
            System.out.println("finalValueHeight: " + valueInch);
            if (textfieldHeight.getText() != null && textfieldHeightForInch.getText() != null) {
                valueHeight = (valueFt * 30.48) + (valueInch * 2.54);
                System.out.println("Height: " + valueHeight);
            }
        }
    }

    @FXML
    void actionFt(ActionEvent event) {
        textfieldHeightForInch.clear();
        textfieldHeightForInch.setDisable(false);
    }

    @FXML
    void actionCm(ActionEvent event) {
        textfieldHeightForInch.setText("0");
        textfieldHeightForInch.setDisable(true);
    }

    @FXML
    void calculateMousePressedAction(MouseEvent event) {
        if (!textfieldHeight.getText().isEmpty() && !textfieldAge.getText().isEmpty() && !textfieldHeightForInch.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.RED || completeCircAge.getFill() == Color.RED
                    || completeCircInch.getFill() == Color.RED) {
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
        if (!textfieldHeight.getText().isEmpty() && !textfieldAge.getText().isEmpty() && !textfieldHeightForInch.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.GREEN && completeCircAge.getFill() == Color.GREEN
                    && completeCircInch.getFill() == Color.GREEN) {
                if (radioMale.isSelected()) {
                    //Hamwi Formula
                    double H = Math.round(48 + (2.7 * finalValueHeight));
                    //Robinson Formula
                    double R = Math.round(52 + (1.9 * finalValueHeight));
                    //Miller Formula
                    double M = Math.round(56.2 + (1.41 * finalValueHeight));
                    //Devine Formula
                    double D = Math.round(50 + (2.3 * finalValueHeight));

                    robinsonFormula.setText(String.format("%.1f", H));
                    millerFormula.setText(String.valueOf(M));
                    devineFormula.setText(String.valueOf(D));
                    hamwiFormula.setText(String.valueOf(H));

                    System.out.println(R);
                    System.out.println(M);
                    System.out.println(D);
                    System.out.println(H);

                }
                if (radioFemale.isSelected()) {
                    //Hamwi Formula
                    double H = Math.round(45.5 + (2.2 * finalValueHeight));
                    //Robinson Formula
                    double R = Math.round(49 + (1.7 * finalValueHeight));
                    //Miller Formula
                    double M = Math.round(53.1 + (1.36 * finalValueHeight));
                    //Devine Formula
                    double D = Math.round(45.5 + (2.3 * finalValueHeight));

                    robinsonFormula.setText(String.valueOf(R));
                    millerFormula.setText(String.valueOf(M));
                    devineFormula.setText(String.valueOf(D));
                    hamwiFormula.setText(String.valueOf(H));

                    System.out.println(R);
                    System.out.println(M);
                    System.out.println(D);
                    System.out.println(H);

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
        fadeAnimationItems(fadeAnimationInch);
        fadeAnimationItems(fadeAnimationAge);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidHeight);
        fadeAnimationItems(fadeAnimationInvalidInch);
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
        if (!Pattern.matches("[0-9]{1,3}", textfieldHeight.getText())) {
            completeCircHeight.setFill(Color.RED);
            fadeAnimationInvalidHeight.setNode(invalidHeight);
            fadeAnimationInvalidHeight.play();
        } else {
            completeCircHeight.setFill(Color.GREEN);
        }
    }
    
    public void invalidInchCondition() {
        if (!Pattern.matches("[0-9]{1,3}", textfieldHeightForInch.getText())) {
            completeCircInch.setFill(Color.RED);
            fadeAnimationInvalidInch.setNode(invalidInch);
            fadeAnimationInvalidInch.play();
        } else {
            completeCircInch.setFill(Color.GREEN);
        }
    }

    public void invalidAgeCondition() {
        if (!Pattern.matches("[0-9]{1,3}", textfieldAge.getText())) {
            completeCircAge.setFill(Color.RED);
            fadeAnimationInvalidAge.setNode(invalidAge);
            fadeAnimationInvalidAge.play();
        } else {
            completeCircAge.setFill(Color.GREEN);
        }
    }
    
    public void heightCondition() {
        if (textfieldHeight.getText().isEmpty()) {
            fadeAnimationHeight.setNode(heightCircle);
            fadeAnimationHeight.play();
        }
    }
    
    public void inchCondition() {
        if (textfieldHeightForInch.getText().isEmpty()) {
            fadeAnimationInch.setNode(inchCircle);
            fadeAnimationInch.play();
        }
    }

    public void ageCondition() {
        if (textfieldAge.getText().isEmpty()) {
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
        Thread tInch = new Thread() {
            @Override
            public void run() {
                inchCondition();
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
            Logger.getLogger(FXMLCalorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAge.start();
        try {
            tAge.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLCalorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInch.start();
    }
    
    public void invalidControllThreads() {
        Thread tInvalidHeight = new Thread() {
            @Override
            public void run() {
                invalidHeightCondition();
            }
        };
        Thread tInvalidInch = new Thread() {
            @Override
            public void run() {
                invalidInchCondition();
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
            Logger.getLogger(FXMLCalorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidAge.start();
        try {
            tInvalidAge.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLCalorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidInch.start();
    }

    @FXML
    void heightMousePressedAction(MouseEvent event) {
        textfieldHeight.textProperty().addListener((ob, ov, nv) -> {
            if (!textfieldHeight.getText().isEmpty()) {
                try {
                    sliHeight.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }
    
    @FXML
    void ageMousePressedAction(MouseEvent event) {
        textfieldAge.textProperty().addListener((ob, ov, nv) -> {
            if (!textfieldAge.getText().isEmpty()) {
                try {
                    sliAge.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }
    
}
