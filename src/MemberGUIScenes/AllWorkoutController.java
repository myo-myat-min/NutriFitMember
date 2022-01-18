/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemberGUIScenes;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import db.workout;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.nio.file.WatchEvent;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import static newpackage.b.FILE_SIZE;
//import static newpackage.b.FILE_TO_RECEIVED;

/**
 *
 * @author ACER
 */
public class AllWorkoutController implements Initializable {

//    @FXML
//    private JFXDrawer adminDrawer;
//
//    @FXML
//    private JFXHamburger adminMenu;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private SVGPath backArrowShape;
    
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
    void backArrowMouseReleasedAction(MouseEvent event) {
        try {
            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("MainWorkoutScene.fxml"));
            Scene scene = new Scene(trainerSignUpScene);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AllWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FlowPane flowPane = new FlowPane();
        flowPane.setStyle("-fx-background-color:white");
        int countRow = 0;
        int countPane = 0;

        ArrayList<workout> w = db.workout_table.showall();

        w.forEach((t) -> {

            BorderPane borderPane = new BorderPane();
            borderPane.setPrefSize(350, 0);
            borderPane.setStyle("-fx-background-color : #8a8a8a; -fx-background-radius : 20px;");

//                                gridPane.setHgap(30);
//                                gridPane.setVgap(30);
            String css = "-fx-font-size : 15px; -fx-font-weight: bold";
            Text workoutName = new Text(t.getWorkout_name());
            workoutName.setStyle(css);
            workoutName.setFill(Color.WHITE);

            Text byWho = new Text("By " + t.getTrainerName());
            byWho.setStyle(css);
            byWho.setFill(Color.WHITE);
            System.out.println(t.getWorkout_video());
            Media media = new Media(new File(t.getWorkout_video()).toURI().toString());

            Image iv = new Image(new ByteArrayInputStream(t.getImage()));
            ImageView i = new ImageView(iv);

            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setOnMouseEntered((event) -> {
                mediaPlayer.play();
            });
            mediaView.setOnMouseExited((event) -> {
                mediaPlayer.stop();
            });
            mediaPlayer.setMute(true);
            mediaPlayer.setCycleCount(Timeline.INDEFINITE);

            i.setOnMouseClicked(e -> {
                try {
                    e(e, t);
                } catch (IOException ex) {
                    Logger.getLogger(AllWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            flowPane.setMargin(i, new Insets(10));
            i.setFitHeight(150);
            i.setFitWidth(370);

            HBox hBoxWho = new HBox();
            hBoxWho.setAlignment(Pos.BOTTOM_CENTER);
            hBoxWho.getChildren().add(byWho);

            HBox hBoxWorkout = new HBox();
            hBoxWorkout.setAlignment(Pos.TOP_CENTER);
            hBoxWorkout.getChildren().add(workoutName);

            borderPane.setTop(hBoxWorkout);
            borderPane.setCenter(i);
            borderPane.setBottom(hBoxWho);
            i.setPreserveRatio(true);
            Reflection ref = new Reflection();
            ref.setBottomOpacity(0.2);
            ref.setFraction(0.2);
            ref.setTopOffset(9);
            ref.setTopOpacity(0.4);
            borderPane.setEffect(ref);
//            BorderPane.setAlignment(hBoxWho, Pos.BOTTOM_CENTER);
//            BorderPane.setAlignment(hBoxWorkout, Pos.TOP_CENTER);

            flowPane.getChildren().addAll(borderPane);

        });

        flowPane.setPrefSize(1100, 482);
        scrollPane.setContent(flowPane);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        flowPane.setHgap(20);
        flowPane.setVgap(10);

//        MemberMyAccountController memberMyAccControl = new MemberMyAccountController();
//        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
//        burgerTask.setRate(-1);
//        adminMenu.setOnMousePressed((event) -> {
//            try {
//                adminDrawer.setSidePane(memberMyAccControl.adminMenuScene());
//            } catch (IOException ex) {
//                Logger.getLogger(AllWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//        adminMenu.setOnMouseReleased((event) -> {
//            burgerTask.setRate(burgerTask.getRate() * -1);
//            burgerTask.play();
//
//            if (adminDrawer.isShown()) {
//                adminDrawer.close();
//            } else {
//                adminDrawer.open();
//            }
//        });

    }

    public void e(MouseEvent a, workout t) throws IOException {

        try {
            Socket sc = new Socket("localhost", 9090);

            sc.setSoTimeout(5000);
            OutputStream os = sc.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.println(t.getWorkout_video());
            pw.flush();
            int bytesRead;
            int current = 0;
            InputStream is = sc.getInputStream();
            byte[] mybytearray = new byte[27262976];
            do {
                bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
            } while (bytesRead > -1);
            System.out.println("Downloaded");

            File tempFile = File.createTempFile("my-file", ".avi");
            FileOutputStream fos = new FileOutputStream(tempFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bos.write(mybytearray, 0, current);
            bos.flush();
            System.out.println("File " + tempFile + " downloaded (" + current + " bytes read)");
            System.out.println(tempFile.getAbsoluteFile());

            Path p = Paths.get("aa");
            Files.createTempFile("A", ".java");
            os.close();
            pw.close();
            is.close();
            fos.close();
            bos.close();
            sc.close();
            tempFile.deleteOnExit();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ViewSingleWorkoutScene.fxml"));
            Parent memberSignUpScene = loader.load();
            ViewSingleWorkoutController vswc = loader.getController();
            vswc.filterForAll("all");
            Scene scene = new Scene(memberSignUpScene);

            Stage window = (Stage) ((Node) a.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            vswc.addingdata(t, tempFile);
            System.out.println(Runtime.getRuntime().freeMemory());
            System.out.println(Runtime.getRuntime().maxMemory());
            window.show();
        } catch (IOException ex) {
            new Alert(AlertType.ERROR, "Admin or trainer system has not launched yet. Please launch admin or trainer system.", ButtonType.OK).showAndWait();
        }

    }

}
