package Graph_Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

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
        int current;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < adjecancy_matrix.length; i++) {
            if(colors[i] == WHITE) {
                stack.push(i);
                colors[i] = GRAY;
                discovery_times[i] = time++;
            }
            while(!stack.isEmpty()){
                current = stack.pop();
                for (int j = 0; j < adjecancy_matrix.length; j++) {
                    if(adjecancy_matrix[current][j] != Integer.MAX_VALUE && colors[j] == WHITE){
                        colors[j] = GRAY;
                        predecessors[j] = current;
                        discovery_times[j] = time++;
                        stack.push(j);
                    }
                }
                finishing_times[current] = time++;
                colors[current] = BLACK;
            }
        }
        // the roots of the dfs trees are symbolized with predesesor = -1
        return finishing_times;
    }

    /**
     * Graph is acyclic iff there it does not contain back edges.
     * @param adj_matrix
     * @return
     */
    private static boolean isAcyclic(int[][] adj_matrix) {
        int[] colors = new int[adj_matrix.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = WHITE;
        }
        Stack<Integer> stack = new Stack<>();
        int cur;
        for (int i = 0; i < adj_matrix.length; i++) {
            if (colors[i] == WHITE) {
                colors[i] = GRAY;
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                cur = stack.pop();
                for (int j = 0; j < adj_matrix.length; j++) {
                    if (colors[i] == GRAY)
                        return false; // back edge detected
                    if (colors[i] == WHITE) {
                        colors[i] = GRAY;
                        stack.push(i);
                    }
                }
            }
        }
        return true;// no back edges detected
    }
    private static void sortArray(int[] arr){
        int min,temp;
        int index;
        for (int i = 0; i < arr.length; i++) {
            index = i;
            min = Integer.MAX_VALUE;
            for (int j = 0; j < arr.length; j++) {
                if(min > arr[j]){
                    index = j;
                    min = arr[j];
                }
            }
            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    public static int[] Topological_sort(int[][] adjecancy_matrix) throws GraphException{
        /* there is another method that might be more effective
           which is to use a stack. And when coloring a node black in dfs
           push to the stack. O(m + n), worst case m = n^2, so O(n^2). then sorting is better.
           but generally the other method is better.
        */
        // could be implemented so that dfs throws exception if detects cycle, but I decided to separate those
        if(!isAcyclic(adjecancy_matrix))
            throw new GraphException();
        int[] finishing_times = DFS(adjecancy_matrix);
        sortArray(finishing_times);
        return finishing_times;
    }
}
