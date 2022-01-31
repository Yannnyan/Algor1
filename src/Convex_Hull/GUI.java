package Convex_Hull;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args){
        //Controller.main(null);

    }
    Panel panel;
    JFrame frame;
    public void createAndShowGUI(node[] nodes, edge[] edges){
        frame = new JFrame();
        frame.setSize(800,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        this.panel = new Panel(nodes,edges,frame.getWidth(),frame.getHeight());
        panel.setLocation(0,0);
        panel.setSize(frame.getWidth(),frame.getHeight());
        frame.add(panel);
        frame.setVisible(true);
    }
    public void update(node[] nodes) {
        //panel.nodes = nodes;
        edge[] edges = new edge[nodes.length-1];
        if (nodes.length == 1 || nodes.length == 0)
            edges = null;
        else
            for (int i = 0; i < nodes.length - 1; i++) {
                if(nodes[i+1] == null) break;
                edge ed = new edge( nodes[i], nodes[i+1]);
                edges[i] = ed;
            }

        panel.edges = edges;
        panel.repaint();
    }
}

class edge{
    public node dest,source;
    public edge(node dest, node source){
        this.dest = dest;
        this.source = source;
    }
}
class Panel extends JPanel {
    public node[] nodes;
    public edge[] edges;
    private int widthCanvas,heightCanvas;
    private final int widthOval = 20, heightOval = 20;
    private final int marginX = 100, marginY = 55;
    private double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
    private double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;


    Panel(node[] nodes,edge[] edges, int widthCanvas, int heightCanvas){

        this.nodes = nodes;
        this.edges = edges;
        this.widthCanvas = widthCanvas - 2*marginX;
        this.heightCanvas = heightCanvas - 3*marginY;
        setMin();
        this.repaint();

    }

    private void setMin(){
        for (node nod:this.nodes) {
            this.minX = minX > nod.x ? nod.x : minX;
            this.minY = minY > nod.y ? nod.y : minY;
            this.maxX = maxX < nod.x ? nod.x : maxX;
            this.maxY = maxY < nod.y ? nod.y : maxY;
        }
    }
    private int getNodeXCanvas(node nod){
        double lenX = Math.abs(this.maxX - this.minX);
        double portionX = Math.abs(this.maxX - nod.x);
        if(portionX == 0.0)
            return this.widthCanvas -marginX;
        if(portionX / lenX == 1.0)
            return marginX;
        return (int)((portionX/lenX) * this.widthCanvas) + marginX;
    }
    private int getNodeYCanvas(node nod){
        double lenY = this.maxY - this.minY;
        double portionY = this.maxY - nod.y;
        if(portionY == 0)
            return this.heightCanvas - marginY;
        if(portionY == 1.0)
            return marginY;
        return (int)((portionY/lenY) * this.heightCanvas) + marginY;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.orange);
        for (node nod: nodes) {
            if(nod == null) continue;
            System.out.println("X : " + getNodeXCanvas(nod) + " Y : " + getNodeYCanvas(nod));
            g.fillOval(getNodeXCanvas(nod),getNodeYCanvas(nod),widthOval,heightOval);
            //g.fillOval(100,200,widthOval,heightOval);
        }
        if(edges != null)
        for(edge ed : edges){
            if(ed == null) continue;
            g.drawLine(getNodeXCanvas(ed.source ) + widthOval/2,getNodeYCanvas(ed.source)+heightOval/2,
                    getNodeXCanvas(ed.dest) + widthOval/2 , getNodeYCanvas(ed.dest) + heightOval /2);
        }
        this.requestFocus();
    }
}
