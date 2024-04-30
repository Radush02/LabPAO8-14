import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.SortedSet;
import org.example.lab9.task1.SortedListSet;
import org.junit.jupiter.api.Test;

public class SortedListSetTest {

    @Test
    public void testAdd() {
        SortedListSet<Integer> set = new SortedListSet<>();
        assertTrue(set.isEmpty());
        assertTrue(set.add(10));
        assertTrue(set.add(20));
        assertFalse(set.add(10));
        assertEquals(2, set.size());
    }

    @Test
    public void testFirstAndLast() {
        SortedListSet<String> set = new SortedListSet<>();
        set.addAll(Arrays.asList("PAO", "Laborator", "abc123"));
        assertEquals("PAO", set.first());
        assertEquals("abc123", set.last());
    }

    @Test
    public void testSubSet() {
        SortedListSet<Integer> set = new SortedListSet<>();
        set.addAll(Arrays.asList(10, 20, 30, 40, 50));
        SortedSet<Integer> subSet = set.subSet(20, 40);
        assertEquals(2, subSet.size());
        assertTrue(subSet.contains(20));
        assertTrue(subSet.contains(30));
        assertFalse(subSet.contains(100000));
    }

    @Test
    public void testHeadSetAndTailSet() {
        SortedListSet<Integer> set = new SortedListSet<>();
        set.addAll(Arrays.asList(1, 2, 3, 4, 5));
        SortedSet<Integer> headSet = set.headSet(4);
        SortedSet<Integer> tailSet = set.tailSet(3);
        assertEquals(3, headSet.size());
        assertEquals(3, tailSet.size());
        assertFalse(headSet.contains(4));
        assertTrue(tailSet.contains(4));
    }
}
