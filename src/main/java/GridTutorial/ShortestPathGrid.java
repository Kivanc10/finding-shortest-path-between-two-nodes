/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridTutorial;

import java.awt.Rectangle;
import java.util.*;

/**
 *
 * @author Kivanc
 */
public class ShortestPathGrid {

    static ArrayList<QVertex> wholeVertex = new ArrayList<>();
    static ArrayList<QVertex> validVertex = new ArrayList<>();

    public static void showElements(ArrayList<QVertex> q) {
        System.out.println("\n --- show valid paths ----\n");
        for (QVertex a : q) {
            System.out.println("coords : (" + a.row + "," + a.col + ") , dist  : " + a.dist);
        }
        System.out.println("\n --- paths are shown -----\n");
    } 

    static boolean isRightSide(QVertex q1, QVertex q2) {
        if (q1.row == q2.row && (q1.col + 1) == q2.col) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isLeftSide(QVertex q1, QVertex q2) {
        if (q1.row == q2.row && (q1.col - 1) == q2.col) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isUpSide(QVertex q1, QVertex q2) {
        if ((q1.row - 1) == q2.row && q1.col == q2.col) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isDownSide(QVertex q1, QVertex q2) {
        if ((q1.row + 1) == q2.row && q1.col == q2.col) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList reverseArr(ArrayList<QVertex> normal) {
        ArrayList<QVertex> reverse = new ArrayList<>();
        for (int i = normal.size() - 1; i >= 0; i--) {
            reverse.add(normal.get(i));
        }

        return reverse;
    }

    public void findParents(ArrayList<QVertex> nodes) {
        ArrayList<QVertex> reverse = reverseArr(nodes);

        boolean result = false;
        QVertex first = new QVertex(0, 0, 0);

        for (int i = 0; i < reverse.size(); i++) {
            if (!result) {
                first = reverse.get(i);
                validVertex.add(first);
                //System.out.println("first coords : " + first.row + "," + first.col);
//                System.out.println("start");
            }

            if (i == (reverse.size() - 1)) {
//                System.out.println("finish");
                break;
            }

            for (QVertex subQ : reverse) {
                if ((subQ.dist + 1) == first.dist) {
                    //System.out.println("subqqqq coords : (" + subQ.row + "," + subQ.col + ") , dist  : " + subQ.dist);
                    if (isUpSide(first, subQ)) {
                        first = subQ;
//                        System.out.println("first coods : " + first.row + "," + first.col);
                        validVertex.add(first);
                        result = true;
                    }
                    if (isDownSide(first, subQ)) {
                        first = subQ;
//                        System.out.println("first coods : " + first.row + "," + first.col);
                        validVertex.add(first);
                        result = true;
                    }
                    if (isRightSide(first, subQ)) {
                        first = subQ;
//                        System.out.println("first coods : " + first.row + "," + first.col);
                        validVertex.add(first);
                        result = true;
                    }

                    if (isLeftSide(first, subQ)) {
                        first = subQ;
//                        System.out.println("first coods : " + first.row + "," + first.col);
                        validVertex.add(first);
                        result = true;
                    }
                }
            }

        } // end for

        //showElements(validVertex);//

    } // end func

    public ArrayList getValidPaths() {
        return validVertex;
    }

    class QVertex {

        int row, col, dist;

        public QVertex(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public int minimumDistance(int[][] grid, Oyuncu player, Dusman enemy) {
        QVertex source = new QVertex(0, 0, 0); // for source vertex
        // some arrangements for source vertex
        source.row = enemy.getXLoc();
        source.col = enemy.getYLoc();
        
        wholeVertex.clear();
        validVertex.clear();
        

        // applying dijsktra's algorithm with queue
        Queue<QVertex> queue = new LinkedList<>();

        // initally, source vertex was appended into queue
        queue.add(new QVertex(source.row, source.col, 0));

        // make control vertex to learn whether it is visited or not
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // dive the loop into to advance on grid,algorithm will calculate distance between source to destination
        while (!queue.isEmpty()) {
            QVertex temp = queue.remove(); // pop qvertex item
            System.out.println(temp.row + "," + temp.col + ", dist : " + temp.dist);
            wholeVertex.add(temp); // append vertex to list
            // destination found
            if (temp.row == player.getXLoc() && temp.col == player.getYLoc()) {
                System.out.println("destination : " + temp.row + ", " + temp.col);
                this.findParents(wholeVertex);
                getValidPaths();

                return temp.dist;
            }
            // I will make control whenever advancing each direction whether the related point is valid or not

            // moving up
            if (isItValid(temp.row - 1, temp.col, grid, visited)) {
                // if point is valid,increase the distance
                queue.add(new QVertex(temp.row - 1, temp.col, temp.dist + 1));
                // make visited the related point
                visited[temp.row - 1][temp.col] = true;
            }

            // moving down
            if (isItValid(temp.row + 1, temp.col, grid, visited)) {
                queue.add(new QVertex(temp.row + 1, temp.col, temp.dist + 1));
                visited[temp.row + 1][temp.col] = true;
            }

            // moving left
            if (isItValid(temp.row, temp.col - 1, grid, visited)) {
                queue.add(new QVertex(temp.row, temp.col - 1, temp.dist + 1));
                visited[temp.row][temp.col - 1] = true;
            }

            // moving right
            if (isItValid(temp.row, temp.col + 1, grid, visited)) {
                queue.add(new QVertex(temp.row, temp.col + 1, temp.dist + 1));
                visited[temp.row][temp.col + 1] = true;
            }

        } // end of the while loop 

        return -1;
    }

    public boolean isItValid(int x, int y, int[][] grid, boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 0 && visited[x][y] == false) {
            return true;
        }
        return false;
    }

}
