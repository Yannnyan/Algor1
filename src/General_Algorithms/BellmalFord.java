package General_Algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import static Graph_Algorithms.BFS.printVector;

public class BellmalFord {

    public static void main(String[] args){




    }

    private static void relaxEdge(int[] distances, int[][] adjec_matrix,HashMap<Integer,Integer> edges){
        int destination;
        for (int i = 0; i < edges.size(); i++) {
            destination = edges.get(i);
            distances[destination] = Math.min(distances[i] + adjec_matrix[i][destination],  distances[destination]);
        }
    }

    // Bellman ford algorithm tkaes into account that each path can be at most vertices -1 long.
    // each time we do relax then one vertice converges and then we can continue doing this n-1 times
    // to ensure all the vertices converge
    public static Boolean BellmanFord(int[][] adjec_matrix, int startID){
        int vertices = adjec_matrix.length;
        int[] distances = new int[vertices];
        HashMap<Integer,Integer> edges = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if(adjec_matrix[i][j] != 0 && adjec_matrix[i][j] != Integer.MAX_VALUE)
                    edges.put(i,j);
            }
        }
        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startID] = 0;

        for (int i = 0; i < vertices; i++) {
            relaxEdge(distances,adjec_matrix,edges);
        }
        int destination;
        for (int i = 0; i < edges.size(); i++) {
            destination = edges.get(i);
            if(distances[i] + adjec_matrix[i][destination] < distances[i]){
                return false;// negative cycle detected
            }
        }
        printVector(distances, "Distances from " + startID + " is :");
        return true;
    }






}
