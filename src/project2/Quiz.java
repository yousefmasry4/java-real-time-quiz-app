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

public class Quiz {

    private double time;

    private double marks;

    private double questionNo;

    private  ArrayList <Questions> ques = new ArrayList<Questions>();

    private Lecturer lect;

    public double calculatemarks() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getTime() {
        return time;
    }
}