import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {
    private int x;
    private int y;
    List<Map<Node, Integer>>  edges = new ArrayList<>();

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public List<Map<Node, Integer>> getEdges() {
        return edges;
    }

    public void setEdges(List<Map<Node, Integer>> edges) {
        this.edges = edges;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
