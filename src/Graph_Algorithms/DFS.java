package Graph_Algorithms;

import java.util.ArrayList;

public class DFS {
    private static final int WHITE = 0,GRAY = 1, BLACK = 2;


    public static void main(String[] args){


    }
    public static int[] DFS(int[][] adjecancy_matrix){
        int[] colors = new int[adjecancy_matrix.length], discovery_times = new int[adjecancy_matrix.length],
            predecessors = new int[adjecancy_matrix.length], finishing_times = new int[adjecancy_matrix.length];
        for(int i=0; i< adjecancy_matrix.length; i++){
            colors[i] = WHITE;
            predecessors[i] = -1;
        }
        int time = 0;
        for (int i = 0; i < adjecancy_matrix.length; i++) {
            if(colors[i] == WHITE)
                DFS_VISIT(adjecancy_matrix,colors,discovery_times,finishing_times,predecessors,time,i);
        }
        return finishing_times;
    }
    public static void DFS_VISIT(int[][] adjecancy_matrix,int[] colors, int[] discovery_times,int[] finishing_times, int[] predecessors,
                                 int time,  int visit_node){
        colors[visit_node] = GRAY;
        time +=1;
        discovery_times[visit_node] = time;
        for (int i = 0; i < adjecancy_matrix.length; i++) {
            if(adjecancy_matrix[visit_node][i] != -1){
                if(colors[i] == WHITE){
                    predecessors[i] = visit_node;
                    DFS_VISIT(adjecancy_matrix,colors,discovery_times,finishing_times,predecessors,time,visit_node);
                }
            }
        }
        colors[visit_node] = BLACK;
        finishing_times[visit_node] = ++time;
    }


//    public static ArrayList Topological_sort(int[][] adjecancy_matrix){
//       // if(! Belman_ford(adjecancy_matrix)) // if there is a cycle in the graph then return null
//            return null;
//        int[] finishing_times = DFS(adjecancy_matrix); // else go forth
//        ArrayList<Integer> linkedlist = new ArrayList<>();
//        //quicksort(finishing_times);
//      //  for (int i = finishing_times-1; i > 0; i--) {
//            linkedlist.add(finishing_times[i]);
//        }
//        return linkedlist;
//    }




}
