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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import project2.javafxsocketprogramming.ClientJavaFX;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXML2Controller implements Initializable {
    @FXML
    public TextField id;
    @FXML
    private Label RegNo;

    @FXML
    private Label PinCode;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label forget;

    @FXML
    private Label student;

    @FXML
    private Label password;

    @FXML
    private TextField text;

    @FXML
    private PasswordField pass;

    @FXML
    private ImageView view;

    @FXML
    private ImageView imageview;

    @FXML
    private Rectangle r;

    @FXML
    private Button login;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        int text1 = Integer.parseInt(text.getText());
        int Password = Integer.parseInt(pass.getText());
        Image image = new Image("/image/exam.jpg");
        Image image1 = new Image("/image/quizicon.png");
        imageview.setImage(image);
        imageview.getImage();
        view.setImage(image1);
        login.setOnAction((ActionEvent e) -> {
            StudentLogin log = new StudentLogin(text1, Password,id.getText());

            try {
                boolean acceptlogin = log.Acceptlogin();
                System.out.println(acceptlogin);
                if (!acceptlogin) {
                    forget.setText("Registration Number and Pin Code dose not matched. Please type them correctly.");
                } else{

                    try {

                        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                        ClientJavaFX n=new ClientJavaFX(log.id,log.data);
                        n.start(stage);
                       // stage.hide();
                    } catch (Exception ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FXML2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Students s = new Students("ahmed","ahyutytuy","M");
            //s.mysql();

        });

    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
