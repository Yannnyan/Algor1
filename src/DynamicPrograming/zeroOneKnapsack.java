package DynamicPrograming;

public class zeroOneKnapsack {
    int[] profit;
    int[] weight;
    int capacity;
    int[][] table;


    public zeroOneKnapsack(int[] profit, int[] weight, int capacity){
        this.profit=profit;
        this.weight=weight;
        this.capacity=capacity;
        table = new int[this.weight.length][this.capacity];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    /**
     * Given an array of weight and profit for each item, calculate the maximum amount of profit possible to take without exceeding
     * the capacity of weight.
     * @param n the number of items left to take.
     * @param currentCapacity the current amount of capacity.
     * @return the maximum amount of weight possible to take.
     */
    public int zeroOneKnapsack_dynamic(int n, int currentCapacity){
        // the table rows is how many items we've taken
        // the table collumns is how much capacity is given
        // the table value is the maximum amount of profit we can make by taking i items with j capacity
        if(this.table[n][currentCapacity] != Integer.MAX_VALUE) return this.table[n][currentCapacity];
        if(n == 0 || currentCapacity == 0)
            return 0;
        else if(currentCapacity - this.weight[n-1] < 0) table[n][currentCapacity] = zeroOneKnapsack_dynamic(n-1,currentCapacity);
        else {
            int temp1 = zeroOneKnapsack_dynamic(n-1,currentCapacity-this.weight[n-1]);
            int temp2 = zeroOneKnapsack_dynamic(n - 1, currentCapacity);
            table[n][currentCapacity] =  Math.max(temp1,temp2);
        }
        return table[n][currentCapacity];
            /**
         * The idea is to go through all possible solutions and take the maximal one.
         * This can be done with a recursive structure with exponential time or dynamic programming approach with polynomial time.
         * For each case we start with a current weight to be 0- the weight of the items we already have taken,
         * and the profit we've made that's 0.
         * Then we go with this in to two routes, one route is to take the next item, and the second is to not take.
         * Ofcourse if it's not valid then don't take it.
         */
    }

    /**
     *
     * @param n number of items left to visit
     * @return the maximum profit for the current capacity and items left
     */
    public int recursiveSol(int n, int capacity){
        if(n == 0 || capacity == 0) return 0;
        // now n is not 0 and capacity not 0
        // check if the item could be taken
        else if(capacity - this.weight[n-1] < 0) return recursiveSol(n-1,capacity);
        // now the item selected could be taken both routes
        else
            return Math.max(recursiveSol(n-1,capacity-this.weight[n-1]) + this.profit[n-1], recursiveSol(n-1,capacity));
    }


}
