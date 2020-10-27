import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AStarAlgorithm {
    private List<NodeForAStarAlgorithm> nodes = new ArrayList();

    private Set<NodeForAStarAlgorithm> closed = new LinkedHashSet();
    private Set<NodeForAStarAlgorithm> opened = new LinkedHashSet();
    private Set<NodeForAStarAlgorithm> passed = new LinkedHashSet();

    public AStarAlgorithm(List nodes) {
        this.nodes = nodes;
    }


    public double countDistance(Node start, Node finish){
        Way way = new Way();
        NodeForAStarAlgorithm startNode = (NodeForAStarAlgorithm) start;
        startNode.setHeuristicDistanceToFinish(way.countDistanceBetweenNodes(start, finish));
        startNode.setDistanceFromStart(0);
        startNode.countDistanceSum();
        opened.add(startNode);

        NodeForAStarAlgorithm currentNode = startNode;
        while (!opened.isEmpty()){
            NodeForAStarAlgorithm nodeForAStarAlgorithm = searchNextNode(currentNode);
            //TODO
        }

        return 0;
    }


    private NodeForAStarAlgorithm searchNextNode(NodeForAStarAlgorithm node){
         List edges = (List) node.getEdges().keySet();
         List distance = (List) node.getEdges().values();
         for (int i = 0; i < edges.size(); i++){

         }
         //TODO
        return null;
    }

}
