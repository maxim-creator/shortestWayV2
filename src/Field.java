import java.util.*;

public class Field {
    Set <Home> homes = new LinkedHashSet<>();
    List<List<Integer>> map = new ArrayList<>();

    public Field(int sizeX, int sizeY) {
        for (int i = 0; i < sizeY; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < sizeX; j++) {
                temp.add(0);
            }
            map.add(temp);
        }
    }

    public void addHomes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of house");
        int homesQuantity = scanner.nextInt();
        for (int i = 0; i < homesQuantity; i++) {
            Home home = new Home();
            System.out.println(i+1 + " house:");
            System.out.println("enter upper left ring");
            home.addRing(scanner);
            System.out.println("enter lover right ring");
            home.addRing(scanner);
            if(home.isEnteredRingsRight()) {
                homes.add(home);
                System.out.println("third and fourth rings will be counted automatically");
            } else {
                System.out.println("incorrect data");
                i--;
            }
        }
        for (var home : homes) {
            home.countTwoRings();
            home.countBlocks();
            addHomeToMap(home);
        }
    }

    private void addHomeToMap(Home home){
        for (int i = 0; i < home.getWalls().size(); i++) {
            int line = home.getWalls().get(i).get(0).getX();
            List<Integer> temp = map.get(line);
            for(int j = 0; j < home.getWalls().get(i).size(); j++){
                temp.set(home.getWalls().get(i).get(j).getY(), 1);
            }
            map.set(line, temp);
        }
    }

}
