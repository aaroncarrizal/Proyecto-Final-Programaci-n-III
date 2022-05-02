package main;

import javax.swing.JOptionPane;

public class ME1 extends Exception{

    public ME1(String cadena){
        JOptionPane.showMessageDialog(null,cadena);
    }
}
