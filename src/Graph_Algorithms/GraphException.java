package Graph_Algorithms;

public class GraphException extends Exception{
    public GraphException(){
        super("Graph Exception has occured!");
        String cl = this.getClass().getName();
        if(cl.equals("DSF")){
            System.out.println("THE GRAPH IS ACYCLIC! <3");
        }
    }

}
