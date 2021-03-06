package db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ConnectDB {

    private static final String url = "jdbc:mysql://localhost/Nutri_Fit";
    private static final String username = "root";
    private static final String password = "";

    public static java.sql.Connection CreateConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, "Please connect MYSQL server", ButtonType.OK).showAndWait();
        }
        return null;
    }

}
