package General_Algorithms;

import GUI_PACKAGE.Controller;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;


public class Minimal_Spanning_Tree {



    public static void main(String[] args){



    }

    public static int comparator1(int[] edge1, int[] edge2){
        if(edge1[2] < edge2[2]){
            return -1;
        }
        else if (edge1[2] == edge2[2]){
            return 0;
        }
        else
            return 1;
    }
    private static void sortByComparator1(ArrayList<int[]> edges){
        int[] min = {0,0,Integer.MAX_VALUE};
        int[] temp;
        int index;
        for (int i = 0; i < edges.size(); i++) {
            index = i;
            for (int j = i; j < edges.size()-1; j++) {
                if(comparator1(edges.get(j),min) < 1){
                    min = edges.get(j);
                    index = j;
                }
            }
            temp = edges.get(i);
            edges.set(i,min);
            edges.set(index,temp);
        }
    }
    public static void Kruskal_Algorithm(int[][] adj_matrix){
        int vertices = adj_matrix.length;
        // first is src , second is src dest and weight in a pair somehow
        // alows to get with O(1) an edge
        HashMap<Integer, ArrayList> edges = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            edges.put(i,new ArrayList());
            for (int j = 0; j < vertices; j++) {
                if(adj_matrix[i][j] != Integer.MAX_VALUE){
                    int[] edge = new int[3];
                    edge[0] = i;edge[1] = j;edge[2] = adj_matrix[i][j];
                    edges.get(i).add(edge);
                }
            }
        }
        // must sort for the algorithm
        ArrayList<int[]> joined = new ArrayList();
        for (int i = 0; i < edges.size(); i++) {
            joined.addAll(edges.get(i));
        }
        // quicksort should be implemented with a comparator, not a problem
        sortByComparator1(joined);
        HashMap<Integer,Node> nodes = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            nodes.put(i,union_find.create(i));
        }
        int src,dest;
        int[] edge;
        for (int i = 0; i < joined.size(); i++) {
            edge = joined.remove(0);
            src = edge[0]; dest = edge[1];
            // tree has at most vertices-1 edges
            // if both nodes at different connected components then merge them together
            // by the cut theorem we receive a group A which is a sub set in the minimal spanning tree at each point
            // and then the final result is the spanning tree
            if(union_find.find(nodes.get(src)) != union_find.find(nodes.get(dest)) && i <= vertices-1) {
                union_find.Union(nodes.get(src), nodes.get(dest));
                nodes.remove(dest);
            }
        }

    }
}
