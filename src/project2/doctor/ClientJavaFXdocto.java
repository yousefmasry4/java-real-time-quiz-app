/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.doctor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author topman garbuja, 
 *
 * This is the client which passes and get message to and from server and
 * further to multiple clients
 *
 * It also uses TaskReadThread.java file to be used in a new thread in order to
 * get simultaneous input from server
 */
public class ClientJavaFXdocto extends Application {

    //controls

    TextField txtName;
    TextField txtInput;
    ScrollPane scrollPane;
    public ListView<VBox> txtAreaDisplay = new ListView<>();

    // IO streams
    DataOutputStream output = null;

    @Override
    public void start(Stage primaryStage) {
        //pane to hold scroll pane and HBox
        VBox vBox = new VBox();

        scrollPane = new ScrollPane();   //pane to display text messages
        HBox hBox = new HBox(); //pane to hold input textfield and send button

     //   txtAreaDisplay = new TextArea();
      //  txtAreaDisplay.setEditable(false);
        scrollPane.setContent(txtAreaDisplay);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);



        //set center and bottom of the borderPane with scrollPane and hBox
        vBox.getChildren().addAll(txtAreaDisplay);
        vBox.setVgrow(txtAreaDisplay, Priority.ALWAYS);

        //create a scene and display
        Scene scene = new Scene(vBox, 450, 500);
        primaryStage.setTitle("Client: JavaFx Text Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(ConnectionUtil.host, ConnectionUtil.port);

            //Connection successful
      //      txtAreaDisplay.appendText("Connected. \n");
          
            // Create an output stream to send data to the server
            output = new DataOutputStream(socket.getOutputStream());

            //create a thread in order to read message from server continuously
            TaskReadThread2 task = new TaskReadThread2(socket, this);
            Thread thread = new Thread(task);
            thread.start();
        } catch (IOException ex) {
            
      //      txtAreaDisplay.appendText(ex.toString() + '\n');
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Handle button action
     */
    private class ButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            try {
                //get username and message
                String username = txtName.getText().trim();
                String message = txtInput.getText().trim();

                //if username is empty set it to 'Unknown' 
                if (username.length() == 0) {
                    username = "Unknown";
                }
                //if message is empty, just return : don't send the message
                if (message.length() == 0) {
                    return;
                }

                //send message to server
                output.writeUTF(username+"~"+message+"~"+message);
                output.flush();

                //clear the textfield
                txtInput.clear();
            } catch (IOException ex) {
                System.err.println(ex);
            }

        }
    }

}
