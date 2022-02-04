package General_Algorithms;

import GUI_PACKAGE.Controller;
import GUI_PACKAGE.edge;
import GUI_PACKAGE.node;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;


public class Minimal_Spanning_Tree {



    public static void main(String[] args){



    }

    public static int comparator1(edge edge1, edge edge2){
        if(edge1.weight < edge2.weight){
            return -1;
        }
        else if (edge1.weight == edge2.weight){
            return 0;
        }
        else
            return 1;
    }
    private static void sortByComparator1(ArrayList<edge> edges){
        edge min ;
        edge  temp;
        int index;
        for (int i = 0; i < edges.size(); i++) {
            index = i;
            min = edges.get(i);
            for (int j = i; j < edges.size()-1; j++) {
                if(comparator1(edges.get(j),min) < 1){
                    min = edges.get(j);
                    index = j;
                }
            }
            temp = edges.get(i);
            edges.set(i,min);
            edges.set(index,temp);
        }
    }
    public static ArrayList<edge> Kruskal_Algorithm(HashMap<Integer, node> nodesMap, ArrayList<edge> edges){
        sortByComparator1(edges);
        HashMap<Integer,Node> connected_components = new HashMap<>();
        ArrayList<edge> MST = new ArrayList<>();
        for (int i = 0; i < nodesMap.size(); i++) {
            connected_components.put(i,new Node(i));
        }
        int src,dest;
        int x ;
        for (int i = 0; i < edges.size(); i++) {
            src = edges.get(i).source.id; dest = edges.get(i).dest.id;
            if(union_find.find(connected_components.get(src)).id != union_find.find(connected_components.get(dest)).id){
                MST.add(edges.get(i));
                union_find.Union_NoCompression(connected_components.get(src),connected_components.get(dest));
            }
        }
        return MST;
    }
}
