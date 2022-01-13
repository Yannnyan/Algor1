package Divide_AndConquer;

public class SubArraySum {

    public static void main(String[] args){
        int[] arr = {-2,-5,6,-2,-3,1,5,-6};
        System.out.println(SubArraySum(arr));
    }

    // algorithm that gives the maximum sum of a continous sub array in the original array

    // naive O(n^2)
    public static int SubArraySum(int[] array){
        int sum = Integer.MIN_VALUE ;
        int currentSum;
        for (int i = 0; i < array.length; i++) {
            currentSum = 0;
            for (int j = i; j < array.length; j++) {
                currentSum = currentSum + array[j];
                if(currentSum > sum){
                    sum = currentSum;
                }
            }
        }
        return sum;
    }

    public static int max_crossingSum(int[] array, int start,int pivot, int end){
        int sum_Left = Integer.MIN_VALUE,sum_current=0;
        for (int i = pivot; i >= start; i--) {
            sum_current += array[i];
            if(sum_current > sum_Left){
                sum_Left = sum_current;
            }
        }
        int sum_Right = Integer.MIN_VALUE;
        sum_current = 0;
        for (int i = pivot+1; i <= end; i++) {
            sum_current+=array[i];
            if(sum_current > sum_Right){
                sum_Right = sum_current;
            }
        }
        return Math.max(Math.max(sum_Left,sum_Right), (sum_Right+sum_Left));
    }

    public static int beter_SubArraySum(int[] array, int start, int end){
        if( (end - start +1) == 1){
            return array[start];
        }
        int pivot = start + (end - start +1)/2;
        return Math.max( Math.max(beter_SubArraySum(array,start,pivot),
                        beter_SubArraySum(array,pivot+1, end)),
                        max_crossingSum(array,start,pivot,end));
    }

}
