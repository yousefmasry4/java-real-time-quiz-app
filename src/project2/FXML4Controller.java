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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project2.doctor.ClientJavaFXdocto;
import project2.javafxsocketprogramming.ClientJavaFX;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXML4Controller implements Initializable {

    public ListView<String> ques;
    public Label cc;
    @FXML
    private TextField question;
    
    @FXML
    private TextField Field;
    
    @FXML
    private ImageView imageview;
    
    @FXML
    private Button show;
    
    @FXML
    private Button add;

    
    @FXML
    private void handleButtonAction() throws IOException {


        Questions q = new Questions();
        String code =dumy_data.id+"~"+q.id;
        System.out.println(code);
        cc.setText(code);
        add.setOnAction((ActionEvent e)->{
            q.insert(question.getText());
            ques.getItems().add(question.getText());
            question.clear();
        });
        show.setOnAction((ActionEvent e)->{

            q.stream();
            try {

                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                ClientJavaFXdocto n=new ClientJavaFXdocto();
                n.start(stage);
                // stage.hide();
            } catch (Exception ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
