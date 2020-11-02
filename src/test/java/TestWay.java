import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestWay {
    static List<List<Integer>> map = new ArrayList();
    static List<Node> nodes = new ArrayList<>();
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

    @BeforeAll
    public static void generateNodes() {
        nodes.add(new Node(1, 1));
        nodes.add(new Node(9, 9));
        nodes.add(new Node(4, 6));
        nodes.add(new Node(4, 2));
        nodes.add(new Node(8, 6));
        nodes.add(new Node(8, 2));
    }


    @Test
    public void testSearchNode() {
        Way way = new Way(map);
        Node node = new Node(4, 6);
        Node node1 = way.searchNode(5, 5);
        assertEquals(node, node1);

        way = new Way(map);
        node = new Node(8, 6);
        node1 = way.searchNode(7, 5);
        assertEquals(node, node1);

    }

    @Test
    public void testIsHouseBetweenNodes() {
        Way way = new Way(map);
        Node node = new Node(4, 6);
        Node node1 = new Node(8, 2);
        assertTrue(way.isHouseBetweenNodes(node, node1));
        node = new Node(1, 1);
        node1 = new Node(8, 1);
        assertFalse(way.isHouseBetweenNodes(node, node1));


    }

}
