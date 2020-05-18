/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Students extends person {

    private int semseter;

    private Courses c;
    
    private int RegNo ;
    
    private int pincode; 

    public Students(int semseter, double gpa, Courses c, String Name, String Address, String gender) {
        super(Name, Address, gender);
        this.semseter = semseter;
        this.c = c;
        this.RegNo = RegNo;
        this.pincode = pincode;
    }

    public Students(String Name, String Address, String gender) {
        super(Name, Address, gender);
    }


    

    public int getSemseter() {
        return semseter;
    }

    public void setSemseter(int semseter) {
        this.semseter = semseter;
    }

    public Courses getC() {
        return c;
    }

    public void setC(Courses c) {
        this.c = c;
    }

    public int getRegNo() {
        return RegNo;
    }

    public void setRegNo(int RegNo) {
        this.RegNo = RegNo;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public void mysql(){
         
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/students","root","root");
            Statement stmt=con.createStatement();
            String query = "INSERT INTO students(RegNo,PinCode,Name,Courses) VALUES ('18101555' , '45802' , 'Ahmed' , 'Is')";
            stmt.executeUpdate(query);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        //here sonoo is database name, root is username and password
        
        }
    public void quiz() throws IOException{
        Socket s = new Socket("localhost", 8000);
        DataInputStream inputFromServer = new DataInputStream(s.getInputStream());
        DataOutputStream outputToServer = new DataOutputStream(s.getOutputStream());
    }
}