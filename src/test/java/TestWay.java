import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestWay {
    static List map = new ArrayList();
    static List nodes;
    int startX = 1;
    int startY = 1;
    int finishX = 9;
    int finishY = 9;

    @BeforeAll
    public static void generateMap() {
        for (int i = 0; i < 12; i++) {
            List temp = new ArrayList();
            for (int j = 0; j < 12; j++) {
                if (i >= 5 && i <= 7) {
                    if (j >= 3 && j <= 5)
                        temp.add(1);
                    else
                        temp.add(0);
                } else
                    temp.add(0);
            }
                map.add(temp);
        }
    }

    @Test
    public void testSearchNode(){
        Way way = new Way(map);
        Node node = new Node(4,6);
        Node node1 = way.searchNode(5,5);
        assertEquals(node.getX(), node1.getX());
        assertEquals(node.getY(), node1.getY());

        way = new Way(map);
        node = new Node(8,6);
        node1 = way.searchNode(7,5);
        assertEquals(node.getX(), node1.getX());
        assertEquals(node.getY(), node1.getY());
    }


}
