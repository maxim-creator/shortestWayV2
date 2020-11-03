import java.util.*;

public class Field {
    private Set <Home> homes = new LinkedHashSet<>();
    private List<List<Integer>> map = new ArrayList<>();


    public Field(int sizeX, int sizeY) {
        for (int i = 0; i < sizeX; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < sizeY; j++) {
                temp.add(0);
            }
            map.add(temp);
        }
    }


    public List<List<Integer>> getMap() {
        return map;
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
            if(!isRingOutOfBounds(home)){
                System.out.println("incorrect data");
                i--;
                continue;
            }
            if(home.isEnteredRingsRight(home.getRings())) {
                homes.add(home);
                System.out.println("third and fourth rings will be counted automatically");
            } else {
                System.out.println("incorrect data");
                i--;
                continue;
            }
        }
        for (var home : homes) {
            home.setRings(home.countTwoRings(home.getRings()));
            home.setWalls(home.countBlocks(home.getWalls()));
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

    private boolean isRingOutOfBounds(Home home){
        for (var ring :
                home.getRings()) {
            if (ring.getX() > map.size())
                return true;
            if(ring.getY() > map.get(0).size())
                return true;
        }
        return false;
    }

}
