package General_Algorithms;

public class Node {
    Node father;
    public int id;
    public int weight;
    public Node(int id){
        father = null;
        this.id = id;
        weight = 1;

    }
}
