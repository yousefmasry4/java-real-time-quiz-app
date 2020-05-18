/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */



public class FXMLController implements Initializable {
    @FXML
    private Label ID;

    @FXML
    private Label pass;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label forget;

    @FXML
    private Label lecturer;

    @FXML
    private Label wrong;

    @FXML
    private TextField text;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView view;

    @FXML
    private ImageView imageview;

    @FXML
    private Rectangle r;

    @FXML
    private Button login;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        int text1 = Integer.parseInt(text.getText());
        //int Password = Integer.parseInt(password.getText());
        Image image = new Image("/image/lecturer.jpg");
        Image image1 = new Image("/image/quiz.png");
        imageview.setImage(image);
        imageview.getImage();
        view.setImage(image1);
        login.setOnAction((ActionEvent e) -> {
            LecturerLogin log = new LecturerLogin(text1, password.getText());

            boolean acceptlogin = log.Acceptlogin();

            if (!acceptlogin) {
                wrong.setText("Registration Number and Pin Code dose not matched. Please type them correctly.");
            } else  {

                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("FXML3.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
