import java.util.List;
import java.util.Scanner;

public class Way {
    private List<List<Integer>> map;
    private int startX;
    private int startY;
    private int finishX;
    private int finishY;


    public Way(Scanner scanner) {
        System.out.println("enter start position");
        startX = scanner.nextInt();
        startY = scanner.nextInt();
        System.out.println("enter finish position");
        finishX = scanner.nextInt();
        finishY = scanner.nextInt();
    }

    public void countDistance(List<List<Integer>> map){
        //TODO
    }

}
