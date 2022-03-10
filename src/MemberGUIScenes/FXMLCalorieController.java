package MemberGUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
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
import javafx.scene.control.RadioButton;
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

public class FXMLCalorieController implements Initializable {
    
    private FadeTransition fadeAnimationHeight = new FadeTransition();
    private FadeTransition fadeAnimationInch = new FadeTransition();
    private FadeTransition fadeAnimationWeight = new FadeTransition();
    private FadeTransition fadeAnimationAge = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidHeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidInch = new FadeTransition();
    private FadeTransition fadeAnimationInvalidWeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidAge = new FadeTransition();
    
    @FXML
    private Circle completeCircHeight;
    
    @FXML
    private Circle completeCircInch;

    @FXML
    private Circle completeCircWeight;

    @FXML
    private Circle completeCircAge;
    
    @FXML
    private Circle ageCircle;

    @FXML
    private Circle heightCircle;
    
    @FXML
    private Circle inchCircle;

    @FXML
    private Circle weightCircle;

    @FXML
    private Text invalidWeight;

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
    private Label lblheight;

    @FXML
    private JFXSlider sliderHeight;

    @FXML
    private JFXTextField txtHeight;

    @FXML
    private JFXRadioButton radioMale;

    @FXML
    private ToggleGroup ggg;

    @FXML
    private JFXRadioButton radioFemale;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXRadioButton lb;

    @FXML
    private ToggleGroup gg;

    @FXML
    private JFXRadioButton kg;

    @FXML
    private JFXTextField txtHeightForInch;

    @FXML
    private JFXRadioButton cm;

    @FXML
    private ToggleGroup gengen;

    @FXML
    private JFXRadioButton ft;

    @FXML
    private Text titleRecordTrainerAtt1;

    @FXML
    private Text calorie;

    @FXML
    private Rectangle normal2;

    @FXML
    private Label lblCalorieOne;

    @FXML
    private Rectangle normal21;

    @FXML
    private Label lblCalorieTwo;

    @FXML
    private Rectangle normal22;

    @FXML
    private Label lblCalorieThree;

    @FXML
    private Rectangle normal23;

    @FXML
    private Label lblCalorieFour;

    @FXML
    private Rectangle normal24;

    @FXML
    private Label lblCalorieFive;

    @FXML
    private Rectangle normal25;

    @FXML
    private Label lblCalorieSix;

    @FXML
    private JFXComboBox<String> combo;
    
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
    private double valueWeight;
    private double valueHeight;
    private double valueFt;
    private double valueIn;
    private int man;
    private int woman;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (cm.isSelected()) {
            txtHeightForInch.setText("0");
            txtHeightForInch.setDisable(true);
        } else {
            txtHeightForInch.clear();
            txtHeightForInch.setDisable(false);
        }

