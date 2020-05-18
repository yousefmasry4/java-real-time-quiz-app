/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author HP
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StudentLogin {

    private int RegNo;
    String id=null;
    String ids;
    private int pincode;
    List<String> data = new ArrayList<String>();

    public StudentLogin(int reg, int pincode,String idd) {
        this.RegNo = reg;
        this.pincode = pincode;
        this.ids = idd;

    }
    

    public boolean Acceptlogin() throws ClassNotFoundException, SQLException {
        Connection con;
        int no;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/exam","root","admin");
            Statement stmt=con.createStatement();
            String select = "SELECT ID FROM students WHERE ID = "+RegNo+" AND pass = "+pincode;
            ResultSet res = stmt.executeQuery(select);
            
            while(res.next()){
                this.id = String.valueOf(res.getInt("ID"));
            }
            if(id !=null){
                var d=ids.split("~");
                System.out.println(d[0]
                +d[1]);
                Statement stmt2=con.createStatement();
                select = "SELECT ques FROM questions WHERE bid = '"+d[0]+"' AND goid = '"+d[1]+"'";
                ResultSet resw = stmt2.executeQuery(select);
                while(resw.next()){
                    String s=resw.getString("ques");

                    data.add(s);
                }


            }
            con.close();
            return id !=null && !data.isEmpty();
            
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
    }
}