package DynamicPrograming;

public class Fibonacci {
    public static void main(String[] args){
        fiboDinamic(20);
        System.out.println(fibonacciRecursive(5));

    }
    public static int fibonacciRecursive(int k){
        if(k == 1 || k == 2){
            return 1;
        }
        return fibonacciRecursive(k-1) + fibonacciRecursive(k-2);
    }
    public static void fiboDinamic(int k){
        int[] array = new int[k+1];
        array[1] =1 ;
        array[2] = 1;
        for (int i = 3; i < k+1; i++) {
            array[i] = array[i-1] + array[i-2];
        }
        System.out.println(array[k]);




    }
}