        actionComboBox();
        combo.getSelectionModel().selectFirst();
        sliderAge.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtAge.setText("" + (int) sliderAge.getValue());
                valueAge=Double.valueOf(String.valueOf(newValue));
            }
        });
        
        sliderHeight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtHeight.setText("" + (int) sliderHeight.getValue());
                valueHeight=Double.valueOf(String.valueOf(newValue));
            }
        });
        sliderWeight.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtWeight.setText("" + (int) sliderWeight.getValue());
                valueWeight=Double.valueOf(String.valueOf(newValue));
            }
        });
        
    }

    public void actionComboBox() {
        combo.getItems().addAll(
                "Sedentary:Little or no exercise",
                "Light:Exercises 1-3 times/week",
                "Active:Exercises 3-4 times/week",
                "Very Active:Exercises 6-7 times/week",
                "Extra Active:Exercise daily");
    }

    @FXML
    public void actionTextfieldAge(){

        int num = Integer.valueOf(txtAge.getText());
        valueAge = Double.valueOf(num);
        sliderAge.setValue(num);

        System.out.println("Age:" + valueAge);

    }

    @FXML
    public void actionTextfieldHeight() {

        if (cm.isSelected()) {
            int num = Integer.valueOf(txtHeight.getText());
            valueHeight = num;
            sliderHeight.setValue(num);
            System.out.println("Height: " + valueHeight);
        }

        if (ft.isSelected()) {
            valueFt = Integer.valueOf(txtHeight.getText());
            valueFt = valueFt * 30.48;
            System.out.println("valueFt: " + valueFt);

            valueIn = Integer.valueOf(txtHeightForInch.getText());
            valueIn = valueIn * 2.54;
            System.out.println("valueInch: " + valueIn);

            if (txtHeight.getText() != null && txtHeightForInch.getText() != null) {
                valueHeight = valueFt + valueIn;
                System.out.println("Height: " + valueHeight);
            }
        }
    }

    @FXML
    public void actionTextfieldWeight() {

        if (kg.isSelected()) {

            int num = Integer.valueOf(txtWeight.getText());
            valueWeight = num;
            sliderWeight.setValue(num);

        }
        if (lb.isSelected()) {

            int num = Integer.valueOf(txtWeight.getText());
            valueWeight = num / 2.205;
            sliderWeight.setValue(num);

        }
        System.out.println("Weight:" + valueWeight);
    }

    @FXML
    public void actionFt() {
        txtHeightForInch.clear();
        txtHeightForInch.setDisable(false);
    }

    @FXML
    public void actionCm() {
        txtHeightForInch.setText("0");
        txtHeightForInch.setDisable(true);
    }
    
    @FXML
    void calculateMousePressedAction(MouseEvent event) {
        if (!txtHeight.getText().isEmpty() && !txtWeight.getText().isEmpty() && !txtAge.getText().isEmpty() && !txtHeightForInch.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.RED || completeCircWeight.getFill() == Color.RED || completeCircAge.getFill() == Color.RED 
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
        if (!txtHeight.getText().isEmpty() && !txtWeight.getText().isEmpty() && !txtAge.getText().isEmpty() && !txtHeightForInch.getText().isEmpty()) {
            if (completeCircHeight.getFill() == Color.GREEN && completeCircWeight.getFill() == Color.GREEN && completeCircAge.getFill() == Color.GREEN
                    && completeCircInch.getFill() == Color.GREEN) {
                if (radioMale.isSelected()) {

                    man = (int) (Math.round((((10 * valueWeight) + (6.25 * valueHeight)) - (5 * valueAge) + 5)));

                    if (null != combo.getValue()) {
                        switch (combo.getValue()) {
                            case "Sedentary:Little or no exercise":
                                man = (int) (man * 1.2);
                                break;
                            case "Light:Exercises 1-3 times/week":
                                man = (int) (man * 1.375);
                                break;
                            case "Active:Exercises 3-4 times/week":
                                man = (int) (man * 1.55);
                                break;
                            case "Very Active:Exercises 6-7 times/week":
                                man = (int) (man * 1.725);
                                break;
                            case "Extra Active:Exercise daily":
                                man = (int) (man * 1.9);
                                break;
                            default:
                                break;
                        }

                        System.out.println("man:" + man);

                        calorie.setText(String.valueOf(man));
                        System.out.println("calorie" + man);
                        lblCalorieOne.setText(String.valueOf(man - 250));
                        System.out.println("one" + (man - 250));
                        lblCalorieTwo.setText(String.valueOf(man - 500));
                        System.out.println("two" + (man - 500));
                        lblCalorieThree.setText(String.valueOf(man - 1000));
                        System.out.println("three" + (man - 1000));
                        lblCalorieFour.setText(String.valueOf(man + 250));
                        System.out.println("four" + (man + 250));
                        lblCalorieFive.setText(String.valueOf(man + 500));
                        System.out.println("five" + (man + 500));
                        lblCalorieSix.setText(String.valueOf(man + 1000));
                        System.out.println("six" + (man + 1000));
                    }
                }
                if (radioFemale.isSelected()) {

                    woman = (int) (Math.round((((10 * valueWeight) + (6.25 * valueHeight)) - (5 * valueAge) - 161)));

                    if (null != combo.getValue()) {
                        switch (combo.getValue()) {
                            case "Sedentary:Little or no exercise":
                                woman = (int) (woman * 1.2);
                                break;
                            case "Light:Exercises 1-3 times/week":
                                woman = (int) (woman * 1.375);
                                break;
                            case "Active:Exercises 3-4 times/week":
                                woman = (int) (woman * 1.55);
                                break;
                            case "Very Active:Exercises 6-7 times/week":
                                woman = (int) (woman * 1.725);
                                break;
                            case "Extra Active:Exercise daily":
                                woman = (int) (woman * 1.9);
                                break;
                            default:
                                break;
                        }

                        System.out.println("woman:" + woman);

                        calorie.setText(String.valueOf(woman));
                        System.out.println("calorie" + woman);
                        lblCalorieOne.setText(String.valueOf(woman - 250));
                        System.out.println("one" + (woman - 250));
                        lblCalorieTwo.setText(String.valueOf(woman - 500));
                        System.out.println("two" + (woman - 500));
                        lblCalorieThree.setText(String.valueOf(woman - 1000));
                        System.out.println("three" + (woman - 1000));
                        lblCalorieFour.setText(String.valueOf(woman + 250));
                        System.out.println("four" + (woman + 250));
                        lblCalorieFive.setText(String.valueOf(woman + 500));
                        System.out.println("five" + (woman + 500));
                        lblCalorieSix.setText(String.valueOf(woman + 1000));
                        System.out.println("six" + (woman + 1000));
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
        fadeAnimationItems(fadeAnimationInch);
        fadeAnimationItems(fadeAnimationWeight);
        fadeAnimationItems(fadeAnimationAge);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidHeight);
        fadeAnimationItems(fadeAnimationInvalidInch);
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
    
    public void invalidInchCondition() {
        if (!Pattern.matches("[0-9]{1,3}", txtHeightForInch.getText())) {
            completeCircInch.setFill(Color.RED);
            fadeAnimationInvalidInch.setNode(invalidInch);
            fadeAnimationInvalidInch.play();
        } else {
            completeCircInch.setFill(Color.GREEN);
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
    
    public void inchCondition() {
        if (txtHeightForInch.getText().isEmpty()) {
            fadeAnimationInch.setNode(inchCircle);
            fadeAnimationInch.play();
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
        Thread tInch = new Thread() {
            @Override
            public void run() {
                inchCondition();
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
            Logger.getLogger(FXMLCalorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWeight.start();
        try {
            tWeight.join();
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
            Logger.getLogger(FXMLCalorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidWeight.start();
        try {
            tInvalidWeight.join();
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
