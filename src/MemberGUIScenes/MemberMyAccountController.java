package MemberGUIScenes;

import db.Member;
import db.Membertable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemberMyAccountController implements Initializable {

    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationEmail = new FadeTransition();
    private FadeTransition fadeAnimationPass = new FadeTransition();
    private FadeTransition fadeAnimationPassComfirm = new FadeTransition();
    private FadeTransition fadeAnimationNrc = new FadeTransition();
    private FadeTransition fadeAnimationPhone = new FadeTransition();
    private FadeTransition fadeAnimationAddress = new FadeTransition();
    private FadeTransition fadeAnimationDOB = new FadeTransition();
    private FadeTransition fadeAnimationHeight = new FadeTransition();
    private FadeTransition fadeAnimationWeight = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationPassWrongNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidName = new FadeTransition();
    private FadeTransition fadeAnimationInvalidEmail = new FadeTransition();
    private FadeTransition fadeAnimationInvalidNRC = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPhone = new FadeTransition();
    private FadeTransition fadeAnimationInvalidHeight = new FadeTransition();
    private FadeTransition fadeAnimationInvalidWeight = new FadeTransition();
    private FadeTransition fadeAnimationWeakPassNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPassNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidDOB = new FadeTransition();

    protected JFXButton myAccButton = new JFXButton("My Account");
    protected JFXButton viewWorkoutButton = new JFXButton("View Workout");
    protected JFXButton viewScheduleButton = new JFXButton("View Schedule");
    protected JFXButton calculatorsButton = new JFXButton("Calculators");
    protected JFXButton healthyTipsButton = new JFXButton("Healthy Tips");
    protected JFXButton logOutButton = new JFXButton("Log Out");

    @FXML
    protected Circle nameCircle;

    @FXML
    protected Circle emailCircle;

    @FXML
    protected Circle passCircle;

    @FXML
    protected Circle confirmCircle;

    @FXML
    protected Circle nrcCircle;

    @FXML
    protected Circle dobCircle;

    @FXML
    protected Circle heightCircle;

    @FXML
    protected Circle weightCircle;

    @FXML
    protected Rectangle welcomeTemplate;

    @FXML
    protected Text txtWelcome;

    @FXML
    protected Text txtNutriFit;

    @FXML
    protected Rectangle titleTemplate;

    @FXML
    protected JFXTextField memberName;

    @FXML
    protected JFXPasswordField pass;

    @FXML
    protected JFXPasswordField confirmPass;

    @FXML
    protected JFXTextField nrc;

    @FXML
    protected JFXTextField phone;

    @FXML
    protected JFXRadioButton male;

    @FXML
    protected ToggleGroup gender;

    @FXML
    protected JFXRadioButton female;

    @FXML
    protected JFXDatePicker dob;

    @FXML
    protected JFXSlider sliHeight;

    @FXML
    protected JFXTextField height;

    @FXML
    protected JFXSlider sliWeight;

    @FXML
    protected JFXTextField weight;

    @FXML
    protected JFXTextArea medicalData;

    @FXML
    protected Text titleMemberSignUp;

    @FXML
    protected JFXButton signUp;

    @FXML
    protected JFXHamburger adminMenu;

    @FXML
    protected Circle phoneCircle;

    @FXML
    protected JFXTextField email;

    @FXML
    protected Circle addressCircle;

    @FXML
    protected JFXTextArea address;

    @FXML
    protected Text emptyNoti;

    @FXML
    protected Text passWrongNoti;

    @FXML
    protected Text invalidName;

    @FXML
    protected Text invalidEmail;

    @FXML
    protected Text invalidPhone;

    @FXML
    protected Text invalidNrc;

    @FXML
    protected Text invalidWeight;

    @FXML
    protected Text invalidHeight;

    @FXML
    private Circle completeCircName;

    @FXML
    private Circle completeCircEmail;

    @FXML
    private Circle completeCircPass;

    @FXML
    private Circle completeCircPhone;

    @FXML
    private Circle completeCircNRC;

    @FXML
    private Circle completeCircHeight;

    @FXML
    private Circle completeCircWeight;

    @FXML
    private Text tooWeakPass;

    @FXML
    private Circle completeCircWeakPass;

    @FXML
    private Text invalidPass;

    @FXML
    private Circle completeCircInvalidPass;

    @FXML
    protected JFXDrawer adminDrawer;

    @FXML
    private Circle completeCircDOB;

    @FXML
    private Text invalidDOB;

//    @FXML
//    private JFXComboBox<String> cboPackage;
    
    @FXML
    private JFXCheckBox editableMode;
    
    public void addingData(String name, String email, String pass, String nrc, String phone, LocalDate dob, 
            String gender, String address, int height, int weight, String medicalData){
        memberName.setText(name);
        this.email.setText(email);
        this.pass.setText(pass);
        confirmPass.setText(pass);
        this.nrc.setText(nrc);
        this.phone.setText(phone);
        this.dob.setValue(dob);
        if(gender.equals("Male")){
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        this.address.setText(address);
        this.sliHeight.setValue(height);
        this.sliWeight.setValue(weight);
        this.height.setText(String.valueOf(height));
        this.weight.setText(String.valueOf(weight));
        this.medicalData.setText(medicalData);
    }

//    private ArrayList<db.Membership> m = db.membership_table.showall();

    @FXML
    void signUpPressedAction(MouseEvent event) {
        if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()
                && !confirmPass.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null && !height.getText().isEmpty()
                && !weight.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED || completeCircPass.getFill() == Color.RED
                    || completeCircPhone.getFill() == Color.RED || completeCircNRC.getFill() == Color.RED
                    || completeCircHeight.getFill() == Color.RED || completeCircWeight.getFill() == Color.RED
                    || completeCircInvalidPass.getFill() == Color.RED || completeCircWeakPass.getFill() == Color.RED
                    || completeCircDOB.getFill() == Color.RED) {
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
    void signUpReleasedAction(MouseEvent event) {
        if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()
                && !confirmPass.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null && !height.getText().isEmpty()
                && !weight.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN
                    && completeCircPhone.getFill() == Color.GREEN && completeCircNRC.getFill() == Color.GREEN
                    && completeCircHeight.getFill() == Color.GREEN && completeCircWeight.getFill() == Color.GREEN
                    && completeCircInvalidPass.getFill() == Color.GREEN && completeCircWeakPass.getFill() == Color.GREEN
                    && completeCircDOB.getFill() == Color.GREEN) {

                String maleOrFemale;
                if (male.isSelected()) {
                    maleOrFemale = "Male";
                } else {
                    maleOrFemale = "Female";
                }

                try {
                    Stage a = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    String member_id = db.Membertable.getMemberID_ByEmail(a.getTitle());

                    db.Membertable.Update_Member(new Member(member_id, memberName.getText(), pass.getText(), dob.getValue(), maleOrFemale, 
                            Integer.parseInt(weight.getText()), Integer.parseInt(height.getText()), email.getText(), nrc.getText(), phone.getText(), 
                            address.getText(), medicalData.getText()));

                    new Alert(Alert.AlertType.INFORMATION, "Member data are recorded", ButtonType.OK).showAndWait();
                    a.setTitle(email.getText());
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate NRC or email is not allowed", ButtonType.OK).showAndWait();
                }

//                Member m = new Member(memberName.getText(), pass.getText(), dob.getValue(), maleOrFemale, (int) sliWeight.getValue(),
//                        (int) sliHeight.getValue(), email.getText(), nrc.getText(), phone.getText(), address.getText(),
//                        medicalData.getText());
//                String ms = cboPackage.getSelectionModel().getSelectedItem();
//
//                this.m.forEach((t) -> {
//                    if (t.getName().equals(ms)) {
//                        String[] s = t.getName().split("-");
//                        System.out.println(s[0]);
//                        System.out.println(t.getId());
//                        System.out.println(m);
//                        LocalDate enddate = LocalDate.now().plusMonths(Integer.parseInt(s[0]));
//                        try {
//                            Membertable.add_member_invoice(m, 1, enddate, t.getId());
//                            Alert alertRecorded = new Alert(Alert.AlertType.INFORMATION, "Member data are recorded", ButtonType.OK);
//                            alertRecorded.showAndWait();
//                            if (alertRecorded.getResult() == ButtonType.OK) {
//                                try {
//                                    Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("InvoiceScene.fxml"));
//                                    Scene scene = new Scene(trainerSignUpScene, 1360, 700);
//                                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                                    window.setResizable(false);
//                                    window.setScene(scene);
//                                    window.show();
//                                } catch (IOException ex) {
//                                    Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        } catch (SQLException ex) {
////                            Alert alertNrc = new Alert(Alert.AlertType.ERROR, "Duplicated NRC is not allowed", ButtonType.OK);
////                            alertNrc.showAndWait();
//                               ex.printStackTrace();
////                            if(alertNrc.getResult() == ButtonType.OK){
////                                event.consume();
////                            }
//                        }
//                    }
//                });

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
        fadeAnimationItems(fadeAnimationMember);
        fadeAnimationItems(fadeAnimationEmail);
        fadeAnimationItems(fadeAnimationPass);
        fadeAnimationItems(fadeAnimationPassComfirm);
        fadeAnimationItems(fadeAnimationNrc);
        fadeAnimationItems(fadeAnimationPhone);
        fadeAnimationItems(fadeAnimationAddress);
        fadeAnimationItems(fadeAnimationDOB);
        fadeAnimationItems(fadeAnimationHeight);
        fadeAnimationItems(fadeAnimationWeight);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidName);
        fadeAnimationItems(fadeAnimationInvalidEmail);
        fadeAnimationItems(fadeAnimationInvalidPhone);
        fadeAnimationItems(fadeAnimationInvalidNRC);
        fadeAnimationItems(fadeAnimationInvalidDOB);
        fadeAnimationItems(fadeAnimationInvalidHeight);
        fadeAnimationItems(fadeAnimationInvalidWeight);
        fadeAnimationItems(fadeAnimationPassWrongNoti);
        fadeAnimationItems(fadeAnimationWeakPassNoti);
        fadeAnimationItems(fadeAnimationInvalidPassNoti);
    }

    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }

    public void invalidNameCondition() {
        if (!Pattern.matches("[a-zA-Z\\s]+", memberName.getText())) {
            completeCircName.setFill(Color.RED);
            fadeAnimationInvalidName.setNode(invalidName);
            fadeAnimationInvalidName.play();
        } else {
            completeCircName.setFill(Color.GREEN);
        }
    }

    public void invalidEmailCondition() {
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email.getText())) {
            completeCircEmail.setFill(Color.RED);
            fadeAnimationInvalidEmail.setNode(invalidEmail);
            fadeAnimationInvalidEmail.play();
        } else {
            completeCircEmail.setFill(Color.GREEN);
        }
    }

    public void differentPassCondition() {
        if (!pass.getText().equals(confirmPass.getText())) {
            completeCircPass.setFill(Color.RED);
            fadeAnimationPassWrongNoti.setNode(passWrongNoti);
            fadeAnimationPassWrongNoti.play();
        } else {
            completeCircPass.setFill(Color.GREEN);
        }
    }

    public void weakPassCondition() {
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{1,}$", pass.getText())) {
            completeCircWeakPass.setFill(Color.RED);
            fadeAnimationPassWrongNoti.setNode(tooWeakPass);
            fadeAnimationPassWrongNoti.play();
        } else {
            completeCircWeakPass.setFill(Color.GREEN);
        }
    }

    public void invalidPassCondition() {
        int count = 0;
        for (int i = 1; i <= pass.getText().length(); i++) {
            count++;
        }
        if (count > 20 || count < 8) {
            completeCircInvalidPass.setFill(Color.RED);
            fadeAnimationInvalidPassNoti.setNode(invalidPass);
            fadeAnimationInvalidPassNoti.play();
        } else {
            completeCircInvalidPass.setFill(Color.GREEN);
        }
    }

    public void invalidPhoneCondition() {
        if (!phone.getText().matches("[0]{1}[9]{1}[\\d]{9}")) {
            completeCircPhone.setFill(Color.RED);
            fadeAnimationInvalidPhone.setNode(invalidPhone);
            fadeAnimationInvalidPhone.play();
        } else {
            completeCircPhone.setFill(Color.GREEN);
        }
    }

    public void invalidNRCCondition() {
        if (!Pattern.matches("[1-9]{1,3}/[A-Z]+[(NFPT)]{3}[\\d]{6}", nrc.getText())) {
            completeCircNRC.setFill(Color.RED);
            fadeAnimationInvalidNRC.setNode(invalidNrc);
            fadeAnimationInvalidNRC.play();
        } else {
            completeCircNRC.setFill(Color.GREEN);
        }
    }

    public void invalidDOBCondition() {
        if (LocalDate.now().getYear() - dob.getValue().getYear() < 14) {
            completeCircDOB.setFill(Color.RED);
            fadeAnimationInvalidDOB.setNode(invalidDOB);
            fadeAnimationInvalidDOB.play();
        } else {
            completeCircDOB.setFill(Color.GREEN);
        }
    }

    public void invalidHeightCondition() {
        if (!Pattern.matches("[0-9]{1,3}", height.getText())) {
            completeCircHeight.setFill(Color.RED);
            fadeAnimationInvalidHeight.setNode(invalidHeight);
            fadeAnimationInvalidHeight.play();
        } else {
            completeCircHeight.setFill(Color.GREEN);
        }
    }

    public void invalidWeightCondition() {
        if (!Pattern.matches("[0-9]{1,3}", weight.getText())) {
            completeCircWeight.setFill(Color.RED);
            fadeAnimationInvalidWeight.setNode(invalidWeight);
            fadeAnimationInvalidWeight.play();
        } else {
            completeCircWeight.setFill(Color.GREEN);
        }
    }

    public void memberCondition() {
        if (memberName.getText().isEmpty()) {
            fadeAnimationMember.setNode(nameCircle);
            fadeAnimationMember.play();
        }
    }

    public void emailCondition() {
        if (email.getText().isEmpty()) {
            fadeAnimationEmail.setNode(emailCircle);
            fadeAnimationEmail.play();
        }
    }

    public void passCondition() {
        if (pass.getText().isEmpty()) {
            fadeAnimationPass.setNode(passCircle);
            fadeAnimationPass.play();
        }
    }

    public void passConfirmCondition() {
        if (confirmPass.getText().isEmpty()) {
            fadeAnimationPassComfirm.setNode(confirmCircle);
            fadeAnimationPassComfirm.play();
        }
    }

    public void nrcCondition() {
        if (nrc.getText().isEmpty()) {
            fadeAnimationNrc.setNode(nrcCircle);
            fadeAnimationNrc.play();
        }
    }

    public void phoneCondition() {
        if (phone.getText().isEmpty()) {
            fadeAnimationPhone.setNode(phoneCircle);
            fadeAnimationPhone.play();
        }
    }

    public void addressCondition() {
        if (address.getText().isEmpty()) {
            fadeAnimationAddress.setNode(addressCircle);
            fadeAnimationAddress.play();
        }
    }

    public void datePickerCondition() {
        if (dob.getValue() == null) {
            fadeAnimationDOB.setNode(dobCircle);
            fadeAnimationDOB.play();
        }
    }

    public void heightCondition() {
        if (height.getText().isEmpty()) {
            fadeAnimationHeight.setNode(heightCircle);
            fadeAnimationHeight.play();
        }
    }

    public void weightCondition() {
        if (weight.getText().isEmpty()) {
            fadeAnimationWeight.setNode(weightCircle);
            fadeAnimationWeight.play();
        }
    }

    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    @FXML
    void confirmAction(ActionEvent event) {
        nrc.requestFocus();
        nrc.selectAll();
    }

    @FXML
    void emailAction(ActionEvent event) {
        pass.requestFocus();
        pass.selectAll();
    }

    @FXML
    void memberAction(ActionEvent event) {
        email.requestFocus();
        email.selectAll();
    }

    @FXML
    void nrcAction(ActionEvent event) {
        phone.requestFocus();
        phone.selectAll();
    }

    @FXML
    void passAction(ActionEvent event) {
        confirmPass.requestFocus();
        confirmPass.selectAll();
    }

    @FXML
    void phoneAction(ActionEvent event) {
        address.requestFocus();
        address.selectAll();
    }

    @FXML
    void heightScrollMouseMovedAction(MouseEvent event) {
        sliHeight.valueProperty().addListener((ob, ov, nv) -> {
            height.setText(String.format("%.0f", nv));
        });
    }

    @FXML
    void weightScrollMouseMovedAction(MouseEvent event) {
        sliWeight.valueProperty().addListener((ob, ov, nv) -> {
            weight.setText(String.format("%.0f", nv));
        });
    }

    @FXML
    void weightMousePressedAction(MouseEvent event) {
        weight.textProperty().addListener((ob, ov, nv) -> {
            try {
                sliWeight.setValue(Double.parseDouble(nv));
            } catch (NumberFormatException e) {
            }
        });
    }

    @FXML
    void heightMousePressedAction(MouseEvent event) {
        height.textProperty().addListener((ob, ov, nv) -> {
            if (!height.getText().isEmpty()) {
                try {
                    sliHeight.setValue(Double.parseDouble(nv));
                } catch (NumberFormatException e) {
                }
            }
        });
    }

    public void emptyControllThreads() {
        Thread tMember = new Thread() {
            @Override
            public void run() {
                memberCondition();
            }
        };
        Thread tEmail = new Thread() {
            @Override
            public void run() {
                emailCondition();
            }
        };
        Thread tPass = new Thread() {
            @Override
            public void run() {
                passCondition();
            }
        };
        Thread tPassComfirm = new Thread() {
            @Override
            public void run() {
                passConfirmCondition();
            }
        };
        Thread tNrc = new Thread() {
            @Override
            public void run() {
                nrcCondition();
            }
        };
        Thread tPhone = new Thread() {
            @Override
            public void run() {
                phoneCondition();
            }
        };
        Thread tAddress = new Thread() {
            @Override
            public void run() {
                addressCondition();
            }
        };
        Thread tDatePicker = new Thread() {
            @Override
            public void run() {
                datePickerCondition();
            }
        };
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
        tMember.start();
        try {
            tMember.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tEmail.start();
        try {
            tEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPass.start();
        try {
            tPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPassComfirm.start();
        try {
            tPassComfirm.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tNrc.start();
        try {
            tNrc.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPhone.start();
        try {
            tPhone.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAddress.start();
        try {
            tAddress.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDatePicker.start();
        try {
            tDatePicker.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tHeight.start();
        try {
            tHeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWeight.start();
    }

    public void invalidControllThreads() {
        Thread tInvalidName = new Thread() {
            @Override
            public void run() {
                invalidNameCondition();
            }
        };
        Thread tInvalidEmail = new Thread() {
            @Override
            public void run() {
                invalidEmailCondition();
            }
        };
        Thread tInvalidNRC = new Thread() {
            @Override
            public void run() {
                invalidNRCCondition();
            }
        };
        Thread tInvalidPhone = new Thread() {
            @Override
            public void run() {
                invalidPhoneCondition();
            }
        };
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
        Thread tDifferentPass = new Thread() {
            @Override
            public void run() {
                differentPassCondition();
            }
        };
        Thread tWeakPass = new Thread() {
            @Override
            public void run() {
                weakPassCondition();
            }
        };
        Thread tInvalidPass = new Thread() {
            @Override
            public void run() {
                invalidPassCondition();
            }
        };
        Thread tInvalidDOB = new Thread() {
            @Override
            public void run() {
                invalidDOBCondition();
            }
        };
        tInvalidName.start();
        try {
            tInvalidName.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidEmail.start();
        try {
            tInvalidEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidNRC.start();
        try {
            tInvalidNRC.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidPhone.start();
        try {
            tInvalidPhone.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDifferentPass.start();
        try {
            tDifferentPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidHeight.start();
        try {
            tInvalidHeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidWeight.start();
        try {
            tInvalidWeight.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWeakPass.start();
        try {
            tWeakPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidPass.start();
        try {
            tInvalidPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidDOB.start();
        try {
            tInvalidDOB.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dob.setOpacity(1);
        dob.getEditor().setOpacity(1);
        
        male.setOpacity(1);
        female.setOpacity(1);
        
        sliHeight.setOpacity(1);
        sliWeight.setOpacity(1);

        if (!editableMode.isSelected()) {
            memberName.setEditable(false);
            email.setEditable(false);
            pass.setEditable(false);
            confirmPass.setEditable(false);
            nrc.setEditable(false);
            phone.setEditable(false);
            dob.setDisable(true);
            male.setDisable(true);
            female.setDisable(true);
            address.setEditable(false);
            height.setEditable(false);
            weight.setEditable(false);
            sliHeight.setDisable(true);
            sliWeight.setDisable(true);
            medicalData.setEditable(false);
//            cboPackage.setEditable(false);
        }

        editableMode.selectedProperty().addListener((ob, ov, nv) -> {
            if (nv) {
                memberName.setEditable(true);
                email.setEditable(true);
                pass.setEditable(true);
                confirmPass.setEditable(true);
                nrc.setEditable(true);
                phone.setEditable(true);
                dob.setDisable(false);
                male.setDisable(false);
                female.setDisable(false);
                address.setEditable(true);
                height.setEditable(true);
                weight.setEditable(true);
                sliHeight.setDisable(false);
                sliWeight.setDisable(false);
                medicalData.setEditable(true);
//                cboPackage.setEditable(true);
            } else {
                memberName.setEditable(false);
                email.setEditable(false);
                pass.setEditable(false);
                confirmPass.setEditable(false);
                nrc.setEditable(false);
                phone.setEditable(false);
                dob.setDisable(true);
                male.setDisable(true);
                female.setDisable(true);
                address.setEditable(false);
                height.setEditable(false);
                weight.setEditable(false);
                sliHeight.setDisable(true);
                sliWeight.setDisable(true);
                medicalData.setEditable(false);
//                cboPackage.setEditable(false);
            }
        });
//        System.out.println(m);
//        m.forEach((t) -> {
//            System.out.println(t.getName());
//            cboPackage.getItems().add(t.getName());
//        });
//        cboPackage.getSelectionModel().selectFirst();

        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(adminMenuScene(titleMemberSignUp.getText()));
            } catch (IOException ex) {
                Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
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

    public AnchorPane adminMenuScene(String titleText) throws IOException {
        JFXButton[] buttons = {myAccButton, viewWorkoutButton, viewScheduleButton, calculatorsButton, healthyTipsButton, logOutButton};
        for (JFXButton button : buttons) {
            button.setPrefWidth(180);
            button.setPrefHeight(44);

            button.setRipplerFill(Color.web("#35ae05"));
            button.setTextFill(Color.web("#000"));
            button.setStyle("-fx-background-color : white");
            button.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
            button.setTextAlignment(TextAlignment.LEFT);
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnMouseEntered((event) -> {
                button.setStyle("-fx-background-color : #c4ffc4");
            });
            button.setOnMouseExited((event) -> {
                button.setStyle("-fx-background-color : white");
            });
        }
        
        switch (titleText) {
            case "My Account":
                myAccButton.setStyle("-fx-background-color : #ffbb00");
                myAccButton.setTextFill(Color.WHITE);
                myAccButton.setOnMouseExited((event) -> {
                    myAccButton.setStyle("-fx-background-color : #ffbb00");
                    myAccButton.setTextFill(Color.WHITE);
                }); break;
            case "View Workout":
                viewWorkoutButton.setStyle("-fx-background-color : #ffbb00");
                viewWorkoutButton.setTextFill(Color.WHITE);
                viewWorkoutButton.setOnMouseExited((event) -> {
                    viewWorkoutButton.setStyle("-fx-background-color : #ffbb00");
                    viewWorkoutButton.setTextFill(Color.WHITE);
                }); break;
            case "View Schedule":
                viewScheduleButton.setStyle("-fx-background-color : #ffbb00");
                viewScheduleButton.setTextFill(Color.WHITE);
                viewScheduleButton.setOnMouseExited((event) -> {
                    viewScheduleButton.setStyle("-fx-background-color : #ffbb00");
                    viewScheduleButton.setTextFill(Color.WHITE);
                });
                break;
            case "Calculators":
                calculatorsButton.setStyle("-fx-background-color : #ffbb00");
                calculatorsButton.setTextFill(Color.WHITE);
                calculatorsButton.setOnMouseExited((event) -> {
                    calculatorsButton.setStyle("-fx-background-color : #ffbb00");
                    calculatorsButton.setTextFill(Color.WHITE);
                });
                break;
            case "Healthy Tips":
                healthyTipsButton.setStyle("-fx-background-color : #ffbb00");
                healthyTipsButton.setTextFill(Color.WHITE);
                healthyTipsButton.setOnMouseExited((event) -> {
                    healthyTipsButton.setStyle("-fx-background-color : #ffbb00");
                    healthyTipsButton.setTextFill(Color.WHITE);
                });
                break;
            default:
                break;
        }
        
        myAccButton.setLayoutX(0);
        myAccButton.setLayoutY(0);

        viewWorkoutButton.setLayoutX(0);
        viewWorkoutButton.setLayoutY(52);

        viewScheduleButton.setLayoutX(0);
        viewScheduleButton.setLayoutY(104);

        calculatorsButton.setLayoutX(0);
        calculatorsButton.setLayoutY(156);

        healthyTipsButton.setLayoutX(0);
        healthyTipsButton.setLayoutY(208);

        logOutButton.setLayoutX(0);
        logOutButton.setLayoutY(260);

        AnchorPane adminMenuScene = new AnchorPane();
        adminMenuScene.setPrefWidth(180);
        adminMenuScene.setPrefHeight(425);
        adminMenuScene.setStyle("-fx-background-color : white");
        adminMenuScene.getChildren().addAll(myAccButton, viewWorkoutButton, viewScheduleButton, calculatorsButton, healthyTipsButton, logOutButton);
        
        myAccButton.setOnAction((event) -> {
            if (ConnectDB.CreateConnection() != null) {
                String sql = "SELECT * FROM member";

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("MemberMyAccountScene.fxml"));
                        Parent trainerMyAccountScene = loader.load();
                        MemberMyAccountController controller = loader.getController();
                        Scene scene = new Scene(trainerMyAccountScene, 1360, 700);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        if (rs.getString("email").equals(window.getTitle())) {
                            controller.addingData(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("nrc"), rs.getString("phone_no"),
                                    rs.getDate("dob").toLocalDate(), rs.getString("gender"), rs.getString("address"), rs.getInt("height"), rs.getInt("weight"), rs.getString("medical_data"));
                            window.setResizable(false);
                            window.setScene(scene);
                            window.show();
                        }

                    }

                } catch (Exception e) {

                }
            }

        });

        calculatorsButton.setOnAction((event) -> {
            
            if (ConnectDB.CreateConnection() != null) {
                Stage windowWithCondi = (Stage) ((Node) event.getSource()).getScene().getWindow();
                String sql = String.format("SELECT * FROM invoice LEFT JOIN member ON member.id = invoice.member_id WHERE member.email = '%s' ORDER BY invoice.id DESC LIMIT 1", windowWithCondi.getTitle());

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("FXMLAllCalculators.fxml"));
                        Parent trainerMyAccountScene = loader.load();
                        FXMLAllCalculatorsController controller = loader.getController();
                        Scene scene = new Scene(trainerMyAccountScene, 1360, 700);
                        if (!rs.getString("membership_id").equals("MS-001")) {
                            windowWithCondi.setResizable(false);
                            windowWithCondi.setScene(scene);
                            windowWithCondi.show();
                            break;
                        } else {
                            new Alert(Alert.AlertType.ERROR, "You need to buy 2 month package or above for calculators.", ButtonType.OK).showAndWait();
                            break;
                        }

                    }

                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }

        });

        viewWorkoutButton.setOnAction((event) -> {
            if (ConnectDB.CreateConnection() != null) {
                Stage windowWithCondi = (Stage) ((Node) event.getSource()).getScene().getWindow();
                String sql = String.format("SELECT * FROM invoice LEFT JOIN member ON member.id = invoice.member_id WHERE member.email = '%s' ORDER BY invoice.id DESC LIMIT 1", windowWithCondi.getTitle());

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("MainWorkoutScene.fxml"));
                        Parent trainerMyAccountScene = loader.load();
                        MainWorkoutController controller = loader.getController();
                        Scene scene = new Scene(trainerMyAccountScene, 1360, 700);
                        if (!rs.getString("membership_id").equals("MS-001") && !rs.getString("membership_id").equals("MS-002") && 
                                !rs.getString("membership_id").equals("MS-003")) {
                            windowWithCondi.setResizable(false);
                            windowWithCondi.setScene(scene);
                            windowWithCondi.show();
                            break;
                        } else {
                            new Alert(Alert.AlertType.ERROR, "You need to buy 4 month package or above for videos.", ButtonType.OK).showAndWait();
                            break;
                        }

                    }

                } catch (Exception e) {
//                    new Alert(AlertType.ERROR, "Admin or trainer system has not launched yet. Please launch admin or trainer system.", ButtonType.OK).showAndWait();
                    e.printStackTrace();
                }
            }

        });
        
        viewScheduleButton.setOnAction((event) -> {
            try {
                Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("ViewScheduleScene.fxml"));
                Scene scene = new Scene(memberSignUpScene, 1360, 700);
                scene.getStylesheets().add("CSS/TableDesign.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        healthyTipsButton.setOnAction((event) -> {
            try {
                Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("HealthyTipsScene.fxml"));
                Scene scene = new Scene(memberSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        logOutButton.setOnAction((event) -> {
            try {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to log out?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("MemberSignInScene.fxml"));
                    Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("NutriFit");
                    window.setResizable(false);
                    window.setScene(scene);
                    window.show();
                } else {
                    event.consume();
                }

            } catch (IOException ex) {
                Logger.getLogger(MemberMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        return adminMenuScene;
    }

}
