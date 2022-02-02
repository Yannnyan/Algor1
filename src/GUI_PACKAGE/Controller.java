package GUI_PACKAGE;

import Convex_Hull.AndrewAlgorithm;
import General_Algorithms.Minimal_Spanning_Tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
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
        runKruskal(adj_matrixCreate(nodes),nodes,gui);

    }
    private static double[][] adj_matrixCreate(node[] nodes){
        double[][] adj_matrix = new double[nodes.length][nodes.length];
        Random gen = new Random(7);
        double x ;
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i; j < nodes.length; j++) {
                x = gen.nextDouble();
                x = BigDecimal.valueOf(x)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                //if(x <= 0.5){
                    adj_matrix[i][j] = (x * 15);
                    adj_matrix[j][i] = (x * 15);
                //}
//                else{
//                    adj_matrix[i][j] = Integer.MAX_VALUE;
//                    adj_matrix[j][i] = Integer.MAX_VALUE;
//                }

            }
        }
        return adj_matrix;
    }
    private static ArrayList createEdges(double[][] adj_matrix, HashMap<Integer,node> nodesMap){
        ArrayList<edge> edges = new ArrayList<>();
        for (int i = 0; i < adj_matrix.length; i++)
            for (int j = 0; j < adj_matrix.length; j++)
                if(adj_matrix[i][j] != Integer.MAX_VALUE)
                    edges.add(new edge(nodesMap.get(i),nodesMap.get(j),adj_matrix[i][j]));

        return edges;
    }
    private static HashMap<Integer,node> getMapFromNodes(node[] nodes){
        HashMap<Integer,node> map = new HashMap<>();
        for (int i = 0; i < nodes.length; i++) {
            map.put(nodes[i].id,nodes[i]);
        }
        return map;
    }
    private static void runAndrew(AndrewAlgorithm andrew, node[] nodes){
        andrew.runAlgorithm(nodes);
    }
    private static void runKruskal(double[][] adj_matrix, node[] nodes,GUI gui){
        HashMap<Integer,node> map = getMapFromNodes(nodes);
        ArrayList<edge> edges = createEdges(adj_matrix,map);
        gui.panel.edges = convertFromList(edges);
        gui.panel.displayEdges = true;
        gui.panel.repaint();
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() < time + 5000) {
            continue;
        }
        gui.panel.displayEdges = false;
        gui.panel.specialEdges = convertFromList(General_Algorithms.Minimal_Spanning_Tree.Kruskal_Algorithm(map,edges));
        System.out.println(gui.panel.specialEdges.length);
        gui.panel.repaint();
    }
    private static edge[] convertFromList(ArrayList<edge>edges){
        edge[] edgess = new edge[edges.size()];
        int index=0;
        for(edge ed : edges){
            edgess[index++] = ed;
        }
        return edgess;
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
    public static void updateGui(edge[] edges){
        if(edges != null)
            gui.update(edges);
    }
}
