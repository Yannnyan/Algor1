package GUI_PACKAGE;

import Convex_Hull.AndrewAlgorithm;
import General_Algorithms.Minimal_Spanning_Tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Controller {
    private static GUI gui;
    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gui = new GUI();
            }
        });
        gui = new GUI();
        AndrewAlgorithm andrew = new AndrewAlgorithm();
        node[] nodes = createNodes();
        gui.createAndShowGUI(nodes,null);
        runKruskal(adj_matrixCreate(nodes));

    }
    private static int[][] adj_matrixCreate(node[] nodes){
        int[][] adj_matrix = new int[nodes.length][nodes.length];
        Random gen = new Random(7);
        double x ;
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                x = gen.nextDouble();
                x = BigDecimal.valueOf(x)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                if(x <= 0.5){
                    adj_matrix[i][j] = (int)(x * 15);
                }
                else{
                    adj_matrix[i][j] = Integer.MAX_VALUE;
                }

            }
        }

        return null;
    }
    private static void runAndrew(AndrewAlgorithm andrew, node[] nodes){
        andrew.runAlgorithm(nodes);
    }
    private static void runKruskal(int[][] adj_matrix){
        General_Algorithms.Minimal_Spanning_Tree.Kruskal_Algorithm(adj_matrix);
    }
    private static node[] createNodes(){
        node[] nodes = new node[15];
        Double x,y;
        Random gen = new Random(7);
        for (int i = 0; i < 15; i++) {
            x = gen.nextDouble() * 35;
           // x = Math.random() * 35;
            x = BigDecimal.valueOf(x)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            y = gen.nextDouble() * 35;
            //y = Math.random() * 35;
            y = BigDecimal.valueOf(y)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            nodes[i] = new node(x,y);
            nodes[i].id = i;
        }
        return nodes;
    }
    public static void updateGui(node[] nodes){
        if(nodes != null)
            gui.update(nodes);

    }
}
