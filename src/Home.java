import java.util.*;

public class Home {

    //1-upper left 2-upper right 3-lower left 4-lower right
    private List<Ring> rings = new ArrayList<>();
    private List<List<Block>> walls = new ArrayList<>();
    public List<Ring> getRings() {
        return rings;
    }

    public List<List<Block>> getWalls() {
        return walls;
    }

    public void addRing(Scanner scanner){
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Ring ring = new Ring(x,y);
        rings.add(ring);
    }


    protected void countTwoRings(){
           int x = rings.get(0).getX();
           int y = rings.get(1).getY();
           int x2 = rings.get(1).getX();
           int y2 = rings.get(0).getY();
           Ring ring = new Ring(x,y);
           Ring ring1 = new Ring(x2, y2);
           rings.add(1,ring);
           rings.add(2,ring1);
    }

    protected boolean isEnteredRingsRight(){
        if(rings.get(0).getX() >= rings.get(1).getX())
            return false;
        if(rings.get(0).getY() <= rings.get(1).getY())
            return false;
        return true;
    }

   protected void countBlocks(){
        for (int j = rings.get(0).getX(); j <= rings.get(2).getX(); j++) {
            List<Block> temp = new ArrayList<>();
            for (int i = rings.get(0).getY(); i >= rings.get(3).getY(); i--) {
              temp.add(new Block(j,i));
            }
            walls.add(temp);
       }
    }
}
