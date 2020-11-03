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

    public Way() {
    }

    public void enterStartFinish() {
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
        double distance = aStarAlgorithm.countDistance();
        System.out.println(distance);
    }

    private void searchEdges() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (j != i)
                    if (!isHouseBetweenNodes(nodes.get(i), nodes.get(j))) {
                        nodes.get(i).addEdge(nodes.get(j), countDistanceBetweenNodes(nodes.get(i), nodes.get(j)));
                       //nodes.get(j).addEdge(nodes.get(i), countDistanceBetweenNodes(nodes.get(i), nodes.get(j)));
                    }
            }
        }
    }

    protected boolean isHouseBetweenNodes(Node one, Node two) {
        int xDist = two.getX() - one.getX();
        int yDist = two.getY() - one.getY();
        int xStep = 0;
        int yStep = 0;
        double xReminder = 0;
        double yReminder = 0;

        if (xDist == 0) {
            return isHouseBetweenPoints(one.getY(), two.getY(), false, one.getX());
        }
        if (yDist == 0) {
            return isHouseBetweenPoints(one.getX(), two.getX(), true, one.getY());
        }
        boolean xMoreThanY = Math.abs(xDist) > Math.abs(yDist);
        boolean yMoreThanX = Math.abs(xDist) < Math.abs(yDist);
        boolean xEqualsT = Math.abs(xDist) == Math.abs(yDist);

        if (xDist > 0 && yDist > 0) {
            if (xDist > yDist) {
                yStep = 1;
                xStep = xDist / yDist;
                xReminder = xDist % yDist;
            }
            if (yDist > xDist) {
                yStep = yDist / xDist;
                xStep = 1;
                yReminder = yDist % xDist;
            }
            if (xEqualsT) {
                xStep = 1;
                yStep = 1;
            }
        } else if (xDist < 0 && yDist < 0) {
            if (xDist < yDist) {
                yStep = -1;
                xStep = -xDist / yDist;
                xReminder = -Math.abs(xDist % yDist);
            }
            if (yDist < xDist) {
                yStep = -yDist / xDist;
                xStep = -1;
                yReminder = -Math.abs(yDist % xDist);
            }
            if (xEqualsT) {
                xStep = -1;
                yStep = -1;
            }
        } else if (xDist > 0 && yDist < 0) {
            if (xMoreThanY) {
                yStep = -1;
                xStep = xDist / yDist;
                xReminder = xDist % yDist;
            }
            if (yMoreThanX) {
                yStep = yDist / xDist;
                xStep = 1;
                yReminder = -Math.abs(yDist % xDist);
            }
            if(xEqualsT){
                xStep = 1;
                yStep = -1;
            }
        } else if (xDist < 0 && yDist > 0) {
            if (xMoreThanY) {
                yStep = 1;
                xStep = xDist / yDist;
                xReminder = -Math.abs(xDist % yDist);
            }
            if (yMoreThanX) {
                yStep = yDist / xDist;
                xStep = -1;
                yReminder = yDist % xDist;
            }
            if(xEqualsT){
                xStep = -1;
                yStep = 1;
            }
        }

        int xCurrent = one.getX();
        int yCurrent = one.getY();
        double xAdditional = 0;
        double yAdditional = 0;

        //check starting with x direction
        while (xCurrent != two.getX() && yCurrent != two.getY()) {
            if (isHouseBetweenPoints(xCurrent, xCurrent + xStep, true, yCurrent))
                return true;
            xCurrent += xStep;
            if (isHouseBetweenPoints(yCurrent, yCurrent + yStep, false, xCurrent))
                return true;
            yCurrent += yStep;

            if (xAdditional >= 1) {
                xCurrent += xAdditional;
                xAdditional -= 1;
            } else if (xAdditional <= -1) {
                xCurrent += xAdditional;
                xAdditional += 1;
            }
            if (yAdditional >= 1) {
                yCurrent += yAdditional;
                yAdditional -= 1;
            } else if (yAdditional <= -1) {
                yCurrent += yAdditional;
                yAdditional += 1;
            }

            xAdditional += xReminder;
            yAdditional += yReminder;
        }

        //check starting with y direction
        xCurrent = one.getX();
        yCurrent = one.getY();
        xAdditional = 0;
        yAdditional = 0;
        while (xCurrent != two.getX() && yCurrent != two.getY()) {
            if (isHouseBetweenPoints(yCurrent, yCurrent + yStep, true, xCurrent))
                return true;
            yCurrent += yStep;
            if (isHouseBetweenPoints(xCurrent, xCurrent + xStep, true, yCurrent))
                return true;
            xCurrent += xStep;

            if (yAdditional >= 1) {
                yCurrent += yAdditional;
                yAdditional -= 1;
            } else if (yAdditional <= -1) {
                yCurrent += yAdditional;
                yAdditional += 1;
            }
            if (xAdditional >= 1) {
                xCurrent += xAdditional;
                xAdditional -= 1;
            } else if (xAdditional <= -1) {
                xCurrent += xAdditional;
                xAdditional += 1;
            }

            xAdditional += xReminder;
            yAdditional += yReminder;
        }

        return false;
    }


    private boolean isHouseBetweenPoints(int one, int two, boolean xDirection, int secondDirection) {
        while (one != two) {
            if (xDirection = true) {
                if (map.get(one).get(secondDirection) == 1)
                    return true;
                else {
                    if (one < two)
                        one++;
                    else
                        one--;
                }
            } else {
                if (map.get(secondDirection).get(one) == 1)
                    return true;
                else {
                    if (one < two)
                        one++;
                    else
                        one--;
                }
            }
        }
        return false;
    }

    public double countDistanceBetweenNodes(Node one, Node two) {
        int a = Math.abs(one.getX() - two.getX());
        int b = Math.abs(one.getY() - two.getY());
        return Math.sqrt(a * a + b * b);

    }

    public double countDistanceBetweenNodes(int x1, int y1, int x2, int y2) {
        int a = Math.abs(x1 - x2);
        int b = Math.abs(y1 - y2);
        return Math.sqrt(a * a + b * b);
    }

    public List<Node> searchNodes() {
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
