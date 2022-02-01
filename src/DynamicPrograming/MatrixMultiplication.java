package DynamicPrograming;

public class MatrixMultiplication {

        // given n matrices with dimensions di x di+1
        // compute the product of the multiplication of all n matrices
        // with minimal number of scalar multiplications

    public static int MatrixMultRecursion(int i, int j, int[][] dimensions){
        int min = Integer.MAX_VALUE, temp;
        for (int k = i; k < j; k++) {
            temp =  (MatrixMultRecursion(i,k,dimensions) +  MatrixMultRecursion(k+1,j,dimensions) +
                    dimensions[k-1][1]*dimensions[k][0]*dimensions[k+1][0]);
            if (temp < min){
                min = temp;
            }
        }
        return min;
    }
    // dimensions[0] = dimension1 rows, dimensions[1] = dimension2 collumns
    public static int MatrixMultDynamic(int[][] dimensions){
        int[][] table = new int[dimensions.length][dimensions.length];
        for (int i = 0; i < table.length; i++) {
            table[i][i] = 0;
        }
        int x, min;
        /**
         * startIndex specifies in which index the sub array starts at
         * i is the index at which we want to begin cutting the sub array
         * j is the range of the subarray i.e the length of the sub array
         *
         */
        for (int startIndex = 0; startIndex < table.length; startIndex++) {
            for (int i = 0; i < table.length-1; i++) {
                int j = i + startIndex;
                min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    x = table[startIndex][k] + table[k+1][j] + dimensions[0][startIndex] * dimensions[1][k] * dimensions[1][j-1];
                    if(x < min)
                        min = x;
                }
                table[startIndex][j] = min;
            }
        }
        return table[table.length-1][table.length-1];
        
        /**
         *  Need to calculate the minimal amount of operations to complete multiplication of all the n = dimensions.length matrices
         *  so consider a 2d table with rows representing where to start the opening brackets, and the columns where to put
         *  end of the opening brackets (M1*M2)*(M3) , (M1)*(M2*M3) and the value at table[i][j] is the minimal amount of operations
         *  to complete mult with brackets at i,j as said above.
         *  to fill up the table, we need to go through all possible occurences starting from bottom up
         *  
         */

    }




}
