/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXML3Controller implements Initializable {
    private int portNumber = 1500;

    // create a server object and start it


    @FXML
    private Label enter;
    
    @FXML
    private Label hello;
    
    @FXML
    private Button quizzes;
    
    @FXML
    private Button logout;
    
    @FXML
    private Label name;
    
    @FXML
    private Button start;
    
    String n;
    @FXML
    private void handleButtonAction() {
        logout.setOnAction((ActionEvent e)->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        start.setOnAction((ActionEvent e)->{
            
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("FXML4.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXML3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        });
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
