package General_Algorithms;

import com.sun.source.tree.WhileLoopTree;

import java.util.LinkedList;

public class DAG_SSSP {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    /*
        In this class we will provide an implementation for a single source shortest path
        in Directed Acyclic Graph with O(V + E) time complexity
     */




    public static void main(){}

    public static double[] DAG_SSSP_Algorithm(double[][] adj_mat, int sourceNode){
        int[] finishing_times = topo_sort(adj_mat);

        double[] distancesFromSource = new double[adj_mat.length];
        for (int i = 0; i < distancesFromSource.length; i++) {
            distancesFromSource[i] = Integer.MAX_VALUE;
        }
        distancesFromSource[sourceNode] = 0;
        // go through all the nodes in as sorted, we know there is no edges back so we can start from the start to the end
        // and do relax for each node, it is guaranteed to be a sort with runtime of O(V + E).
        // we run through all the edges linearly and through all the nodes linearly.
        for (int i = 0; i < finishing_times.length; i++) {
            relax(distancesFromSource,adj_mat,finishing_times[i]);
        }
        return distancesFromSource;
    }
    private static void relax(double[] distances_fromSource, double[][] adj_matrix, int currnet_node){
        for (int i = 0; i < adj_matrix.length; i++) {
            // if an edge exists and the path is less than the already existing distance then update it
            if(adj_matrix[currnet_node][i] != Integer.MAX_VALUE)
            if(distances_fromSource[currnet_node] + adj_matrix[currnet_node][i] < distances_fromSource[i]){
                distances_fromSource[i] = adj_matrix[currnet_node][i] + distances_fromSource[currnet_node];
            }
        }
    }
    /**
     *
     * Given an adjecancy matrix return the nodes at sorted finishing times after DFS run
     * @param adj_mat rows and columns are nodes value edge weight, The matrix represents a DAG
     * @return index as finishing time and value as node
     */
    public static int[] topo_sort(double[][] adj_mat){
        int[] finishing_times = new int[adj_mat.length*2];
        int[] discovery_times = new int[adj_mat.length*2];
        int[] colors = new int[adj_mat.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = WHITE;
        }
        int time = 0;
        int current;
        LinkedList<Integer> nodesQueue = new LinkedList<>();
        for (int i = 0; i < adj_mat.length; i++) {
            nodesQueue.add(i);
            colors[i] = GRAY;
            while(!nodesQueue.isEmpty()){
                current = nodesQueue.removeLast();
                discovery_times[time++] = current;
                // go through the edges of the current node and add them to the queue if there is an edge and the node is white
                for (int j = 0; j < adj_mat.length; j++) {
                    if(j != current && colors[current] == WHITE && adj_mat[current][j] != Integer.MAX_VALUE){
                        nodesQueue.add(j);
                        discovery_times[time++] = j;
                        colors[j] = GRAY;
                    }
                }
                colors[current] = BLACK;
                finishing_times[time++] = current;
            }
        }
        return finishing_times;
    }



}
