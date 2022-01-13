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





}
