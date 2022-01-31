package Convex_Hull;

import java.util.LinkedList;
import java.util.Stack;

public class AndrewAlgorithm {

    private int comparator(node node1, node node2){
        if(node1.x > node2.x) return 1;
        else if(node1.x == node2.x) return 0;
        else return -1;
    }
    private void swap(int i, int j, node[] nodes){
        node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }
    private void sortArray(node[] nodes){
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i; j < nodes.length; j++) {
                if(comparator(nodes[i],nodes[j]) == 1) swap(i,j,nodes);
            }
        }
    }
    private int orient(node node1, node node2, node node3){
        // res is the determinant of a matrix which first row is 1 and second row is x values and third row is y values
        double res = (node1.x - node3.x) * (node2.y - node3.y) - (node1.y - node3.y) * (node2.x - node3.x);
        if(res < 0)return -1;
        else if(res > 0) return 1;
        else return 0;
    }
    public void runAlgorithm(node[] nodes){
        sortArray(nodes);
        Stack<node> upperHull = new Stack<>();
        Stack<node> lowerHull = new Stack<>();
        int n = nodes.length;
        node[] hull = new node[2*n];
        hull[0] = nodes[0];
        for (int i = 0; i < n; i++) {
            while(upperHull.size() >= 2 && orient(upperHull.get(1),upperHull.get(0),nodes[i]) < 0){
                upperHull.pop();
               // Controller.updateGui((node[])upperHull.stream().toArray());
            }
            upperHull.push(nodes[i]);
            //Controller.updateGui((node[])upperHull.stream().toArray());
        }
        int indexHull = 0;
        while(upperHull.size() > 0)
            hull[indexHull++] = upperHull.pop();
        for (int i = n -1; i >= 0; i--) {
            while(lowerHull.size() >= 2 && orient(lowerHull.get(1),lowerHull.get(0),nodes[i]) < 0){
                lowerHull.pop();
                //Controller.updateGui((node[])lowerHull.stream().toArray());
            }
            lowerHull.push(nodes[i]);
            //Controller.updateGui((node[])lowerHull.stream().toArray());
        }
        while(lowerHull.size() > 0)
            hull[indexHull] = lowerHull.pop();
        Controller.updateGui(hull);

    }


}
