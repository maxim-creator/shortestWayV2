import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Way {
    private List<List<Integer>> map;
    private List<Node> nodes;
    private int startX;
    private int startY;
    private int finishX;
    private int finishY;


    public Way(List<List<Integer>> map) {
        this.map = map;
    }
    public Way(){}

    public void enterStartFinish(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter start position");
        startX = scanner.nextInt();
        startY = scanner.nextInt();
        System.out.println("enter finish position");
        finishX = scanner.nextInt();
        finishY = scanner.nextInt();
    }

    public void countDistance() {
        nodes = searchNodes();
        searchEdges();
        AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(nodes);
        double distance = aStarAlgorithm.countDistance(nodes.get(0), nodes.get(1));
        System.out.println(distance);
    }

    private void searchEdges() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                if (!isHouseBetweenNodes(nodes.get(i), nodes.get(j))) {
                    nodes.get(i).addEdge(nodes.get(j), countDistanceBetweenNodes(nodes.get(i), nodes.get(j)));
                    nodes.get(j).addEdge(nodes.get(i), countDistanceBetweenNodes(nodes.get(i), nodes.get(j)));
                }
            }
        }
    }

    protected boolean isHouseBetweenNodes(Node one, Node two) {
        int xDist = Math.abs(one.getX() - two.getX());
        int yDist = Math.abs(one.getY() - two.getY());
        int xStep;
        int yStep;
        double reminder = 0;
        if (xDist > yDist) {
            if (yDist > 0) {
                yStep = 1;
                xStep = xDist / yDist;
                reminder = xDist % yDist;
            } else {
                yStep = 0;
                xStep = 1;
                reminder = 0;
            }
        } else if (yDist > xDist) {
            if (xDist > 0) {
                xStep = 1;
                yStep = yDist / xDist;
                reminder = yDist % xDist;
            } else {
                yStep = 1;
                xStep = 0;
                reminder = 0;
            }
        } else {
            xStep = 1;
            yStep = 1;
        }

        int xCurrent = one.getX();
        int yCurrent = one.getY();

        double additionalNum = reminder;
        while (xCurrent != two.getX() && yCurrent != two.getY()) {
            if (!isHouseBetweenPoints(xCurrent, xCurrent + xStep, true, yCurrent)) {
                if (one.getX() < two.getX())
                    xCurrent += xStep;
                else xCurrent -= xStep;
            } else return true;

            if (!isHouseBetweenPoints(yCurrent, yCurrent + yStep, false, xCurrent)) {
                if (one.getY() < two.getY())
                    yCurrent += yStep;
                else yCurrent -= yStep;
            } else return true;
        }

        additionalNum += reminder;
        int wholePart = (int) additionalNum;
        if (additionalNum > 1) {
            if (yStep == 1)
                xCurrent += wholePart;

            else
                yCurrent += wholePart;
        }
        additionalNum -= wholePart;

        return false;
    }


    private boolean isHouseBetweenPoints(int one, int two, boolean xDirection, int secondDirection) {
        while (one != two) {
            if (xDirection = true) {
                if (map.get(one).get(secondDirection) == 1)
                    return true;
                else one++;
            } else {
                if (map.get(secondDirection).get(one) == 1)
                    return true;
                else one++;
            }
        }
        return false;
    }

    public double countDistanceBetweenNodes(Node one, Node two) {
        int a = Math.abs(one.getX() - two.getX());
        int b = Math.abs(one.getY() - two.getY());
        double c = Math.sqrt(a * a + b * b);
        return c;

    }

    private List<Node> searchNodes() {
        List<Node> nodes = new ArrayList();
        Node node = new Node(startX, startY);
        nodes.add(node);
        Node node1 = new Node(finishX, finishY);
        nodes.add(node1);
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == 1) {
                    Node node2 = searchNode(i, j);
                    if (node2 != null)
                        nodes.add(searchNode(i, j));
                }
            }
        }
        return nodes;
    }

    public Node searchNode(int x, int y) {
        boolean leftBlock = isBlockLeft(x, y);
        boolean rightBlock = isBlockRight(x, y);
        boolean upperBlock = isBlockUpper(x, y);
        boolean lowerBlock = isBlockLower(x, y);
        if (leftBlock && upperBlock) {
            Node node = new Node(--x, ++y);
            return node;
        }
        if (rightBlock && upperBlock) {
            Node node = new Node(++x, ++y);
            return node;
        }
        if (leftBlock && lowerBlock) {
            Node node = new Node(--x, --y);
            return node;
        }
        if (rightBlock && lowerBlock) {
            Node node = new Node(++x, --y);
            return node;
        }
        return null;
    }

    private boolean isBlockLeft(int x, int y) {
        try {
            if (map.get(--x).get(y) == 0)
                return true;
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isBlockRight(int x, int y) {
        try {
            if (map.get(++x).get(y) == 0)
                return true;
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isBlockUpper(int x, int y) {
        try {
            if (map.get(x).get(++y) == 0)
                return true;
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isBlockLower(int x, int y) {
        try {
            if (map.get(x).get(--y) == 0)
                return true;
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
