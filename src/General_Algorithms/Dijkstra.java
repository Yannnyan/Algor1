package General_Algorithms;

import java.util.PriorityQueue;

import static Graph_Algorithms.BFS.printVector;

public class Dijkstra {




    public static void main(String[] args){




    }
    private static void relax(int[] distances,int[][] adj_matrix, int id){
        for (int i = 0; i < adj_matrix.length; i++) {
//            if(distances[i] > adj_matrix[id][i] + distances[id]){
//                distances[id] = adj_matrix[id][i] + distances[id];
//                // min also works less work also
//            }
            distances[i] = Math.min(distances[i],adj_matrix[id][i] + distances[id]);
        }
    }
    public static void Dijkstra(int[][] adj_matrix, int startID){
        int vertices = adj_matrix.length;
        int[] distances = new int[vertices];
        PriorityQueue<Integer> queue= new PriorityQueue<>();
        for (int i = 0; i < vertices; i++) {
            queue.add(i);
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startID] = 0;
        int minimum;
        while(!queue.isEmpty()){
            minimum = queue.poll();
            relax(distances,adj_matrix,minimum);
        }
        printVector(distances, "From S to id: ");
    }
}
