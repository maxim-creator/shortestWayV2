import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Way {
    private List<List<Integer>> map;
    List<Block> nodes;
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

    private List<Block> searchNodes(){
        List<Block> nodes = new ArrayList<>();
        Block block = new Block(startX, startY);
        nodes.add(block);
        Block block1 = new Block(finishX, finishY);
        nodes.add(block1);
        for (var i :
                map) {
            for (int j = 0; j < i.size(); j++) {
               if (i.get(j) == 1){
                   Block block2 = searchNode(map.indexOf(i),j);
                   if(block2 != null)
                       nodes.add(searchNode(map.indexOf(i), j));
               }
            }
        }
        return nodes;
    }
    private Block searchNode(int x, int y){
        boolean leftBlock = isBlockLeft(x,y);
        boolean rightBlock = isBlockRight(x,y);
        boolean upperBlock = isBlockUpper(x,y);
        boolean lowerBlock = isBlockLower(x,y);
        if(leftBlock && upperBlock){
            Block block = new Block(x--, y++);
            return block;
        }
        if(rightBlock && upperBlock){
            Block block = new Block(x++, y++);
            return block;
        }
        if(leftBlock && lowerBlock){
            Block block = new Block(x--, y--);
            return block;
        }
        if(rightBlock && lowerBlock){
            Block block = new Block(x++, y--);
            return block;
        }
        return null;
    }

    private boolean isBlockLeft(int x, int y){
        try {
            if(map.get(x--).get(y) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    private boolean isBlockRight(int x, int y){
        try {
            if(map.get(x++).get(y) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    private boolean isBlockUpper(int x, int y){
        try {
            if(map.get(x).get(y++) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isBlockLower(int x, int y){
        try {
            if(map.get(x).get(y--) == 0)
                return true;
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
