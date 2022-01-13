package Divide_AndConquer;

public class Median {


    public static void main(String[] args){
        int arr[] = {5,4,3,2,1};
        quick_sort(arr,0,4);
        //swap(arr,1,2);

        for (int i = 0; i < 5; i++) {
            System.out.println(arr[i]);
        }
    }

    static void quick_sort(int[] array, int start , int end){
        if( start < end){
            int pivot = partition(array,start,end);
            quick_sort(array, start, pivot-1);
            quick_sort(array, pivot+1,end);
        }
    }
    static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    static int partition(int[] array,int start,int end){
        // improve this from n^2 to nlogn worst
        int pivot = start + (end-start+1)/2;
        int i=start-1, j=end;
        // now the pivot is at the end
        swap(array, pivot, j);
        j = start;
        // lets find pivot's position in the array
        while(j < end){
            if(array[j] < array[end]){
                i++;
                swap(array,i,j);
            }
            j++;
        }
        i++;
        swap(array, i,end);
        return i;
    }

    static int kth_smallest(int[] array, int k){
        quick_sort(array, 0 , array.length-1);
        return array[k];
    }
    static int kth_smallest_better(int[] array, int k){ // complexity at big lengths is O(n) and for smaller at max 2500 ops (O(n^2)) doesn't really matter
        return SELECT(array,k);
    }
    // this function selects the t's smallest element in an array in O(n) time for big input
    static int SELECT(int[] array, int t){
        if(array.length < 50){
            quick_sort(array,0,array.length);
            return array[t];
        }
        int k = SELECT_GOOD_PIVOT(array);
        // let S1 be the array which all elements bigger or equal than array[k]
        // let S2 be the array which all elements smaller than array[k]
        int[] S1, S2;
        int size1 = 0, size2=0;
        for (int i = 0; i < array.length; i++) {  // initialize the arrays
            if (array[i] < array[k])
                size1++;
            else
                size2++;
        }
        int index1=0, index2=0;
        S1 = new int[size1]; S2 = new int[size2];
        for(int i=0; i< array.length; i++){ // put values in the arrays
            if(array[i] < array[k])
                S1[index1++] = array[i];
            else
                S2[index2++] = array[i];
        }
        if(size1 == t-1){
            return k;
        }
        else if (size1 > t-1){
            return SELECT(S1,t);
        }
        else{
            return SELECT(S2,t);
        }

    }
    // this function selects a pivot that is the median of an array
    static int SELECT_GOOD_PIVOT(int[] array){
        // dividing the array into groups of 5 and sorting them this takes O(n)
        int[] medians = new int[(int)Math.ceil(array.length/5)];
        for (int i = 0; i < array.length/5 - 1; i++) {
            int start = i * 5;
            int end = (i+1)*5;
            quick_sort(array,start,end);
            medians[i] = array[2 + 5 * i];
        }
        int k = SELECT(medians, array.length/10);
        return k;
    }
    // returns array at size of end - start + 1
    static int[] getArray(int[] array, int start, int end){
        if (array.length == end - start + 1) return array;
        int[] myarray = new int[end - start +1];
        for (int i = start; i < myarray.length; i++) {
            myarray[i] = array[i];
        }
        return myarray;
    }
    static int partition_better(int[] array, int start, int end){
        int[] beter_array = getArray(array,start,end);
        int pivot = SELECT_GOOD_PIVOT(beter_array);

        int i = start -1, j =start;
        swap(array, pivot, end);
        while(j < end){
            if(array[j] < array[end]){
                i++;
                swap(array,i,j);
            }
            j++;
        }
        i++;
        swap(array,i,end);
        return array[i];
    }
    static void quick_sort_better(int[] array, int start, int end){
        if( start < end){
            int pivot = partition_better(array,start,end);
            quick_sort(array, start, pivot-1);
            quick_sort(array, pivot+1,end);
        }
    }
}
