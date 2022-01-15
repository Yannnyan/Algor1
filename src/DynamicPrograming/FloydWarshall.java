package DynamicPrograming;

import static Graph_Algorithms.BFS.printMatrix;

public class FloydWarshall {

    // for all adjec matrices give infinity if no edge or 0 if the edge is from the node to itself

    public static void main(String[] args){

    }



    // this algorithm builds n matrices.
    // the first matrix is the adjec given
    // say there is n generated matrices, so for the n+1 matrix, determine it
    // by taking the minimal of the minimal of the last route to a random vertice and then to the wanted vertice,
    // and the last route to the requered destination
    public static void pathsFromAllToAll(int[][] adjec_matrix){
        int vertices = adjec_matrix.length;
        int minimum = Integer.MAX_VALUE;
        int[][] lastMatrix = adjec_matrix;
        for (int i = 1; i <= vertices-1; i++) {
            int[][] currentMatrix = new int[vertices][vertices];
            for (int j = 0; j < vertices; j++) {
                for (int k = 0; k < vertices; k++) {
                    minimum = Integer.MAX_VALUE;
                    for (int l = 0; l < vertices; l++) {
                        minimum = Math.min(minimum,lastMatrix[j][l] + adjec_matrix[l][k]);
                    }
                    currentMatrix[j][k] = Math.min(minimum, lastMatrix[j][k]);
                }
            }
            lastMatrix = currentMatrix;
        }

    printMatrix(lastMatrix);
    }


    public static void beter_pathsToAll(int[][] adj_matrix){
//          improves runtime to O(n^3 * log(n))
        int vertices = adj_matrix.length;
        int num_max_matrix = (int)Math.ceil(Math.log(vertices) / Math.log(2));
        int[][] lastMatrix = adj_matrix;
        int[][] tempMatrix = new int[vertices][vertices];
        int minimum;
        for (int i = 0; i < num_max_matrix; i++) { // maybe <= didn't think about it
            for (int j = 0; j < vertices; j++) {
                for (int k = 0; k < vertices; k++) {
                    minimum = Integer.MAX_VALUE;
                    for (int l = 0; l < vertices; l++) {
                        minimum = Math.min(minimum,adj_matrix[j][l] + adj_matrix[l][k]);
                    }
                    tempMatrix[j][k] = Math.min(minimum,lastMatrix[j][k]);
                }
            }
            lastMatrix = tempMatrix;
        }
        printMatrix(lastMatrix);
    }

    public static void FloydWarshallAlgorithm(int[][] adj_matrix){
        // improves complexity to O(n^3)
        int vertices = adj_matrix.length;
        int[][] last_matrix = adj_matrix;
        int[][] temp_matrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                for (int k = 0; k < vertices; k++) {
                    temp_matrix[i][j] = Math.min(last_matrix[j][k], last_matrix[j][i] + last_matrix[i][k]);
                }
            }
            last_matrix = temp_matrix;
        }
        printMatrix(last_matrix);
    }








}
