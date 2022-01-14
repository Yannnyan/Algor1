import java.lang.reflect.Array;
import java.util.ArrayList;

public class union_find {


    // the goal is to make a data structure that can make disjoint ground and supports
    // operations like find and union at optimal running time

    // creates a singleton
    public static Node create(int id){
        Node root = new Node(id);
        return root;
    }

    // returns the complete root of the subSet
    public static Node find(Node current){
        if(current.father == null)
            return current;
        return current.father;
    }

    // does the same as find but points all the nodes in the path to the root
    public static Node find_path_compression(Node node){
        Node father = null;
        Node current = node;
        ArrayList<Node> nodes_in_path = new ArrayList<>();
        while(current.father != null){
            nodes_in_path.add(current);
            current = current.father;
        }
        father = current;
        for (int i = 0; i < nodes_in_path.size(); i++) {
            nodes_in_path.get(i).father = father;
        }
        return father;
    }

    public static void Union(Node node1, Node node2){
        Node current_root = find_path_compression(node1);
        Node next_root = find_path_compression(node2);
        if(current_root.id == next_root.id) return;
        if(current_root.weight > next_root.weight){
            next_root.father = current_root;
        }
        else{
            current_root.father = next_root;
        }
    }





}
