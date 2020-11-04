import java.util.*;

public class AStarAlgorithm {
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
        nodes.get(0).setHeuristicDistanceToFinish(Way.countDistanceBetweenNodes(nodes.get(0), nodes.get(1)));
        nodes.get(0).countDistanceSum();
        opened.add(nodes.get(0));


        NodeForAStarAlgorithm currentNode = nodes.get(0);
        while (!opened.isEmpty()){
           NodeForAStarAlgorithm bestDist = opened.stream().
                   min(Comparator.comparing(NodeForAStarAlgorithm::getDistanceSum)).
                   orElseThrow(NoSuchElementException::new);
          //bestDist.setDistanceFromStart(way.countDistanceBetweenNodes(currentNode, bestDist)+currentNode.getDistanceFromStart());
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
                   nodes.get(neighborPos).setHeuristicDistanceToFinish(Way.countDistanceBetweenNodes(nodes.get(neighborPos),
                           nodes.get(1)));
                   nodes.get(neighborPos).countDistanceSum();
               }
           }

        }

        return 0;
    }



    private int searchNodePosition(Node node){
        for (int i = 0; i < nodes.size(); i++) {
            Node node1 = new Node(nodes.get(i).getX(), nodes.get(i).getY());
            if(node1.equals(node))
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
