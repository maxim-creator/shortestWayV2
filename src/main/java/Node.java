import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Node{
    private int x;
    private int y;
    private Map<Node, Double> edges = new LinkedHashMap();

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Map<Node, Double> getEdges() {
        return edges;
    }

    public void setEdges(Map edges) {
        this.edges = edges;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void addEdge(Node node, double distance){
        edges.put(node, distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }




}
