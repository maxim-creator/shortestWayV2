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


    public Way(Scanner scanner, List<List<Integer>> map) {
        System.out.println("enter start position");
        startX = scanner.nextInt();
        startY = scanner.nextInt();
        System.out.println("enter finish position");
        finishX = scanner.nextInt();
        finishY = scanner.nextInt();
        this.map = map;
    }

    public void countDistance(){
        nodes = searchNodes();
        //TODO
    }

    private List<Node> searchNodes(){
        List<Node> nodes = new ArrayList<>();
        Node node = new Node(startX, startY);
        nodes.add(node);
        Node node1 = new Node(finishX, finishY);
        nodes.add(node1);
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
               if (map.get(i).get(j) == 1){
                   Node node2 = searchNode(i, j);
                   if(node2 != null)
                       nodes.add(searchNode(i, j));
               }
            }
        }
        return nodes;
    }
    private Node searchNode(int x, int y){
        boolean leftBlock = isBlockLeft(x,y);
        boolean rightBlock = isBlockRight(x,y);
        boolean upperBlock = isBlockUpper(x,y);
        boolean lowerBlock = isBlockLower(x,y);
        if(leftBlock && upperBlock){
            Node node = new Node(--x, ++y);
            return node;
        }
        if(rightBlock && upperBlock){
            Node node = new Node(++x, ++y);
            return node;
        }
        if(leftBlock && lowerBlock){
            Node node = new Node(--x, --y);
            return node;
        }
        if(rightBlock && lowerBlock){
            Node block = new Node(++x, --y);
            return block;
        }
        return null;
    }

    private boolean isBlockLeft(int x, int y){
        try {
            if(map.get(--x).get(y) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    private boolean isBlockRight(int x, int y){
        try {
            if(map.get(++x).get(y) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    private boolean isBlockUpper(int x, int y){
        try {
            if(map.get(x).get(++y) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isBlockLower(int x, int y){
        try {
            if(map.get(x).get(--y) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
