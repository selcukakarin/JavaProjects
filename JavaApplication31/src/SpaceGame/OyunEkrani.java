/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceGame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Selcuk
 */
public class OyunEkrani extends JFrame{

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        
        OyunEkrani ekran=new OyunEkrani("Uzay Oyunu");
        
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(800,600);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun=new Oyun();
        oyun.requestFocus();        
        oyun.addKeyListener(oyun);        
        oyun.setFocusable(true);        
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        
        ekran.setVisible(true);
        
        
        
        
        
        
    }
    
}
