/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Questions {
    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    private String ques;

    private double marks;
    
    private int no;
    String id= String.valueOf(generateRandomIntIntRange(10000, 999999));
    List<String> questions=new ArrayList<String>();
    public void insert(String ques){
        questions.add(ques);
    }
    
    
    public void stream(){

        Connection con ;
        try {
            //TODO :password root instead of admin
                con = DriverManager.getConnection("jdbc:mysql://localhost/exam","root","admin");
                Statement stmt=con.createStatement();
                JOptionPane.showMessageDialog(null, ques);
                //todo : t3del el id
                //todo : stop
            for(String ques:questions) {
                String query = "INSERT INTO questions(ID,ques,bid,goid) VALUES (" + generateRandomIntIntRange(10000, 999999) +
                        ",'" + ques + "' , '" + dumy_data.id + "'," + id + ")";
                stmt.executeUpdate(query);
            }
                con.close();
                JOptionPane.showMessageDialog(null, "the question added successfuly");
            } catch (SQLException ex) {
                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
                
            }
        
    }
}

