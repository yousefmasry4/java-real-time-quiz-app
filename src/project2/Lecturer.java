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
import java.util.ArrayList;

     

public class Lecturer extends person {
    
    private int RegNo;
    
    private int PinCode;
            
   ArrayList<Courses> course = new ArrayList<Courses>();

    public Lecturer(String Name, String Address, String gender, int RegNo , int PinCode) {
        super(Name, Address, gender);
        this.RegNo = RegNo;
        this.PinCode = PinCode ;
        
    }
   
   
}
