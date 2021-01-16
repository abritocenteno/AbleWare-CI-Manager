/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voorraadsysteem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author abel
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private JFXTextField usertxt;

    @FXML
    private JFXPasswordField passtxt;

    @FXML
    private JFXButton loginbtn;

    @FXML
    private JFXButton afsluitbtn;

    @FXML
    private Label errorlabel;

    @FXML
    private JFXComboBox<String> profielcombo;
    ObservableList<String> listProfiles = FXCollections.observableArrayList("Admin");

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Login(ActionEvent event) {
        if (Login().equals("Success")) {
            try {

                if  (profielcombo.getValue() == "Admin") {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setTitle("Welcome, Admin | AbleWare");
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainWindowFXML.fxml")));
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                }   
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profielcombo.setItems(listProfiles);
    }

    public LoginFXMLController() {
        con = ConnectDatabase.conDB();
    }

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String Login() {
        String username = usertxt.getText();
        String password = passtxt.getText();
        String profile = profielcombo.getValue();
        String sql = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=? AND PROFIEL=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, profile);
            rs = pst.executeQuery();
            if (!rs.next()) {
                errorlabel.setTextFill(Color.RED);
                errorlabel.setText("Onjuiste combinatie gebruiker/wachtwoord/profiel");
                usertxt.setText("");
                passtxt.setText("");
                profielcombo.setValue("");
                return "Error";
            } else {
                errorlabel.setTextFill(Color.web("#00A49B"));
                errorlabel.setText("Ingelogd. Momentje...");
                return "Success";
            } 
           
           

        } catch (SQLException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            return "Exceptions";
        } 
    }

    private void ShowDialog(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

}
