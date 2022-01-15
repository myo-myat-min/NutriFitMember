/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemberGUIScenes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.workout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ViewSingleWorkoutController implements Initializable {

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Circle completeCircWorkoutName;

    @FXML
    private Circle completeCircEffectedMuscle;

    @FXML
    private Circle completeCircBurntCalorie;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private AnchorPane mediaAnchorPane;

    @FXML
    private JFXTextField txtFieldWorkoutName;

    @FXML
    private AnchorPane imageAnchorPane;

    @FXML
    private JFXTextField txtFieldEffectedMuscle;
    @FXML
    private JFXTextField txtFieldBurntCalorie;

    @FXML
    private Circle workoutNameCircle;

    @FXML
    private Circle effectedMuscleCircle;

    @FXML
    private Circle burntCalorieCircle;

    @FXML
    private Text emptyNoti;

    @FXML
    private Text invalidName;

    @FXML
    private Text invalidMuscle;

    @FXML
    private Text invalidCalorie;

    @FXML
    private SVGPath backArrowShape;

    @FXML
    private JFXComboBox<String> exerciseChoice = new JFXComboBox<>(FXCollections.observableArrayList("Loose Weight", "Gain Weight", "Get Fitter"));

    private static workout w;

    private File f;
    private MediaPlayer mediaPlayer;
    
    private String dataAll = "Nothing";
    
    public void filterForAll(String all){
        dataAll = all;
    }

    public void addingdata(workout t, File f) {

        this.w = t;
        this.f = f;
        this.exerciseChoice.getSelectionModel().select(t.getWorkout_type());
        this.txtFieldWorkoutName.setText(t.getWorkout_name());
        this.txtFieldEffectedMuscle.setText(t.getEffectedMuscle());
        this.txtFieldBurntCalorie.setText(String.valueOf(t.getBurnt_calorie()));
//w.getWorkout_video()).toURI().toString()
        Media media = new Media(f.toURI().toString());
        media.setOnError(() -> {
            new Alert(Alert.AlertType.ERROR).show();
        });
        mediaPlayer = new MediaPlayer(media);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setDisable(true);
        mediaPlayer.setMute(true);
        mediaPlayer.setAutoPlay(true);
        mediaView.setFitHeight(mediaAnchorPane.getHeight());
        mediaView.setFitWidth(mediaAnchorPane.getWidth());

        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        mediaAnchorPane.getChildren().add(mediaView);
        mediaAnchorPane.setStyle("-fx-background-color : background;");

        Image image = new Image(new ByteArrayInputStream(t.getImage()));
        ImageView imgView = new ImageView(image);
        System.out.println(imageAnchorPane.getHeight() + "asa" + imageAnchorPane.getWidth());
        imgView.setFitHeight(imageAnchorPane.getHeight());
        imgView.setFitWidth(imageAnchorPane.getWidth());

        imageAnchorPane.setStyle("-fx-background-color : transparent;");
        imageAnchorPane.getChildren().add(imgView);
        System.gc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exerciseChoice.getItems().addAll("Loose Weight", "Gain Weight", "Get Fitter");
        exerciseChoice.getSelectionModel().selectFirst();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        
            System.out.println("aaaaaaaaaaaaa");
        }));

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

//    @FXML
//    void backArrowMouseReleasedAction(MouseEvent event) {
//        try {
//            mediaPlayer.dispose();
//
//            System.out.println(Files.deleteIfExists(f.toPath()));
//
//            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("ViewWorkoutScene.fxml"));
//            Scene scene = new Scene(trainerSignUpScene, 1360, 700);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setResizable(false);
//            window.setScene(scene);
//            window.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ViewSingleWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    @FXML
    void backArrowMouseReleasedAction(MouseEvent event) {
        if (exerciseChoice.getValue().equals("Loose Weight") && !dataAll.equals("all")) {
            try {
                mediaPlayer.dispose();
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("LooseWeightWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewSingleWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (exerciseChoice.getValue().equals("Gain Weight") && !dataAll.equals("all")) {
            try {
                mediaPlayer.dispose();
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("GainWeightWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewSingleWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (exerciseChoice.getValue().equals("Get Fitter") && !dataAll.equals("all")) {
            try {
                mediaPlayer.dispose();
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("GetFitterWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewSingleWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                mediaPlayer.dispose();
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("AllWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewSingleWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
