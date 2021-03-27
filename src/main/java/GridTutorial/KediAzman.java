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
public class KediAzman extends Dusman {

    public ArrayList<ShortestPathGrid.QVertex> paths;

    public static int count = 0;
    

    public KediAzman(int dusmanID, String dusmanAdi, String dusmanTur) {
        super(dusmanID, dusmanAdi, dusmanTur);
    }

    public KediAzman(int dusmanID, String dusmanAdi, String dusmanTur, int x, int y) {
        super(dusmanID, dusmanAdi, dusmanTur, x, y);
    }

    @Override
    public void followPlayer(Dusman enemy, Oyuncu player, JFrame f) {
        enemy.setImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\azman.jpeg");
        enemy.charJLabel.setIcon(enemy.getImageIcon());
        // enemy.charJLabel.setBounds(enemy.getXLoc(), enemy.getYLoc(), 80, 80);

    }

    @Override
    public void enKisaYol(Oyuncu player) {

        if(count==0)
            this.totalDistance.setText("Minimum distance for azman : " + this.spGrid.minimumDistance(myGrid, player, this));
        else if(this.command == false)
            this.totalDistance.setText("Minimum distance for azman : " + this.spGrid.minimumDistance(myGrid, player, this));
        else
            this.totalDistance.setText("Minimum distance for azman : " + (this.spGrid.minimumDistance(myGrid, player, this)-1));
        this.paths = this.spGrid.getValidPaths();
        if (count != 0 && this.command) {
            this.ilerle(paths, player);
        }

        System.out.print("With Azman ");
        spGrid.showElements(paths);

        //System.out.println("fucking arr size(azman) : " + this.distPaths.size());
        this.makeControlWhetherValidOrNot();
        count++;
        
    }

    @Override
    public void makeControlWhetherValidOrNot() {

        Rectangle temp;
        JLabel impl;
        this.makeThemAllUnvisible();
        for (ShortestPathGrid.QVertex q : this.paths) {
            temp = new Rectangle(q.col * 80, q.row * 80, 80, 80);
            impl = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\X.png"));
            if (this.distPaths.contains(temp)) {
                // System.out.println("Yes it contains at : " + this.distPaths.indexOf(temp));
                int index = this.distPaths.indexOf(temp);
                int last = paths.size() - 1;
                
                if(this.labels.get(index).getBounds().x == this.paths.get(last).col*80 && this.labels.get(index).getBounds().y == this.paths.get(last).row*80)
                    this.labels.get(index).setVisible(false);
                else
                    this.labels.get(index).setVisible(true);
//                if(count!= this.paths.size()-1)
//                    this.labels.get(index).setVisible(true);                                    
            } else {
                // System.out.println("Maalesef");
            }
        }

    }

    @Override
    public void ilerle(ArrayList<ShortestPathGrid.QVertex> paths, Oyuncu player) {
        if (paths.size() == 1 && paths.get(0).dist == 0) {
            System.out.println("Y A K A L A N D I N");
        } else {
            int index = paths.size() - 1;
            ShortestPathGrid.QVertex t = this.paths.get(index - 1);
//            System.out.println("next state : " + t.row + "," + t.col);
            this.charJLabel.setBounds(t.col * 80, t.row * 80, 80, 80);
            this.setXLoc(this.charJLabel.getBounds().y / 80);
            this.setYLoc(this.charJLabel.getBounds().x / 80);
            System.out.println("azman nokta : " + this.charJLabel.getBounds());
        }
        
        
    }

}
