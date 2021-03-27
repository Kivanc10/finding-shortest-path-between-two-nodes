/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;

/**
 *
 * @author Kivanc
 */
public class Location {
    int x,y;

        public Location() {
        }

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // setter
        public void setX(int x){
            this.x=x;
        }
        public void setY(int y) {
            this.y = y;
        }
        // getter
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
}
