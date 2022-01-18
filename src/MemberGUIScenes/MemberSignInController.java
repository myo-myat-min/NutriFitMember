package MemberGUIScenes;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.ConnectDB;
import db.invoice_table;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberSignInController implements Initializable {
    
    private FadeTransition fadeAnimationName = new FadeTransition();
    private FadeTransition fadeAnimationPass = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationWrongName = new FadeTransition();
    private FadeTransition fadeAnimationWrongPass = new FadeTransition();

    @FXML
    private Rectangle welcomeTemplate;

    @FXML
    private Text txtWelcome;

    @FXML
    private Text txtNutriFit;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleTrainerSignUp;

    @FXML
    private JFXTextField txtFieldEmail;

    @FXML
    public JFXButton signInButton;

    @FXML
    protected Text txtForgetPass;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private Circle nameCircle;

    @FXML
    private Circle passCircle;

    @FXML
    private Circle completeCircPass;

    @FXML
    private Circle completeCircName;

    @FXML
    private Text emptyNoti;
    
    @FXML
    private Text wrongName;

    @FXML
    private Text wrongPass;
    
    @FXML
    void forgetPassMouseEnteredAction(MouseEvent event) {

    }

    @FXML
    void nameAction(ActionEvent event) {
        pass.requestFocus();
        pass.selectAll();
    }

    @FXML
    void signInPressedAction(MouseEvent event) {
        if (!txtFieldEmail.getText().isEmpty() && !pass.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN) {
                completeCircName.setFill(Color.RED);
                completeCircPass.setFill(Color.RED);
            } else {
                wrongAnimation();
                wrongControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }
    
    public String emailData(String email){
        return email;
    }

    @FXML
    public void signInReleasedAction(MouseEvent event) {
        if (!txtFieldEmail.getText().isEmpty() && !pass.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN) {
                if (ConnectDB.CreateConnection() != null) {
                    String sql = "SELECT * FROM member";
                    int countForEmailPassAlert = 0;

                    try (Connection con = ConnectDB.CreateConnection();
                            PreparedStatement ps = con.prepareStatement(sql);) {

                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            if (rs.getString("email").equals(txtFieldEmail.getText()) && rs.getString("password").equals(pass.getText())) {
                                if (invoice_table.lastEndDate(txtFieldEmail.getText()).isAfter(LocalDate.now()) || invoice_table.lastEndDate(txtFieldEmail.getText()).equals(LocalDate.now())) {
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("MemberMyAccountScene.fxml"));
                                    Parent trainerMyAccountScene = loader.load();
                                    MemberMyAccountController controller = loader.getController();
                                    Scene scene = new Scene(trainerMyAccountScene);
                                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    window.setResizable(false);
                                    window.setScene(scene);
                                    controller.addingData(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("nrc"), rs.getString("phone_no"),
                                            rs.getDate("dob").toLocalDate(), rs.getString("gender"), rs.getString("address"), rs.getInt("height"), rs.getInt("weight"), rs.getString("medical_data"));
                                    window.setTitle(rs.getString("email"));
                                    window.show();
                                    countForEmailPassAlert = 0;
                                    break;
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Your membership plan has expired.", ButtonType.OK).showAndWait();
                                    countForEmailPassAlert = 0;
                                    break;
                                }

                            } else {
                                countForEmailPassAlert++;
                            }
                        }
                        if (countForEmailPassAlert > 0) {
                            new Alert(Alert.AlertType.ERROR, "Please check your email or password again. Forgot password?", ButtonType.OK).showAndWait();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                completeCircName.setFill(Color.RED);
                completeCircPass.setFill(Color.RED);
            } else {
                wrongAnimation();
                wrongControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forgetPassHover();
        txtForgetPass.setOnMouseClicked((event) -> {
            if (ConnectDB.CreateConnection() != null) {
                String sql = "SELECT email, password FROM member";
                int countBothWrong = 0;
                int countBothRight = 0;

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        if (rs.getString("email").equals(txtFieldEmail.getText()) && !rs.getString("password").equals(pass.getText())) {
                            //                            Decrypt password
                            send("mstzsn01@gmail.com", "qazmlp123!", txtFieldEmail.getText(), "NutriFit - Password", rs.getString("password"));
                            new Alert(AlertType.INFORMATION, "Password is sent to your email").showAndWait();
                            countBothWrong = 0;
                            countBothRight = 0;
                            break;
                        } else if (rs.getString("email").equals(txtFieldEmail.getText()) && rs.getString("password").equals(pass.getText())) {
                            countBothRight++;
                            countBothWrong = 0;
                            break;
                        } else {
                            countBothWrong++;
                        }
                    }
                    if (countBothWrong > 0) {
                        new Alert(AlertType.ERROR, "Your email has not registered yet. Please check your email again.").showAndWait();
                    }
                    if (countBothRight > 0) {
                        new Alert(AlertType.INFORMATION, "Please click SignIn button").showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
    
    public void send(String from, String password, String to, String sub, String msg) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(sub);
            message.setText(msg);
            System.out.println("Before");
            Transport.send(message);
            System.out.println("message sent successfully");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }

    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }
    
    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationName);
        fadeAnimationItems(fadeAnimationPass);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }
    
    public void wrongAnimation() {
        fadeAnimationItems(fadeAnimationWrongName);
        fadeAnimationItems(fadeAnimationWrongPass);
    }
    
    public void nameCondition() {
        if (txtFieldEmail.getText().isEmpty()) {
            fadeAnimationName.setNode(nameCircle);
            fadeAnimationName.play();
        }
    }
    
    public void passCondition() {
        if (pass.getText().isEmpty()) {
            fadeAnimationPass.setNode(passCircle);
            fadeAnimationPass.play();
        }
    }
    
    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }
    
    public void wrongEmailCondition() {
//        Plz insert wrong name SQL code in if condition
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", txtFieldEmail.getText())) {
            completeCircName.setFill(Color.RED);
            fadeAnimationWrongName.setNode(wrongName);
            fadeAnimationWrongName.play();
        } else {
            completeCircName.setFill(Color.GREEN);
        }
    }
    
    public void wrongPassCondition() {
//        Plz insert wrong password SQL code in if condition
        if (!Pattern.matches("[\\w\\S]{8,20}", pass.getText())) {
            completeCircPass.setFill(Color.RED);
            fadeAnimationWrongPass.setNode(wrongPass);
            fadeAnimationWrongPass.play();
        } else {
            completeCircPass.setFill(Color.GREEN);
        }
    }

    public void emptyControllThreads() {
        Thread tEmail = new Thread() {
            @Override
            public void run() {
                nameCondition();
            }
        };
        Thread tPass = new Thread() {
            @Override
            public void run() {
                passCondition();
            }
        };
        tEmail.start();
        try {
            tEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPass.start();
    }
    
    public void wrongControllThreads() {
        Thread tWrongEmail = new Thread() {
            @Override
            public void run() {
                wrongEmailCondition();
            }
        };
        Thread tWrongPass = new Thread() {
            @Override
            public void run() {
                wrongPassCondition();
            }
        };
        tWrongEmail.start();
        try {
            tWrongEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWrongPass.start();
    }
    
    public void forgetPassHover(){
        Stop[] stop = {new Stop(0, Color.BLACK),  
                       new Stop(1, Color.web("#fb0"))}; 
  
        LinearGradient linear_gradient = new LinearGradient(0, 0, 
                          1, 0, true, CycleMethod.NO_CYCLE, stop);
        txtForgetPass.setOnMouseEntered((event) -> {
            txtForgetPass.setFill(Color.web("#000"));
        });
        txtForgetPass.setOnMouseExited((event) -> {
            txtForgetPass.setFill(linear_gradient);
        });
    }
}
