/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Kivanc
 */
public class Grid_1 {
    
    ReadFromTxt read = new ReadFromTxt();
    
    
    static int graph[][] = new int[11][13];

    ReadFromTxt rTxt = new ReadFromTxt();

    JFrame f1;
    JFrame f2;
    JPanel jp;
    TextArea t;
    ImageIcon image1;
    JLabel jlabel2;
//    JLabel jlabel_garg = new JLabel(); // for gargamel
//    JLabel jlabel_azman = new JLabel(); // for azman
    ArrayList<Rectangle> available_coords = new ArrayList<>(); // for location
    ArrayList<Dusman> enemies = new ArrayList<>(); // for enemies
    Oyuncu player;
    Dusman enemy, enemy2;
   
    
    public static void addFrame(JFrame f, ArrayList<Dusman> d) {
        for (Dusman x : d) {
            f.add(x.charJLabel);
            if (x.name().startsWith("G")) {
                x.totalDistance.setBounds(1100, 400, 250, 50);
            } else {
                x.totalDistance.setBounds(1350, 400, 250, 50);
            }
            f.add(x.totalDistance);
        }
    }

    public static void addPaths(ArrayList<Dusman> enemies, Oyuncu player) {
        for (Dusman d : enemies) {
            d.setXLoc(d.charJLabel.getBounds().y / 80);
            d.setYLoc(d.charJLabel.getBounds().x / 80);
            d.enKisaYol(player);
            System.out.println("arr (first) -> --- " + d.distPaths);

        }
    }


    public void addPathLabel(ArrayList<Dusman> enemies, JFrame f, int i, int j, Oyuncu player) {
        for (Dusman d : enemies) {
           // JLabel temp = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\O.png"));
           JLabel temp;
           if(d.name().startsWith("G"))
               temp = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\X_.png"));
           else
               temp = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\X.png"));
           
            temp.setBounds(80 * j, 80 * i, 80, 80);
            temp.setVisible(false);
            //System.out.println(temp.getBounds());
            d.distPaths.add(temp.getBounds());
            d.labels.add(temp);
            f.add(temp);
            //System.out.println("arr new size --> " + d.distPaths.size());
        }
    }

    public Grid_1() throws InterruptedException {
//        jlabel2.setBounds(1040, 560, 80, 80);
        
        read.readMatrix();
        graph = read.getArr();
        
        
        Scanner input = new Scanner(System.in);
        System.out.println("Lutfen oyuncunuzu secin : ");
        System.out.println("Tembel sirin icin 1 \t Gozluklu sirin icin 2");
        int decision = input.nextInt();

        if (decision == 1) {
            player = new TembelSirin(0, "Tembel Sirin", "oyuncu", 20, 480, 400);
            player.charJLabel.setBounds(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y, 80, 80);
            player.setXLoc(player.charJLabel.getBounds().y / 80);
            player.setYLoc(player.charJLabel.getBounds().x / 80);
            enemies = rTxt.arrangeEnemies(player, f1);
            addPaths(enemies, player);
            player.sirine.setBounds(1040, 560, 80, 80);
        } else if (decision == 2) {
            player = new GozlukluSirin(1, "Gozluklu sirin", "oyuncu", 20, 480, 400);
            player.charJLabel.setBounds(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y, 80, 80);
            player.setXLoc(player.charJLabel.getBounds().y / 80);
            player.setYLoc(player.charJLabel.getBounds().x / 80);
            enemies = rTxt.arrangeEnemies(player, f1);
            addPaths(enemies, player);
            player.sirine.setBounds(1040, 560, 80, 80);
        } else {
            System.out.println("Lutfen 1 ya da 2 ye basin");
        }

        
        // button
        JButton b1;
        // frames
        f1 = new JFrame("Ana Ekran");
//        for (Dusman d : enemies) { // try
//            for (JLabel j : d.distPaths) {
//                f1.add(j);
//            }
//        }
        
        f1.add(player.skorLabel);
        f1.add(player.sirine);
//        f1.add(jlabel2);
        //
        f1.add(player.charJLabel); // important
        addFrame(f1, enemies);

        //jlabel2 = new JLabel("(" + (player.getYLoc() / 80) + "," + (player.getXLoc() / 80) + ")");
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 1) {
                    b1 = new JButton();
                    b1.setBounds(80 * j, 80 * i, 80, 80); // (x,y,w,h)
                    b1.setBackground(Color.WHITE);
                    b1.setFocusable(false);
                    available_coords.add(b1.getBounds());
                    addPathLabel(enemies, f1, i, j, player);
                    player.gold.recordAvailableSquares(player, f1, i, j);
                    f1.add(b1);
                    if (i == 0 && j == 3) {
                        b1.setIcon(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\letterA.jpg"));
                    } else if (i == 0 && j == 10) {
                        b1.setIcon(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\letterB.jpg"));
                    } else if (i == 5 && j == 0) {
                        b1.setIcon(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\letterC.jpg"));
                    } else if (i == 10 && j == 3) {
                        b1.setIcon(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\letterD.jpg"));
                    }
                } else {
                    b1 = new JButton();
                    b1.setBackground(Color.GRAY);
                    b1.setBounds(80 * j, 80 * i, 80, 80);
                    b1.setFocusable(false);
                    f1.add(b1);
                }
            }
        }
        

//        JLabel ne = new JLabel(new ImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\O.png"));
//        ne.setBounds(1300, 400, 80, 85);
//        f1.add(ne);
        player.typeOfAdvanceOnMaze(player, f1, jlabel2, available_coords, enemies);
        
        f1.setLayout(null);
        f1.setSize(1500, 1200); // (w,h)
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        //
        player.gold.putGoldsOnGrid();
        Thread.sleep(2000);
        player.gold.putGoldsOnGrid();
        Thread.sleep(2000);
        player.gold.putGoldsOnGrid();
        Thread.sleep(2000);
        player.gold.putGoldsOnGrid();
        Thread.sleep(2000);
        player.gold.putGoldsOnGrid();
    }

}
