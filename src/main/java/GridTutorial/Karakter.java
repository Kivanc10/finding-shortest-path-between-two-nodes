/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;
import javax.swing.*;
/**
 *
 * @author Kivanc
 */
public abstract class Karakter {

    private int ID;
    private String name;
    private String type; // friend or enemy
    public Location loc;
    private String image_path;
    private ImageIcon image;
    public JLabel charJLabel = new JLabel();
    
    ReadFromTxt read = new ReadFromTxt();
    
    
    
    int[][] myGrid = new int[11][13];
    
    

    public Karakter() {
        
    }
    
    public Karakter(int ID, String name, String type) {
        System.out.println("Karakter olusturuluyor");
        this.ID = ID;
        this.name = name;
        this.type = type; 
        
    }

    public Karakter(int ID, String name, String type, int x_loc, int y_loc) {
        System.out.println("Karakter olusturuluyor");
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.loc = new Location(x_loc,y_loc);
        read.readMatrix();
        myGrid = read.getArr();
    }
    
    
    public void enKisaYol(Oyuncu player){};
    
    //public void findSmallestPath()
    // setter
    public void setId(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setXLoc(int x_loc) {
        this.loc.x = x_loc;
    }

    public void setYLoc(int y_loc) {
        this.loc.y = y_loc;
    }
    
    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }
    
    public void setImageIcon(String path){
        this.image = new ImageIcon(path);
    }
    
    public void setJLabel(JLabel jlabel) {
        this.charJLabel = jlabel;
    }

    // getter
    public int getId() {
        return this.ID;
    }

    public String name() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getXLoc() {
        return this.loc.x;
    }

    public int getYLoc() {
        return this.loc.y;
    }
    
    public String getImagePath() {
        return this.image_path;
    }
    
    public ImageIcon getImageIcon() {
        return this.image;
    }
    
    public JLabel getJLabel() {
        return this.charJLabel;
    }
    
}
