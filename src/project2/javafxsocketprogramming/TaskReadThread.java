/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2.javafxsocketprogramming;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import project2.javafxsocketprogramming.ClientJavaFX;

/**
 *
 * @author topman garbuja,
 * 
 * It is used to get input from server simultaneously
 */
public class TaskReadThread implements Runnable {
    //private variables
    Socket socket;
    ClientJavaFX client;
    DataInputStream input;
    String myid;
    //constructor
    public TaskReadThread(Socket socket, ClientJavaFX client,String myid) {
        this.socket = socket;
        this.client = client;
        this.myid=myid;
    }

    @Override 
    public void run() {
        //continuously loop it
        while (true) {
            try {
                //Create data input stream
                input = new DataInputStream(socket.getInputStream());

                //get input from the client
                String message = input.readUTF();

                //append message of the Text Area of UI (GUI Thread)
                Platform.runLater(() -> {
                    //display the message in the textarea
                   // client.txtAreaDisplay.getItems().add(me(message));
                    var nm=message.split("~");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    if(String.valueOf(nm[0]).equals(myid) && nm.length==4){
                        //TODO add my id from db
                        alert.setContentText("qusetion\n"
                        +nm[1]+
                                "\n is "+nm[2]);
                        alert.show();

                    }

                });


            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
