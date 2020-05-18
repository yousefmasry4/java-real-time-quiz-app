/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.javafxsocketprogramming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ClientJavaFX extends Application {

    //controls
    final String my_id;//TODO get from db

    TextField txtName;
    TextField txtInput;
    ScrollPane scrollPane;

    // IO streams
    DataOutputStream output = null;
    public ListView<VBox> txtAreaDisplay = new ListView<>();

      public Map<String, String> fs = new HashMap<String, String>();
    List<String> questions;
    public ClientJavaFX(String my_id, List questions) {
        this.my_id = my_id;
        this.questions=questions;
    }

    class question{
        final String question;

        question(String question) {
            this.question = question;
                 fs.put(question,"d");

        }
        VBox me(){
            VBox v=new VBox();
            Label q = new Label(question);
            v.setId(question);


            TextField textField = new TextField ();

            HBox hh= new HBox();

            Button f=new Button("send");

            f.setOnAction((e)->{
                DataOutputStream output= null;
                try {
                    Socket socket = new Socket(ConnectionUtil.host, ConnectionUtil.port);
                    output = new DataOutputStream(socket.getOutputStream());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    output.writeUTF(my_id+"~"+question+"~"+textField.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            v.getChildren().addAll(q,  textField,f );
            return v;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        //pane to hold scroll pane and HBox
        VBox vBox = new VBox();

        scrollPane = new ScrollPane();   //pane to display text messages
        HBox hBox = new HBox(); //pane to hold input textfield and send button

  //      txtAreaDisplay = new TextArea();
        txtAreaDisplay.setEditable(false);
        scrollPane.setContent(txtAreaDisplay);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);



        //set center and bottom of the borderPane with scrollPane and hBox
        vBox.getChildren().addAll(scrollPane);
        vBox.setVgrow(scrollPane, Priority.ALWAYS);

        //create a scene and display
        Scene scene = new Scene(vBox, 450, 500);
        primaryStage.setTitle("Client: JavaFx Text Chat App");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(ConnectionUtil.host, ConnectionUtil.port);
                //TODO GET DATA FROM DB
                for(String s:questions){
                    txtAreaDisplay.getItems().add( new question(s).me() );
                }

            //Connection successful
        //    txtAreaDisplay.appendText("Connected. \n");

            // Create an output stream to send data to the server
            output = new DataOutputStream(socket.getOutputStream());

            //create a thread in order to read message from server continuously
            TaskReadThread task = new TaskReadThread(socket, this,this.my_id );
            Thread thread = new Thread(task);
            thread.start();
        } catch (IOException ex) {

        //    txtAreaDisplay.appendText(ex.toString() + '\n');
        }
    }

    /**
     * @param args the command line arguments
     */


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
                output.writeUTF("[" + username + "]: " + message + "");
                output.flush();

                //clear the textfield
                txtInput.clear();
            } catch (IOException ex) {
                System.err.println(ex);
            }

        }
    }

}
