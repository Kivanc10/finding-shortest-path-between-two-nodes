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
public class GozlukluSirin extends Oyuncu {

    public GozlukluSirin(int oyuncuId, String oyuncuAdi, String oyuncuTur, int skor, int x, int y) {
        super(oyuncuId, oyuncuAdi, oyuncuTur, skor, x, y);
        // System.out.println("gozluklu bounds : " + this.charJLabel.getBounds());
    }

    public GozlukluSirin(int oyuncuId, String oyuncuAdi, String oyuncuTur, int skor) {
        super(oyuncuId, oyuncuAdi, oyuncuTur, skor);
    }

    @Override
    public void PuaniGoster(Oyuncu player) {
        player.skorLabel.setBounds(1200, 200, 200, 30);
        player.skorLabel.setText("Gozluklu sirin puani " + this.getSkor());
    }

    @Override
    public void typeOfAdvanceOnMaze(Oyuncu player, JFrame f, JLabel jlabel2, ArrayList<Rectangle> available_coords, ArrayList<Dusman> enemies) {

        System.out.println("Player bounds : " + player.charJLabel.getBounds());

        player.setImageIcon("D:\\PROJECTS\\NetBeansProjects\\GridTutorial\\gozluklu.jpg");

        //jlabel.setIcon(player.getImageIcon());
        player.charJLabel.setIcon(player.getImageIcon());

        f.addKeyListener(new KeyListener() {
            int up = 0, down = 0, left = 0, right = 0;
            Rectangle step_temp;
            Dusman d, d1;

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
//                        step_temp = new Rectangle(jlabel.getBounds().x, jlabel.getBounds().y - 160, 80, 80);
                        step_temp = new Rectangle(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y - 160, 80, 80);
                        if (available_coords.contains(step_temp)) {
                            player.charJLabel.setBounds(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y - 160, 80, 80);
                            player.setXLoc(player.charJLabel.getBounds().y / 80);
                            player.setYLoc(player.charJLabel.getBounds().x / 80);
                            player.goldQuery();
                            player.PuaniGoster(player);

                            if (enemies.size() == 1) {
                                d = enemies.get(0);
                                //d.charJLabel.setBounds(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y + 80, 80, 80); //
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);

                                System.out.println("------------ dusman final x , final y --- " + d.getFinalX() + "," + d.getFinalY());
                                try {
                                    d.enKisaYol(player);    // minimum path
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }

                                    }
                                } catch (Exception ex) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }

                            } else if (enemies.size() == 2) {
                                d = enemies.get(0);
                                d1 = enemies.get(1);
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                try {
                                    d.enKisaYol(player);
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex1) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazandiniz");
                                    f.setVisible(false);
                                }
                                try {
                                    d1.enKisaYol(player);
                                    if (d1.spGrid.minimumDistance(myGrid, player, d1) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d1.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex2) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }
                            }
                        }
                        break;

                    case KeyEvent.VK_DOWN:
                        step_temp = new Rectangle(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y + 160, 80, 80);
                        if (available_coords.contains(step_temp)) {
                            player.charJLabel.setBounds(player.charJLabel.getBounds().x, player.charJLabel.getBounds().y + 160, 80, 80);
                            player.setXLoc(player.charJLabel.getBounds().y / 80);
                            player.setYLoc(player.charJLabel.getBounds().x / 80);
                            player.goldQuery();
                            player.PuaniGoster(player);
                            if (enemies.size() == 1) {
                                d = enemies.get(0);
                                // arrange enemie's location
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                //System.out.println("gargamel loc : (" + d.getXLoc() + "," + d.getYLoc() + " )");
                                try {
                                    d.enKisaYol(player);
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }

                                    }
                                } catch (Exception ex) {
                                    player.skorLabel.setText("hata");

                                }

                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }
                            } else if (enemies.size() == 2) {
                                d = enemies.get(0);
                                d1 = enemies.get(1);
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                try {
                                    d.enKisaYol(player);
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex1) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazandiniz");
                                    f.setVisible(false);
                                }
                                try {
                                    d1.enKisaYol(player);
                                    if (d1.spGrid.minimumDistance(myGrid, player, d1) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d1.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex2) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        step_temp = new Rectangle(player.charJLabel.getBounds().x + 160, player.charJLabel.getBounds().y, 80, 80);
                        if (available_coords.contains(step_temp)) {
                            player.charJLabel.setBounds(player.charJLabel.getBounds().x + 160, player.charJLabel.getBounds().y, 80, 80);
                            player.setXLoc(player.charJLabel.getBounds().y / 80);
                            player.setYLoc(player.charJLabel.getBounds().x / 80);
                            player.goldQuery();
                            player.PuaniGoster(player);
                            if (enemies.size() == 1) {
                                d = enemies.get(0);
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);

                                // System.out.println("gargamel loc : (" + d.getXLoc() + "," + d.getYLoc() + " )");
                                try {
                                    d.enKisaYol(player);
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex) {
                                    player.skorLabel.setText("hata");

                                }

                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }

                            } else if (enemies.size() == 2) {
                                d = enemies.get(0);
                                d1 = enemies.get(1);
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                try {
                                    d.enKisaYol(player);
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex1) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazandiniz");
                                    f.setVisible(false);
                                }
                                try {
                                    d1.enKisaYol(player);
                                    if (d1.spGrid.minimumDistance(myGrid, player, d1) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d1.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex2) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        step_temp = new Rectangle(player.charJLabel.getBounds().x - 160, player.charJLabel.getBounds().y, 80, 80);
                        if (available_coords.contains(step_temp)) {
                            player.charJLabel.setBounds(player.charJLabel.getBounds().x - 160, player.charJLabel.getBounds().y, 80, 80);
                            player.setXLoc(player.charJLabel.getBounds().y / 80);
                            player.setYLoc(player.charJLabel.getBounds().x / 80);
                            player.goldQuery();
                            player.PuaniGoster(player);
                            if (enemies.size() == 1) {
                                d = enemies.get(0);
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                System.out.println("tembel s. koord : " + player.getXLoc() + "," + player.getYLoc());
                                try {
                                    d.enKisaYol(player);     // minimum path
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }

                                    }
                                } catch (Exception ex) {
                                    player.skorLabel.setText("hata");
                                }

                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }
                            } else if (enemies.size() == 2) {
                                d = enemies.get(0);
                                d1 = enemies.get(1);
                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                try {
                                    d.enKisaYol(player);
                                    if (d.spGrid.minimumDistance(myGrid, player, d) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d.charJLabel.setBounds(d.getFinalY() * 80, d.getFinalX() * 80, 80, 80);
                                                d.setXLoc(d.charJLabel.getBounds().y / 80);
                                                d.setYLoc(d.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d.command = false;
                                                d.enKisaYol(player);
                                                d.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex1) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazandiniz");
                                    f.setVisible(false);
                                }
                                try {
                                    d1.enKisaYol(player);
                                    if (d1.spGrid.minimumDistance(myGrid, player, d1) == 0) {
                                        player.skorLabel.setText("yakalandin");
                                        if (d1.name().startsWith("G")) {
                                            if (player.getSkor() - 15 > 0) {
                                                player.setSkor(player.getSkor() - 15);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        } else {
                                            if (player.getSkor() - 5 > 0) {
                                                player.setSkor(player.getSkor() - 5);
                                                d1.charJLabel.setBounds(d1.getFinalY() * 80, d1.getFinalX() * 80, 80, 80);
                                                d1.setXLoc(d1.charJLabel.getBounds().y / 80);
                                                d1.setYLoc(d1.charJLabel.getBounds().x / 80);
                                                player.PuaniGoster(player);
                                                d1.command = false;
                                                d1.enKisaYol(player);
                                                d1.command = true;
                                            } else {
                                                System.out.println("oyun bitti...");
                                                f.setVisible(false);
                                            }
                                        }
                                    }
                                } catch (Exception ex2) {
                                    player.skorLabel.setText("hata");

                                }
                                if (player.getXLoc() * 80 == (player.sirine.getBounds().y) && (player.getYLoc() * 80) == (player.sirine.getBounds().x - 80)) {
                                    System.out.println("Kazand?n?z");
                                    f.setVisible(false);
                                }
                            }
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

    }

}
