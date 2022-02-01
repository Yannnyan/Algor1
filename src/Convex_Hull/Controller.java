package Convex_Hull;

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
        node[] nodes = new node[15];
        Double x,y;
        Random gen = new Random(7);
        for (int i = 0; i < 15; i++) {
            x = gen.nextDouble() * 35;
            x = BigDecimal.valueOf(x)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            y = gen.nextDouble() * 35;
            y = BigDecimal.valueOf(y)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            nodes[i] = new node(x,y);
            nodes[i].id = i;
        }
        gui.createAndShowGUI(nodes,null);
        andrew.runAlgorithm(nodes);

    }
    public static void updateGui(node[] nodes){
        if(nodes != null)
            gui.update(nodes);


    }
}
