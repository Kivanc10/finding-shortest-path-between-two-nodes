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
public abstract class Dusman extends Karakter {

    public JLabel totalDistance = new JLabel();
    ShortestPathGrid spGrid = new ShortestPathGrid();
    ArrayList<Rectangle> distPaths = new ArrayList<>();
    ArrayList<JLabel> labels = new ArrayList<>();
    
    public boolean command = true;
    
    final int f_x = this.getYLoc()/80;
    final int f_y = this.getXLoc()/80;
            
            
    public Dusman(int dusmanID, String dusmanAdi, String dusmanTur, int x, int y) {
        super(dusmanID, dusmanAdi, dusmanTur, x, y);
        this.charJLabel = new JLabel();
        this.charJLabel.setBounds(x, y, 80, 80);
        
    }

    public Dusman(int dusmanID, String dusmanAdi, String dusmanTur) {
        super(dusmanID, dusmanAdi, dusmanTur);

    }
    

    public abstract void followPlayer(Dusman enemy, Oyuncu player, JFrame f);

    // setter
    @Override
    public void enKisaYol(Oyuncu player) {
        int distance = spGrid.minimumDistance(myGrid, player, this);
        //System.out.println("Minimum distance : " + distance);
        this.totalDistance.setText("Minimum distance : " + distance);
        
    }

    public abstract void makeControlWhetherValidOrNot();
    
    public void makeThemAllUnvisible() {
        for(JLabel j : this.labels){
            j.setVisible(false);
        }
    }
    
    
    public abstract void ilerle(ArrayList<ShortestPathGrid.QVertex> paths,Oyuncu player);
    
    public int selectDistOne(ArrayList<ShortestPathGrid.QVertex> qitem){
        
        for(ShortestPathGrid.QVertex q : qitem){
            if (q.dist == 1)
                return qitem.indexOf(q);
        }
        return -1;
    }
    
    public int getFinalX(){
        return f_x;
    }
    
    public int getFinalY(){
        return f_y;
    }
    
    
}
