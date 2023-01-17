import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;

import org.junit.Test;

public class GradingBlockIntListTest {
    
    @Test
    public void testAddGet() {
        assertAttributesUnchanged();

        BlockIntList list = new BlockIntList();

        list.add(0);
        assertEquals(0, list.get(0));
        list.add(1);
        assertEquals(1, list.get(1));
        list.add(2);
        assertEquals(2, list.get(2));

        for (int i = 3; i < 30; i++) {
            list.add(i);
            for (int j = 0; j <= i; j++) {
                assertEquals(j, list.get(j));
            }
        }

        list.add(0);
        assertEquals(0, list.get(30));
    }

    @Test
    public void testAddGet2() {
        assertAttributesUnchanged();

        BlockIntList list = new BlockIntList();

        list.add(100);
        assertEquals(100, list.get(0));
        list.add(200);
        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        list.add(300);
        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        assertEquals(300, list.get(2));
        list.add(301);
        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        assertEquals(300, list.get(2));
        assertEquals(301, list.get(3));
        list.add(302);
        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        assertEquals(300, list.get(2));
        assertEquals(301, list.get(3));
        assertEquals(302, list.get(4));
    }

    @Test
    public void testGetIndexOutOfBounds() {
        BlockIntList list = new BlockIntList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });

        list.add(42);
        assertEquals(42, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1000000000);
        });
    }

    @Test
    public void testAddFirst() {
        assertAttributesUnchanged();

        BlockIntList list = new BlockIntList();

        list.addFirst(0);
        assertEquals("[0]", list.toString());
        list.addFirst(1);
        assertEquals("[1, 0]", list.toString());
        list.addFirst(2);
        assertEquals("[2, 1, 0]", list.toString());
        list.addFirst(3);
        assertEquals("[3, 2, 1, 0]", list.toString());

        for (int i = 4; i < 30; i++) {
            list.addFirst(i);
        }
        String expected = range(0, 30).map(i -> 29 - i)
                .mapToObj(String::valueOf).collect(joining(", ", "[", "]"));
        assertEquals(expected, list.toString());
    }

    @Test
    public void testAddFirst2() {
        assertAttributesUnchanged();

        BlockIntList list = new BlockIntList();

        list.addFirst(10);
        assertEquals("[10]", list.toString());
        list.addFirst(11);
        assertEquals("[11, 10]", list.toString());
        list.addFirst(12);
        assertEquals("[12, 11, 10]", list.toString());
        list.addFirst(13);
        assertEquals("[13, 12, 11, 10]", list.toString());
        list.addFirst(14);
        assertEquals("[14, 13, 12, 11, 10]", list.toString());
        list.addFirst(10000);
        assertEquals("[10000, 14, 13, 12, 11, 10]", list.toString());
        list.addFirst(1000000000);
        assertEquals("[1000000000, 10000, 14, 13, 12, 11, 10]", list.toString());
    }

    @Test
    public void testAddFirstAdd() {
        assertAttributesUnchanged();

        BlockIntList list = new BlockIntList();

        list.add(0);
        assertEquals("[0]", list.toString());
        list.addFirst(1);
        assertEquals("[1, 0]", list.toString());
        list.add(2);
        assertEquals("[1, 0, 2]", list.toString());
        list.addFirst(3);
        assertEquals("[3, 1, 0, 2]", list.toString());
        list.add(4);
        assertEquals("[3, 1, 0, 2, 4]", list.toString());
        list.addFirst(5);
        assertEquals("[5, 3, 1, 0, 2, 4]", list.toString());
        list.add(6);
        assertEquals("[5, 3, 1, 0, 2, 4, 6]", list.toString());
    }

    @Test
    public void testAddFirstSize() {
        assertAttributesUnchanged();

        BlockIntList list = new BlockIntList();

        list.addFirst(0);
        assertEquals(1, list.size());
        list.addFirst(0);
        assertEquals(2, list.size());
        list.addFirst(0);
        assertEquals(3, list.size());

        for (int i = 1; i <= 27; i++) {
            list.addFirst(0);
        }
        assertEquals(30, list.size());

        list = new BlockIntList();
        for (int i = 0; i < 30; i++) {
            list.addFirst(0);
            list.add(0);
        }
        assertEquals(60, list.size());
    }

    private static void assertAttributesUnchanged() {
        var fields = stream(BlockIntList.class.getDeclaredFields())
                .filter(f -> !isStatic(f.getModifiers()))
                .collect(toMap(Field::getName, Field::getType));
        assertEquals(4, fields.size());
        assertEquals(int[][].class, fields.get("blocks"));
        assertEquals(int.class, fields.get("lastBlock"));
        assertEquals(int.class, fields.get("elemsInLast"));
        assertEquals(int.class, fields.get("size"));
    }
}
