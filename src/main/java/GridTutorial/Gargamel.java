/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kivanc
 */
public class Gargamel extends Dusman {
    
    public static int count = 0;

    public ArrayList<ShortestPathGrid.QVertex> paths;

    public Gargamel(int dusmanID, String dusmanAdi, String dusmanTur) {
        super(dusmanID, dusmanAdi, dusmanTur);
        // this.setImagePath("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\gargamel.png");

    }

    public Gargamel(int dusmanID, String dusmanAdi, String dusmanTur, int x, int y) {
        super(dusmanID, dusmanAdi, dusmanTur, x, y);
        System.out.println("gargamel bounds : " + this.charJLabel.getBounds());
    }

    @Override
    public void followPlayer(Dusman enemy, Oyuncu player, JFrame f) {
//        jlabel = new JLabel();
        enemy.setImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\gargamel.jpg");
        enemy.charJLabel.setIcon(enemy.getImageIcon());

    }
    
//    final int f_x = this.getYLoc()/80;
//    final int f_y = this.getXLoc()/80;
    
    @Override
    public void enKisaYol(Oyuncu player) {
        // ((this.f_x == this.getYLoc()/80) && (this.f_y == this.getXLoc()/80))
        System.out.println("gargamel fucker konum ->" + this.getXLoc() + "," + this.getYLoc());
        if(count == 0)
            this.totalDistance.setText("Minimum distance for gargamel : " + this.spGrid.minimumDistance(myGrid, player, this));
        else if(this.command == false)
            this.totalDistance.setText("Minimum distance for gargamel : " + this.spGrid.minimumDistance(myGrid, player, this));
        else
            this.totalDistance.setText("Minimum distance for gargamel : " + (this.spGrid.minimumDistance(myGrid, player, this)-2));
        
        this.paths = this.spGrid.getValidPaths();
        
        if(count != 0 && this.command)
            this.ilerle(paths, player);
        
        System.out.print("With gargamel ");
        spGrid.showElements(paths);
       
//        System.out.println("fucking arr size : " + this.distPaths.size());
        this.makeControlWhetherValidOrNot();
        count++;
        
    }

    
    
    @Override
    public void makeControlWhetherValidOrNot() {
        
        Rectangle temp;
        JLabel impl;
        this.makeThemAllUnvisible();
        for(ShortestPathGrid.QVertex q : this.paths){
            temp = new Rectangle(q.col*80, q.row*80, 80, 80);
            impl = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\X_.png"));
            if(this.distPaths.contains(temp)){
                 int index = this.distPaths.indexOf(temp);
                int last = paths.size() - 1;
                int seconLast = paths.size()-2;
                
                if(this.labels.get(index).getBounds().x == this.paths.get(last).col*80 && this.labels.get(index).getBounds().y == this.paths.get(last).row*80)
                    this.labels.get(index).setVisible(false);
                else if(this.command && this.labels.get(index).getBounds().x == this.paths.get(seconLast).col*80 && this.labels.get(index).getBounds().y == this.paths.get(seconLast).row*80)
                    this.labels.get(index).setVisible(false);
                else
                    this.labels.get(index).setVisible(true);
            }
             else{
//                System.out.println("Maalesef");
            }
        }
        
    }

    @Override
    public void ilerle(ArrayList<ShortestPathGrid.QVertex> paths, Oyuncu player) {
        if(paths.size() == 1 && paths.get(0).dist == 0){
            System.out.println("Y A K A L A N D I N");
        }else {
            int index = paths.size() - 1;
            ShortestPathGrid.QVertex t = this.paths.get(index - 2);
            this.charJLabel.setBounds(t.col * 80, t.row * 80, 80, 80);
            this.setXLoc(this.charJLabel.getBounds().y / 80);
            this.setYLoc(this.charJLabel.getBounds().x / 80);
            //System.out.println("gargamel nokta : " + this.charJLabel.getBounds());
        }
    }
    
    
    
    

}
