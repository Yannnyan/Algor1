package Graph_Algorithms;

import java.util.ArrayList;

public class BFS {

    private static final int WHITE = 0,GRAY = 1, BLACK = 2;
    public static void printVector(int[] vector, String printing){
        System.out.println("printing now "+ printing);
        for (int i = 0; i < vector.length; i++) {System.out.print("for node id "+ i +" "+ vector[i]);}
        System.out.println();
    }
    public static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }
    // get adjecancy matrix of nodes
    public static void myBFS(int[][] adjecancyMatrix, int s){
        int[] distances_from_s = new int[adjecancyMatrix.length];
        distances_from_s[s] = 0;
        int[] predecessors = new int[adjecancyMatrix.length];
        predecessors[s] = s;
        int[] colors = new int[adjecancyMatrix.length];
        ArrayList<Integer> q = new ArrayList<>();
        q.add(s);
        for (int i = 0; i < adjecancyMatrix.length; i++) {
            colors[i] = WHITE;
        }
        int current;
        while (!q.isEmpty()) {
            current = q.remove(0);
            BFSVISIT(adjecancyMatrix,distances_from_s,predecessors,colors,q,current);
        }
        printVector(distances_from_s, "Distances");
        printVector(predecessors, "Predecessors");
    }


    public static void BFSVISIT(int[][] adjecancyMatrix, int[] distances, int[] predecessors, int[] colors,
                                ArrayList q, int nodeID ){
        for (int i = 0; i < adjecancyMatrix.length; i++) {
            if(colors[i] == WHITE && adjecancyMatrix[nodeID][i] != -1) {
                distances[i] = distances[nodeID] + 1;
                predecessors[i] = nodeID;
                colors[i] = GRAY;
                q.add(i);
            }
        }
        colors[nodeID] = BLACK;
    }


}
