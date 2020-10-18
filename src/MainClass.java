import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("enter map size");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Field field = new Field(x,y);
        field.addHomes();
        Way way = new Way(scanner, field.getMap());
        way.countDistance();

    }
}



