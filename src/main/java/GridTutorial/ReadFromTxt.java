/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import javax.swing.JFrame;

/**
 *
 * @author Kivanc
 */
public class ReadFromTxt {

    public String txtPath = "D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\harita.txt";
    public static ArrayList<String> txtCollector = new ArrayList<>();
    public ArrayList<Dusman> enemies = new ArrayList<>();
    public Dusman enemy, enemy2;
    public int[][] matrix = new int[11][13];
    

    public void readTxt1() {
        try {
            File file = new File(this.txtPath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {

                System.out.println(sc.next());

            }
        } catch (Exception e) {
            System.out.println("An error occured during the read file");
        }
    }

    public void readEnemies() {
        try {
            File file = new File(this.txtPath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null) {
                if (s.startsWith("0") || s.startsWith("1")) {
                    continue;
                } else {
//                    System.out.println(s);
                    if (s.equals("") || s.equals(" ")) {
                        continue;
                    } else {
                        txtCollector.add(s);
                    }

                }
            }
            System.out.println(txtCollector);
        } catch (Exception e) {
        }
    }
// Karakter:Gargamel,Kapi:D
    //Karakter / Azman,Kapi / B

    public void readMatrix() {
        try {
            File file = new File(this.txtPath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            int row=0,col=0;
            while ((s = br.readLine()) != null) {
                if (s.startsWith("K")) {
                    continue;
                } else {
                    col=0;
                    for (String a : s.split("	")) {
//                        System.out.print(Integer.parseInt(a.trim()) + " ");
                        this.matrix[row][col] = Integer.parseInt(a.trim());
                        col++;
                    }
                    row++;
//                    System.out.println("\n-------------------");
                }
            }
        } catch (Exception e) {
        }

    }
    
    public int [][] getArr() {
        return this.matrix;
    }
    
    public void showArr() {
        System.out.println("------ show arr -------- ");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Dusman> arrangeEnemies(Oyuncu player, JFrame f1) {
        this.readEnemies();
        if (txtCollector.size() == 1) {
            String x = txtCollector.get(0);
            String door = x.split(":")[2]; // represent the door
            String character = x.split(":")[1].replace(",Kapi".trim(), "");
            if (x.contains(character)) {
                if (door.equals("A")) {
                    if (character.contains("G".toUpperCase())) {
                        enemy = new Gargamel(3, character, character, 240, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, character, character, 240, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                } else if (door.equals("B")) {
                    if (character.contains("G")) {
                        enemy = new Gargamel(3, character, character, 800, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, character, character, 800, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                } else if (door.equals("C")) {
                    if (character.contains("G")) {
                        enemy = new Gargamel(3, character, character, 0, 400);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, character, character, 0, 400);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                } else if (door.equals("D")) {
                    if (character.contains(("G"))) {
                        enemy = new Gargamel(3, character, character, 240, 800);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, character, character, 240, 800);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
            }

        }

        if (txtCollector.size() == 2) {
            String x1 = txtCollector.get(0); //  Karakter:Azman,Kapi:B
            String x2 = txtCollector.get(1);  // Karakter:Gargamel,Kapi:D   
            String char1 = x1.split(":")[1].replace(",Kapi".trim(), ""); // azman
            String char2 = x2.split(":")[1].replace(",Kapi".trim(), ""); // gargamel
            String door1 = x1.split(":")[2]; // B
            String door2 = x2.split(":")[2]; // D

            if (x1.contains(char1)) {
                if (door1.equals("A")) {
                    if (char1.contains("G")) {
                        enemy = new Gargamel(3, char1, char1, 240, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char1, char1, 240, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
                if (door1.equals("B")) {
                    if (char1.contains("G")) {
                        enemy = new Gargamel(3, char1, char1, 800, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char1, char1, 800, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
                if (door1.equals("C")) {
                    if (char1.contains("G")) {
                        enemy = new Gargamel(3, char1, char1, 0, 400);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char1, char1, 0, 400);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
                if (door1.equals("D")) {
                    if (char1.contains("G")) {
                        enemy = new Gargamel(3, char1, char1, 240, 800);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char1, char1, 240, 800);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
            } //            else if(x1.contains(char2)){
            //                  if (door2.equals("A")) {
            //                    if (char2.contains("G")) {
            //                        enemy = new Gargamel(3, char2, char2, 240, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char2, char2, 240, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //                if (door2.equals("B")) {
            //                    if (char2.contains("G")) {
            //                        enemy = new Gargamel(3, char2, char2, 800, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char2, char2, 800, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //                if (door2.equals("C")) {
            //                    if (char2.contains("G")) {
            //                        enemy = new Gargamel(3, char2, char2, 0, 400);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char2, char2, 0, 400);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //                if (door2.equals("D")) {
            //                    if (char2.contains("G")) {
            //                        enemy = new Gargamel(3, char2, char2, 240, 800);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char2, char2, 240, 800);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //            }
            //
            //            else if(x2.contains(char1)){
            //                  if (door1.equals("A")) {
            //                    if (char1.contains("G")) {
            //                        enemy = new Gargamel(3, char1, char1, 240, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char1, char1, 240, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //                if (door1.equals("B")) {
            //                    if (char1.contains("G")) {
            //                        enemy = new Gargamel(3, char1, char1, 800, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char1, char1, 800, 0);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //                if (door1.equals("C")) {
            //                    if (char1.contains("G")) {
            //                        enemy = new Gargamel(3, char1, char1, 0, 400);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char1, char1, 0, 400);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //                if (door1.equals("D")) {
            //                    if (char1.contains("G")) {
            //                        enemy = new Gargamel(3, char1, char1, 240, 800);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    } else {
            //                        enemy = new KediAzman(4, char1, char1, 240, 800);
            //                        enemy.followPlayer(enemy, player, f1);
            //                        enemies.add(enemy);
            //                    }
            //                }
            //            }
            //
            if (x2.contains(char2)) {
                if (door2.equals("A")) {
                    if (char2.contains("G")) {
                        enemy = new Gargamel(3, char2, char2, 240, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char2, char2, 240, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
                if (door2.equals("B")) {
                    if (char2.contains("G")) {
                        enemy = new Gargamel(3, char2, char2, 800, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char2, char2, 800, 0);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
                if (door2.equals("C")) {
                    if (char2.contains("G")) {
                        enemy = new Gargamel(3, char2, char2, 0, 400);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char2, char2, 0, 400);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
                if (door2.equals("D")) {
                    if (char2.contains("G")) {
                        enemy = new Gargamel(3, char2, char2, 240, 800);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    } else {
                        enemy = new KediAzman(4, char2, char2, 240, 800);
                        enemy.followPlayer(enemy, player, f1);
                        enemies.add(enemy);
                    }
                }
            }
        } else {
            System.out.println("Bossssssssssss");
        }
        return enemies;
    }

    public ArrayList<String> getList() {
        return txtCollector;
    }

}
