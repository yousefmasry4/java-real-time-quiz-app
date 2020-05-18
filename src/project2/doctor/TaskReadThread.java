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
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project2.javafxsocketprogramming.ClientJavaFX;

/**
 *
 * @author topman garbuja,
 * 
 * It is used to get input from server simultaneously
 */
class TaskReadThread2 implements Runnable {
    //private variables
    Socket socket;
    ClientJavaFXdocto client;
    DataInputStream input;

    //constructor
    public TaskReadThread2(Socket socket, ClientJavaFXdocto client) {
        this.socket = socket;
        this.client = client;
    }
    VBox me(String m){
        System.out.println(m);
        var mlist=m.split("~");
        String student_id=mlist[0];String question=mlist[1];String answer=mlist[2];
        VBox v=new VBox();
        Label sid = new Label(student_id);
        Label q = new Label(question);
        Label ans = new Label(answer);
        HBox hh= new HBox();
        Button t=new Button("true");
        Button f=new Button("false");
        t.setOnAction((e)->{
            DataOutputStream output= null;
            try {
                output = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                output.writeUTF(student_id+"~"+question+"~"+"true"+"~"+0);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        f.setOnAction((e)->{
            DataOutputStream output= null;
            try {
                output = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                output.writeUTF(student_id+"~"+question+"~"+"false"+"~"+0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                output.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        hh.getChildren().addAll(t,f);
        v.getChildren().addAll(sid,q,  ans,hh );
        return v;
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
                    if( message.split("~").length==3)
                    client.txtAreaDisplay.getItems().add(me(message));

                });
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
