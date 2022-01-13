package GreedyAlgor;

public class Activities_Problem {


    // the problem is to find the maximum amount of activities to finish
    // given the starting time and the ending time of each activity

    // starting and ending is an array of two, a[0] = activity index, a[1] = time
    // given that the two arrays are already sorted
    public static int[] maxActivities(int starting[][], int ending[][]){
        int num_activities = 0;
        int[] arr = new int[starting.length];
        arr[0] = ending[0][0];
        int current_finising_time = ending[0][1];
        for (int i = 1; i < starting.length; i++) {
            if(current_finising_time <= ending[i][1]){
                num_activities+=1;
                current_finising_time=ending[i][1];
                arr[i] = ending[i][0];
            }
        }
        return arr;
    }

    public static int[] maxActivities_Values(){

        return null;
    }


}
