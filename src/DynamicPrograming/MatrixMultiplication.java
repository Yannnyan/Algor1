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
    // dimensions[0] = dimension1, dimensions[1] = dimension2
    public static void MatrixMultDynamic(int i, int k, int[][] dimenstions){
        int[][] myCTable = new int[i+1][k+1];
        for (int j = 0, d=0; j < i+1 && d < k+1; j++, d++) {
            myCTable[j][d] = 0;
        }
        // minimum k multiplied will be stored here
        int[][] myKTable = new int[i+1][k+1];




    }




}
