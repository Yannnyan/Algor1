package DynamicPrograming;

public class Binominal {


    public static void main(String[] args){
        System.out.println(BinominalRecursive(5,3));
        BinominalDynamic(5,3);


    }
    public static int BinominalRecursive(int n, int k){
        if(k == 0 || n == k){
            return 1;
        }
        return BinominalRecursive(n-1,k) + BinominalRecursive(n-1,k-1);
    }


    public static void BinominalDynamic(int n, int k){ // calculate n choose k
        if (n < k) return;
        int[][] myBinumArray = new int[n+1][k+1];
        for (int i = 0; i < n+1; i++) {
            myBinumArray[i][0] = 1;
        }
        int j=0;
        for (int i = 0; i < n+1 && j < k+1; i++,j++) {
            myBinumArray[i][j] = 1;
        }
        for (int i = 1; i < n+1; i++) {
            for (int l = 1; l < k+1; l++) {
                myBinumArray[i][l] = myBinumArray[i-1][l] + myBinumArray[i-1][l-1];
            }
        }
        System.out.println(myBinumArray[n][k]);
    }


}
