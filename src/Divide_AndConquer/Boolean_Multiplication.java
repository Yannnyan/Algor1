package Divide_AndConquer;

public class Boolean_Multiplication {

    public static void main(String[] args){
        int[] a = {0,1,1,0,1,0,1,0};
        int[] b = {1,1,0,1,0,0,1,0};
        int[] a1 = {1,1};
        int[] a2 = {1,1};
        //printBoolean(school_multiplication(convertToBoolean(a),convertToBoolean(b)));
        //printBoolean(school_multiplication(convertToBoolean(a1),convertToBoolean(a2)));
//        printBoolean(attemp_ToImprove1(convertToBoolean(a),convertToBoolean(b)));
//        printBoolean(attemp_ToImprove1(convertToBoolean(a1),convertToBoolean(a2)));
    }
    public static boolean[] convertToBoolean(int[] a){
        boolean[] bol = new boolean[a.length];
        for (int i = 0; i < a.length; i++) {
            bol[i] = a[i] == 0 ? false : true;
        }
        return bol;
    }
    public static void printBoolean(boolean[] bol){
        int sum=0;
        for (int i = 0; i < bol.length; i++) {
            System.out.print(bol[i] ? 1 : 0);
            if(bol[i])
                sum += Math.pow(2,bol.length -1 - i);
        }
        System.out.println();
        System.out.println(sum);
    }
    // regular long multiplication
    public static boolean[] school_multiplication(boolean[] binary1, boolean[] binary2){
        boolean[] boolSol;
        boolean[][] BoolToSum = new boolean[binary1.length][binary2.length+binary1.length];
        int indexBol ;
        for (int i = 0; i < binary1.length; i++) {
            indexBol = BoolToSum[0].length - 1 -i;
            for (int j = 0; j < binary2.length; j++) {
                BoolToSum[i][indexBol--] = And(binary1[binary1.length -1 - i],binary2[binary2.length - 1 - j]);
            }
        }
        boolSol = BoolToSum[0];
        for (int i = 1; i < BoolToSum.length; i++)
            boolSol = Add(boolSol,BoolToSum[i]);
        return boolSol;
    }

    /**
     *  The idea of the function is to use divide and conquer to solve
     *  Algo:
     *  1) General: X = x1 + x2 * 2^(len(X)/2), Y = y1 + y2 * 2^(len(Y)/2) .
     *  2) formula: X * Y = x1y1 + 2^(len(X)/2) * (x2y1 + x1y2) + 2^(len(X)/2+len(Y)/2) * x2y2 .
     *  3) there are 4 recursive calls: x1y1 , x2y1, x1y2, x2y2 .
     *  4) if len(X) == 1 || len(Y) == 1 : return small == 1 ? big : 0
     *  Complexity O(n^(log_2 3))
     */
    public static boolean[] attemp_ToImprove1(boolean[] X, boolean[] Y){
        if(X.length == 1 || X.length == 1) // 4
            return X.length > Y.length ? Y[0] ? X : new boolean[X.length] : X[0] ? Y : new boolean[Y.length];

        // 2
        boolean[] x1y1 = attemp_ToImprove1(copyArr(X,X.length/2,false), copyArr(Y, X.length/2, false));
        boolean[] x2y1 = attemp_ToImprove1(copyArr(X,X.length/2,true), copyArr(Y, X.length/2, false));
        boolean[] x1y2 = attemp_ToImprove1(copyArr(X,X.length/2,false), copyArr(Y, X.length/2, true));
        boolean[] x2y2 = attemp_ToImprove1(copyArr(X,X.length/2,true), copyArr(Y, X.length/2, true));
        // formula
        boolean[] x1y2Plusx2y1 = Add(x2y1,x1y2);
        boolean[] XY = Add( Add( x1y1, shiftLeft( x1y2Plusx2y1,X.length/2,  (int) Math.max(X.length,Y.length)+1))
                , shiftLeft(x2y2,X.length/2 + Y.length/2, (int) Math.max(X.length,Y.length)+1));

        return XY;
    }
    // O(n)
    public static boolean[] shiftLeft(boolean[] bools, int shift, int length){
        boolean[] ret = new boolean[length];
        int index = bools.length - 1;
        for (int i = ret.length-1; i >= 0 && index >= 0 ; i--) {
            ret[i - shift] = bools[index--];
        }
        return ret;
    }
    public static boolean[] copyArr(boolean[] bools, int length, boolean Left){
        boolean[] bol = new boolean[length];
        int indexBol = 0;
        for (int i = Left ? 0 : length; i < (Left ? length : bools.length) ; i++) {
            bol[indexBol++] = bools[i];
        }
        return bol;
    }






















    //////////////////////////////// Dumb functions "low level" in java stuff /////////////////////////////


    private static boolean[] Add(boolean[] bin1, boolean[] bin2){
        boolean[] binSol = new boolean[bin1.length > bin2.length ? bin1.length+1 : bin2.length+1];
        boolean carry = false;
        boolean[] bin11 , bin22 ;
        // zeros out all unused elements
       bin11 = memBoolCpy(bin1,binSol.length);
       bin22 = memBoolCpy(bin2,binSol.length);
       boolean sum;
        for (int i = binSol.length-1; i >= 0 ; i--) {
            sum = Xor(bin11[i],bin22[i]);
            binSol[i] = Xor(sum,carry);
            carry = Or(And(bin11[i],bin22[i]),And(sum,carry));
        }
        return binSol;
    }
    private static boolean[] memBoolCpy(boolean[] bin1, int length){
        boolean[] cop = new boolean[length];
        int index = length - 1;
        for (int i = bin1.length - 1; i >=0 ; i--) {
            cop[index--] = bin1[i];
        }
        return cop;
    }
    private static boolean Xor(boolean A, boolean B){
        if((A == true && B == false) || (A == false && B == true))
            return true;
        return false;
    }
    private static boolean Or(boolean A, Boolean B){
        return A || B;
    }
    private static boolean And(boolean A, boolean B){
        return A && B;
    }


}
