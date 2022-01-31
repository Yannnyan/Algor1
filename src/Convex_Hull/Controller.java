package Convex_Hull;

public class Controller {
    private static GUI gui;
    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gui = new GUI();
            }
        });
        AndrewAlgorithm andrew = new AndrewAlgorithm();
        node[] nodes = new node[15];
        double x,y;
        for (int i = 0; i < 15; i++) {
            x = Math.random() * 35;
            y = Math.random() * 35;
            nodes[i] = new node(x,y);
        }
        gui.createAndShowGUI(nodes,null);
        andrew.runAlgorithm(nodes);

    }
    public static void updateGui(node[] nodes){
        if(nodes != null)
            gui.update(nodes);


    }
}
