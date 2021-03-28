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
public class Mushroom {

    ArrayList<Rectangle> mushrooms = new ArrayList<>();
    ArrayList<JLabel> mushroom_label = new ArrayList<>();
    ArrayList<Integer> random = new ArrayList<>();
    public static int count = 0;

    public Mushroom() {
        count = 0;
        random = new ArrayList<>();

    }

    public void recordAvailableSquares(Oyuncu player, JFrame f, int i, int j) {
        JLabel temp = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\mushroom.jpg"));

        temp.setBounds(80 * j, 80 * i, 80, 80);
        if (random.contains(count)) {
            //temp.setVisible(true);
            temp.setName("myes");
        } else {
            //temp.setVisible(false);
            temp.setName("mno");
        }

        this.mushrooms.add(temp.getBounds());
        this.mushroom_label.add(temp);
        //System.out.println("Golds -> " + golds);
        f.add(temp);
        count++;

    }
    
    public void showMushroomArr() {
        System.out.println("\n ----- mushrooms ---");
        for(JLabel j : this.mushroom_label){
            if(j.getName() == "myes"){
                 System.out.println(j.getBounds());
            }
        }
    }
    
    

}
