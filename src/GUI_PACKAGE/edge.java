package GUI_PACKAGE;

public class edge {
    public node dest, source;
    public double weight;
    public edge(node dest, node source, double weight) {
        this.dest = dest;
        this.source = source;
        this.weight = weight;
    }
}
