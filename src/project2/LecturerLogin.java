/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author sara
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
class LecturerLogin {

    private int RegNo;
    String name=null;
    private String pincode;

    public LecturerLogin(int reg, String pincode) {
        this.RegNo = reg;
        this.pincode = pincode;
    }
    

    public boolean Acceptlogin() {
        //TODO:bygeb el data mn db w yshof sa7 walla la2 law sa7 return 1 else 0
        Connection con;
        try {
            //TODO: change password to root
            con = DriverManager.getConnection("jdbc:mysql://localhost/exam","root","admin");
            Statement stmt=con.createStatement();
            String select = "SELECT name FROM lecturer WHERE ID = "+RegNo+" AND pass = "+pincode;
            ResultSet res = stmt.executeQuery(select);
            while(res.next()){
                this.name= res.getString("name");
                dumy_data.id=this.name;
            }
            con.close();
            System.out.println("name"+name);
            return name != null;
        } catch (SQLException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
    }
}