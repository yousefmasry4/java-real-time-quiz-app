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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button student;
    
    @FXML
    private Button lecturer;
    
    @FXML
    private Label l1;
            
    @FXML
    private Label l2;
    
    @FXML
    private Rectangle r;
    
    @FXML
    private ImageView imageview;
    
    @FXML
    private void handleButtonAction(){
        Image image = new Image("/image/quiz.png");
        imageview.setImage(image);
        student.setOnAction((ActionEvent e)->{
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
        lecturer.setOnAction((ActionEvent e)->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
