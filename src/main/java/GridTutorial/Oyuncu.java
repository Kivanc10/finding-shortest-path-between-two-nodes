/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kivanc
 */
public abstract class Oyuncu extends Karakter {
    
    public JLabel skorLabel = new JLabel();
    private static int skor = 20;
    JLabel sirine = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\sirine.jpg"));
    Gold gold = new Gold();
    
    
    public Oyuncu(int oyuncuId, String oyuncuAdi, String oyuncuTur, int skor, int x, int y) {
        super(oyuncuId, oyuncuAdi, oyuncuTur, x, y);
        this.charJLabel = new JLabel();
        this.charJLabel.setBounds(x, y, 80, 80);
        System.out.println("Label bounds : " + this.charJLabel.getBounds());
    }

    public Oyuncu(int oyuncuId, String oyuncuAdi, String oyuncuTur, int skor) {
        super(skor, oyuncuAdi, oyuncuTur);
        this.skor = skor;
    }

    public abstract void PuaniGoster(Oyuncu player);// it will be different for gozlujlu and tembel 

    public abstract void typeOfAdvanceOnMaze(Oyuncu player, JFrame f, JLabel jlabel2, ArrayList<Rectangle> available_coords, ArrayList<Dusman> enemies);

    // setter
    public void setSkor(int skor) {
        this.skor = skor;
    }

    // getter
    public int getSkor() {
        return this.skor;
    }
    
    
    public void goldQuery() {
        for(JLabel j : this.gold.gold_label){
            if(j.getName().equals("yes")){
                if((j.getBounds().x == this.getYLoc()*80) && (j.getBounds().y == this.getXLoc()*80)){
                    int temp = this.getSkor();
                    this.setSkor(temp+5);
                    j.setVisible(false);
                }
            }
        }
    }

}
