import java.util.*;

public class AStarAlgorithm {
    Way way = new Way();
    private List<NodeForAStarAlgorithm> nodes = new ArrayList<>();
    private Set<NodeForAStarAlgorithm> closed = new LinkedHashSet();
    private List<NodeForAStarAlgorithm> opened = new ArrayList<>();
    private Set<NodeForAStarAlgorithm> passed = new LinkedHashSet();

    public AStarAlgorithm(List<Node> nodes) {
        for (Node node :
                nodes) {
            NodeForAStarAlgorithm nodeForAStarAlgorithm = new NodeForAStarAlgorithm(node.getX(), node.getY());
            nodeForAStarAlgorithm.setEdges(node.getEdges());
            this.nodes.add(nodeForAStarAlgorithm);
        }

    }

    public double countDistance(){
        nodes.get(0).setDistanceFromStart(0);
        nodes.get(0).setHeuristicDistanceToFinish(way.countDistanceBetweenNodes(nodes.get(0), nodes.get(1)));
        nodes.get(0).countDistanceSum();
        opened.add(nodes.get(0));


        NodeForAStarAlgorithm currentNode = nodes.get(0);
        while (!opened.isEmpty()){
           NodeForAStarAlgorithm bestDist = opened.stream().
                   min(Comparator.comparing(NodeForAStarAlgorithm::getDistanceSum)).
                   orElseThrow(NoSuchElementException::new);

           if(bestDist.equals(nodes.get(1)))
               return bestDist.getDistanceFromStart();
           opened.remove(bestDist);
           closed.add(bestDist);

           currentNode = setCurrentNode(bestDist);
            Boolean bestNeighbor;
           for(Map.Entry<Node, Double> neighbor : currentNode.getEdges().entrySet()){
               if(closed.contains(neighbor))
                   continue;

               double gDistance = currentNode.getDistanceFromStart() + neighbor.getValue();
               int neighborPos = searchNodePosition(neighbor.getKey());
               if(!opened.contains(neighbor)) {
                   NodeForAStarAlgorithm nodeForAStarAlgorithm =
                           new NodeForAStarAlgorithm(neighbor.getKey().getX(), neighbor.getKey().getY());
                   opened.add(setCurrentNode(nodeForAStarAlgorithm));
                   bestNeighbor = true;
               }
               else if(gDistance < nodes.get(neighborPos).getDistanceFromStart())
                   bestNeighbor = true;
               else
                   bestNeighbor = false;


               if(bestNeighbor){
                   nodes.get(neighborPos).setDistanceFromStart(gDistance);
                   nodes.get(neighborPos).setHeuristicDistanceToFinish(way.countDistanceBetweenNodes(nodes.get(neighborPos),
                           nodes.get(1)));
                   nodes.get(neighborPos).countDistanceSum();
               }
           }

        }

        return 0;
    }

    private int searchNode(Node node){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(node))
                return i;
        }
        return 0;
    }

    private int searchNodePosition(Node node){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(node))
                return i;
        }
        return 0;
    }

    private NodeForAStarAlgorithm setCurrentNode(NodeForAStarAlgorithm node){
        for (var i :
                nodes) {
            if(node.equals(i))
                return i;
        }

        return node;
    }





}
