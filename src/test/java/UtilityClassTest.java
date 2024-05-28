import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.example.lab12.ex1.MyUtilityClass;
import org.junit.jupiter.api.Test;

public class UtilityClassTest {
    @Test
    void testPrintCollection_NullCollection() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    MyUtilityClass.printCollection(null);
                });
    }

    @Test
    void testPrintCollection_EmptyCollection() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MyUtilityClass.printCollection(new ArrayList<>());
        assertEquals("[]", outContent.toString().trim());
    }

    @Test
    void testPrintCollection_StringCollection() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MyUtilityClass.printCollection(List.of("ana", "are", "mere"));
        assertEquals("[ana, are, mere]", outContent.toString().trim());
    }

    @Test
    void testAggregate_NullCollection() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    MyUtilityClass.aggregate(null, 0, (acc, v) -> acc);
                });
    }

    @Test
    void testAggregate_NullLambda() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    MyUtilityClass.aggregate(List.of(1, 2, 3), 0, null);
                });
    }

    @Test
    void testAggregate_EmptyCollection() {
        int result = MyUtilityClass.aggregate(new ArrayList<>(), 0, (acc, v) -> acc);
        assertEquals(0, result);
    }

    @Test
    void testAggregate_countTrue() {
        List<Boolean> booleans = List.of(true, false, true, false, true);
        int result = MyUtilityClass.aggregate(booleans, 0, (acc, v) -> v ? acc + 1 : acc);
        assertEquals(3, result);
    }

    @Test
    void testAggregate_and() {
        List<Boolean> booleans = List.of(true, false, true, false, true);
        boolean result = MyUtilityClass.aggregate(booleans, true, (acc, v) -> acc & v);
        assertFalse(result);
    }

    @Test
    void testAggregate_Sum() {
        int result = MyUtilityClass.aggregate(List.of(1, 2, 3), 0, Integer::sum);
        assertEquals(6, result);
    }

    @Test
    void testDuplicate_NullCollection() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    MyUtilityClass.duplicateCollection(null);
                });
    }

    @Test
    void testDuplicate_EmptyCollection() {
        List<Integer> col = new ArrayList<>();
        MyUtilityClass.duplicateCollection(col);
        assertEquals(List.of(), col);
    }

    @Test
    void testDuplicate_StringCollection() {
        List<String> col = new ArrayList<>(List.of("ana", "are", "mere"));
        MyUtilityClass.duplicateCollection(col);
        assertEquals(List.of("ana", "ana", "are", "are", "mere", "mere"), col);
    }

    record Person(String name) {}
    ;

    @Test
    void testDuplicate_PersonCollection() {
        List<Person> persons = new ArrayList<>(List.of(new Person("Aurel"), new Person("Vali")));
        MyUtilityClass.duplicateCollection(persons);
        assertEquals(4, persons.size());
        assertEquals("Aurel", persons.get(0).name());
        assertEquals("Aurel", persons.get(1).name());
        assertEquals("Vali", persons.get(2).name());
        assertEquals("Vali", persons.get(3).name());
    }
}
