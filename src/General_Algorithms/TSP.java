package General_Algorithms;

import java.util.ArrayList;

public class TSP {
    /**
     * Given an undirected weighted graph with nodes find the minimal route to visit all the nodes from the starting node
     * and return to the starting node.
     * @param symetric_adj_matrix
     * @param starting_node
     */

    public static void TSP_Algorithm(int[][] symetric_adj_matrix, int starting_node){
        /**
         *  create 2d table, rows are all the possible sub arrays, collumns are the node to get to.
         *  the value inside the table is the minimal distance to start from starting_node and
         *  travel between all the nodes in the i'th array and finish at the j'th node
         */
        int[][] table = new int[(int)Math.pow(2,symetric_adj_matrix.length-1)][symetric_adj_matrix.length];
        // for the first row, all the cells are filled with the edges from starting_node value to get to the j'th node
        for (int i = 0; i < table[0].length; i++) {
            if(i != starting_node)
                table[0][i] = symetric_adj_matrix[starting_node][i];
        }
        // T(A,i) = min j in A ( T(A-j,j) + weight[j][i] )
        
        // create an sub set
        /*
            use reduction to counting in binary, make sequences with len n of 0, 1 that represents whether to take the element or not
            , it's an isomorphic function.
         */
        // assumed java puts 0 in all the cells of the subArray
        int[] subArrayBinary = new int[symetric_adj_matrix.length];
        ArrayList<Integer> subArray;
        int min, current;
        for (int i = 1; i < table.length; i++) {
            increment_binary(subArrayBinary);
            subArray = convert_binary_toElements(subArrayBinary,starting_node);
            for (int k = 0; k < subArrayBinary.length; k++) {
                if(k != starting_node){
                    min = Integer.MAX_VALUE;
                    for (int j : subArray) {
                        int[] temp = subArrayBinary;
                        temp[j] = 0;
                        current = table[calculateBinary(temp)][j] + symetric_adj_matrix[j][k];
                        if(current < min) min = current;
                    }
                    table[calculateBinary(subArrayBinary)][k] = min;
                }
            }
        }


    }
    // receives a binary array of 0 and 1 with desired length, and the function increments the number by 1
    private static void increment_binary(int[] currentArray){
        for (int i = currentArray.length-1; i >= 0; i--) {
            if(currentArray[i] == 1) currentArray[i] = 0;
            else{
                currentArray[i] = 1; break;
            }
        }
    }
    // receives a binary array and returns the number that it represents in decimal
    private static int calculateBinary(int[] currentArray){
        int sum = 0;
        for (int i = currentArray.length-1; i >= 0; i--) {
            sum += Math.pow(2,currentArray.length - i) * currentArray[i];
        }
        return sum;
    }
    private static ArrayList<Integer> convert_binary_toElements(int[] binaryArray, int starting_node){
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < binaryArray.length; i++) {
            if(binaryArray[i] != 0 && i != starting_node)arr.add(i);
        }
        return arr;
    }
}
