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
public class Gold {

    JLabel gold_img = new JLabel();
    ArrayList<Rectangle> golds = new ArrayList<>();
    ArrayList<JLabel> gold_label = new ArrayList<>();
    ArrayList<Integer> random = new ArrayList<>();
    public static int count = 0;
    public boolean state = false;

    public Gold() {
        gold_img = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\gold.jpg"));
        count = 0;
        random = new ArrayList<>();
        this.produceRandomNums();
        this.setState(false);
    }

    public void recordAvailableSquares(Oyuncu player, JFrame f, int i, int j) {
        JLabel temp = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\gold.jpg"));

        temp.setBounds(80 * j, 80 * i, 80, 80);
        if (random.contains(count)) {
            //temp.setVisible(true);
            temp.setName("yes");
        } else {
            //temp.setVisible(false);
            temp.setName("no");
        }
        temp.setVisible(false);
        this.golds.add(temp.getBounds());
        this.gold_label.add(temp);
        //System.out.println("Golds -> " + golds);
        f.add(temp);
        count++;
        this.setState(true);
    }

    public void showGoldsArr() {
        System.out.println("\n ------ golds --------\n");
        for (JLabel j : this.gold_label) {
            if (j.getName() == "yes") {
                System.out.println(j.getBounds());
            }
        }
    }

    public void putGoldsOnGrid() {
        this.setState(false);
        try {
            this.produceRandomNums();
            for (int i = 0; i < 5; i++) {
                int temp = this.random.get(i);
                JLabel label = this.gold_label.get(temp);
                label.setVisible(true);
                label.setName("yes");
            }
            Thread.sleep(5000);
            this.setState(true);
            this.makeThemUnvisible();
            random.clear();
        } catch (Exception e) {
        }
    }

    public void produceRandomNums() {
       // this.setState(false);
        for (int i = 0; i < 5; i++) {
            int temp = (int) (Math.random() * 77);
            if (!random.contains(temp)) {
                random.add(temp);
            } else {
                if ((temp + 1) < 77) {
                    random.add(temp + 1);
                } else {
                    random.add(temp - 1);
                }
            }
        }
    }

    public void makeThemUnvisible() {
        for (JLabel j : this.gold_label) {
            j.setVisible(false);
            j.setName("no");
        }
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
