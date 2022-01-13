package DynamicPrograming;

public class WorldSeriesOdds {



    public static void main(String[] args){
        System.out.println(worldSeiriesRecursive(3,4,0.5));
        worldSeriesDynamic(3,3,0.4);

    }
    // given i is num game for team A to win the game
    // j is num games for team B to win the game
    // q is the probability A wins one game

    // compute what is the probability for A to win the tournament given i,j
    public static double worldSeiriesRecursive(int i, int j, double q){
        if(i == 0){
            return 1;
        }
        else if(j==0){
            return 0;
        }
        else{
            return q*worldSeiriesRecursive(i-1,j,q) + (1-q)*worldSeiriesRecursive(i,j-1,q);
        }
    }

    public static void worldSeriesDynamic(int i, int j, double q){

        double[][] myProbabilityArray = new double[i+1][j+1];
        for (int k = 0; k < i+1; k++) {
            myProbabilityArray[k][0] = 0;
        }
        for (int k = 0; k < j+1; k++) {
            myProbabilityArray[0][k] = 1;
        }
        for (int k = 1; k < i+1; k++) {
            for (int l = 1; l < j+1; l++) {
                myProbabilityArray[k][l] = q*myProbabilityArray[k-1][l] + (1-q)*myProbabilityArray[k][l-1];
            }
        }
        System.out.println(myProbabilityArray[i][j]);

    }


}
