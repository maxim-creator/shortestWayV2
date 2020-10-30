import java.util.*;

public class AStarAlgorithm {
    Way way = new Way();
    private List<NodeForAStarAlgorithm> nodes = new ArrayList();

    private Set<NodeForAStarAlgorithm> closed = new LinkedHashSet();
    private Set<NodeForAStarAlgorithm> opened = new LinkedHashSet();
    private Set<NodeForAStarAlgorithm> passed = new LinkedHashSet();

    public AStarAlgorithm(List<Node> nodes) {
        for (Node node :
                nodes) {
            NodeForAStarAlgorithm nodeForAStarAlgorithm = new NodeForAStarAlgorithm(node.getX(), node.getY());
            nodeForAStarAlgorithm.setEdges(node.getEdges());
            this.nodes.add(nodeForAStarAlgorithm);
        }


    }


    public double countDistance(Node start, Node finish) {

        NodeForAStarAlgorithm startNode = new NodeForAStarAlgorithm(start.getX(), start.getY());
        startNode.setEdges(startNode.getEdges());

        startNode.setHeuristicDistanceToFinish(way.countDistanceBetweenNodes(start, finish));
        startNode.setDistanceFromStart(0);
        startNode.countDistanceSum();
        startNode.setEdges(nodes.get(0).getEdges());
        opened.add(startNode);

        NodeForAStarAlgorithm currentNode = startNode;
        while (!opened.isEmpty()) {
            NodeForAStarAlgorithm nextNode = searchNextNode(currentNode);
            if (nodes.get(1).equals(nextNode))
                return nextNode.getDistanceFromStart();
            nextNode.setEdges(searchEdges(nextNode));
            opened.remove(nextNode);
            closed.add(nextNode);

            for (Node neighbor :
                    nextNode.getEdges().keySet()) {
                boolean tentativeIsBetter;
                NodeForAStarAlgorithm node = (NodeForAStarAlgorithm) neighbor;
                if (closed.contains(node))
                    continue;
                double distanceFromStart = nextNode.getDistanceFromStart()
                        + way.countDistanceBetweenNodes(nextNode, neighbor);
                if (!opened.contains(node)) {
                    opened.add(node);
                    tentativeIsBetter = true;
                } else {
                    if (node.getDistanceFromStart() + way.countDistanceBetweenNodes(node, nextNode) < node.getDistanceFromStart())
                        tentativeIsBetter = true;
                    else
                        tentativeIsBetter = false;
                }

                if (tentativeIsBetter = true) {
                    node.setDistanceFromStart(nextNode.getDistanceFromStart() + way.countDistanceBetweenNodes(node, nextNode));
                    node.setHeuristicDistanceToFinish(way.countDistanceBetweenNodes(node, finish));
                    node.countDistanceSum();
                    for (int i = 0; i < nodes.size(); i++) {
                        if (nodes.get(i).equals(node))
                            nodes.set(i, node);
                    }
                }

            }


        }

        return 0;
    }


    private NodeForAStarAlgorithm searchNextNode(NodeForAStarAlgorithm node) {
        NodeForAStarAlgorithm nextNode = null;
        double minDist = 999999999;
        List<Node> edges =  new ArrayList<>(node.getEdges().keySet());
        List<Integer> distance = new ArrayList<>(node.getEdges().values());
        for (int i = 0; i < edges.size(); i++) {
            double dist = way.countDistanceBetweenNodes(edges.get(i).getX(), edges.get(i).getY(),
                    node.getX(), node.getY());
            dist += way.countDistanceBetweenNodes(edges.get(i).getX(), edges.get(i).getY(),
                    nodes.get(1).getX(), nodes.get(i).getY());
            if (dist < minDist) {
                minDist = dist;
                nextNode = new NodeForAStarAlgorithm(edges.get(i).getX(), edges.get(i).getY());
            }

        }
        nextNode.setDistanceFromStart(node.getDistanceFromStart() + way.countDistanceBetweenNodes(node, nextNode));

        return nextNode;
    }

    private Map searchEdges(NodeForAStarAlgorithm node) {
        for (NodeForAStarAlgorithm i :
                nodes) {
            if (i.equals(node))
                return node.getEdges();
        }
        return null;
    }

}
