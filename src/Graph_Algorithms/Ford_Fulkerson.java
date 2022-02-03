package Graph_Algorithms;

import java.util.Stack;

public class Ford_Fulkerson {
    private static final int WHITE = 1;
    private static final int GRAY = 2;
    private static final int BLACK = 3;

    /*
        This algorithm represents the ford fulkerson algorithm.
        Designed to solve a flow graph problems.
        The purpose of the algorithm is to find the maximal flow in the graph.

     */
    /**
     *
     *
     * @param adj_matrix  adjecancy matrix to represent the capacity from a node to node, note that this matrix should be symmetric
     * @param s The source node to start with
     * @param v The end node
     * @return the flow function for each edge
     */
    // this is the edmond's karp algorithm implementation for the ford fulkerson method
    public static double[][] FordFulkerson_Algorithm(double[][] adj_matrix, int s, int v){
        double[][] flow = new double[adj_matrix.length][adj_matrix.length];
        for (int i = 0; i < adj_matrix.length; i++) {
            for (int j = 0; j < adj_matrix.length; j++) {
                // means there is no edge so no flow neither
                if(adj_matrix[i][i] == Integer.MAX_VALUE)
                    flow[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] parents;
        Stack<Integer> path;
        do {
            parents = findPathBFS(adj_matrix, flow, s);
            path = getPathFromSToV(parents, s, v);
            if(path != null)
                incrementFlowByMinimum(flow,path);
        }
        while(path != null);
        return flow;
    }
    // receives a path from s to v and a flow adjecancy matrix
    // goes through the path and increment the edges by the minimum flow in the path
    private static void incrementFlowByMinimum(double[][] flow, Stack<Integer> path){
        Stack<Integer> stack = (Stack<Integer>) path.clone();
        int first;
        double minFlow = 0;
        // find minimum
        while(!stack.isEmpty()){
            first = stack.pop();
            if( minFlow < flow[first][stack.peek()])
                minFlow = flow[first][stack.peek()];
        }
        // increment
        stack = (Stack<Integer>) path.clone();
        while(!stack.isEmpty()){
            first = stack.pop();
            flow[first][stack.peek()] += minFlow;
        }
    }
    private static Stack<Integer> getPathFromSToV(int[] parents, int s, int v){
        if(parents[v] == -1) // no path from s to v
            return null;
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        int currentParent = parents[v];
        // has to be a path from s to v, since we started from s and reached to v, we know we reached v because v has a parent
        while(currentParent != s){
            stack.push(currentParent);
            currentParent = parents[currentParent];
        }
        stack.push(s);
        return stack;
    }
    private static int[] findPathBFS(double[][] adj_matrix,double[][] flow ,int s){
        // run bfs to compute a path inside the graph
        Stack<Integer> stack  = new Stack<>();
        int[] parents = new int[adj_matrix.length], colors = new int[adj_matrix.length];
        for (int i = 0; i < adj_matrix.length; i++) {
            colors[i] = WHITE;
        }
        parents[s] = -1;
        stack.push(s);
        colors[s] = GRAY;
        int current;
        while(!stack.isEmpty()) {
            current = stack.pop();
            for (int i = 0; i < adj_matrix.length; i++) {
                // exist an edge and the node is a white node, AND the flow is not yet the max
                if(colors[i] == WHITE && adj_matrix[current][i] != Integer.MAX_VALUE && flow[current][i] != adj_matrix[current][i]) {
                    colors[i] = GRAY;
                    stack.push(i);
                    parents[i] = current;
                }
            }
            colors[current] = BLACK;
        }
        ///////////// finish BFS ///////////
        return parents;
    }




}
