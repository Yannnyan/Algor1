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
        node temp;
        double min;
        int index=0;
        for(int i=0; i< nodes.length; i++){
            index=i;
            min = Integer.MAX_VALUE;
            for (int j = i; j < nodes.length; j++) {
                if(nodes[j].x < min) {
                    min = nodes[j].x;
                    index = j;
                }
            }
            temp = nodes[i];
            nodes[i] = nodes[index];
            nodes[index] = temp;
        }
    }
    private int orient(node node1, node node2, node node3){
        // res is the determinant of a matrix which first row is 1 and second row is x values and third row is y values
        double res = (node1.x - node3.x) * (node2.y - node3.y) - ((node1.y - node3.y) * (node2.x - node3.x));
        if(res < 0)return -1;
        else if(res > 0) return 1;
        else return 0;
    }
    public node[] getArray(LinkedList<node> stak){
        node[] myar = new node[stak.size()];
        for (int i = 0; i < myar.length; i++) {
            myar[i] = stak.pop();
        }
        return myar;
    }
    public void runAlgorithm(node[] nodes){
        sortArray(nodes);

        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].id+ " ");
        }
        System.out.println();
        LinkedList<node> upperHull = new LinkedList<>();
        LinkedList<node> lowerHull = new LinkedList<>();
        int n = nodes.length;
        node[] hull = new node[n];
        //hull[0] = nodes[0];
        //long time;
        for (int i = 0; i < n; i++) {
            while(upperHull.size() >= 2 && orient(upperHull.get(1),upperHull.getFirst(),nodes[i]) <= 0){
                upperHull.removeFirst();
                //Controller.updateGui((node[]) upperHull.stream().toArray());
//                time = System.currentTimeMillis();
//                while(System.currentTimeMillis() - time < 500)
//                    continue;
            }
            upperHull.addFirst(nodes[i]);
            //time = System.currentTimeMillis();
//           while(System.currentTimeMillis() - time < 500)
//               continue;
//           if(upperHull != null)
//             Controller.updateGui(getArray(upperHull));

        }
        int indexHull = 0;
        while(upperHull.size() > 0)
            hull[indexHull++] = upperHull.removeLast();
        for (int i = 0; i < indexHull; i++) {
            System.out.print(hull[i].x + " ");
        }

        for (int i = n - 1; i >= 0; i--) {
            while(lowerHull.size() >= 2 && orient(lowerHull.get(lowerHull.size()-2),lowerHull.getLast(),nodes[i]) <= 0){
                lowerHull.removeLast();
                //Controller.updateGui((node[])lowerHull.stream().toArray());
            }
            lowerHull.add(nodes[i]);
            //Controller.updateGui((node[])lowerHull.stream().toArray());
        }
        while(lowerHull.size() > 0)
            hull[indexHull++] = lowerHull.pop();
        Controller.updateGui(hull);

    }


}
