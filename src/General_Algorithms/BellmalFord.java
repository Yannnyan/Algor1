package General_Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static Graph_Algorithms.BFS.printVector;

public class BellmalFord {

    public static void main(String[] args){
    }
    /*
        This function designed to relax the edges going out from all the vertices in the adj matrix
     */
    private static boolean relax(int[] distances, int[][] adjec_matrix,HashMap<Integer,ArrayList<Integer>> edges, boolean checkNegative){
        int destination;
        Iterator iterator;
        for (int i = 0; i < adjec_matrix.length; i++) {
            iterator = edges.get(i).iterator();
            while(iterator.hasNext()){
                destination = (int)iterator.next();
                if(distances[destination] > (distances[i] + adjec_matrix[i][destination])){
                    distances[destination] = distances[i] + adjec_matrix[i][destination];
                    if(checkNegative)
                        return true;
                }
            }
        }
        return false;
    }

    // Bellman ford algorithm takes into account that each path can be at most vertices -1 long.
    // each time we do relax then one vertice converges and then we can continue doing this n-1 times
    // to ensure all the vertices converge
    public static Object[] BellmanFord(int[][] adjec_matrix, int startID){
        int[] distances = new int[adjec_matrix.length];
        HashMap<Integer,ArrayList<Integer>> edges = new HashMap<>();
        // initialize the edges for more elegant solution and fewer iterations on the edges
        for (int i = 0; i < adjec_matrix.length; i++) {
            distances[i] = Integer.MAX_VALUE;
            edges.put(i,new ArrayList<>());
            for (int j = 0; j < adjec_matrix.length; j++) {
                if(adjec_matrix[i][j] != Integer.MAX_VALUE)
                    edges.get(i).add(j);
            }
        }
        distances[startID] = 0;
        for (int i = 0; i < adjec_matrix.length; i++) { // do relax n times
            relax(distances,adjec_matrix,edges,false);
        }
        Object[] ob = new Object[2];
        if(relax(distances,adjec_matrix,edges,true)) { // detect negative cycle
            ob[0] = true;
            ob[1] = null;
        }
        else{
            ob[0] = false;
            ob[1] = distances;
        }
        return ob;
    }






}
