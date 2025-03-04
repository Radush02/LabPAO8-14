import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class Task5Tests extends TaskTestsBase {
    @Test
    void testCreate() {
        SimplePOJO expected = new SimplePOJO(1, "asd");

        customContainer.addInstance(expected.getNumber());
        customContainer.addInstance(expected.getString());

        customContainer.addFactoryMethod(
                SimplePOJO.class,
                (container) -> {
                    int number = container.getInstance(Integer.class);
                    String string = container.getInstance(String.class);
                    return new SimplePOJO(number, string);
                });

        SimplePOJO actual = customContainer.create(SimplePOJO.class);
        assertEquals(expected, actual);
    }

    @Test
    void testCreateWithParameters() {
        SimplePOJO expected = new SimplePOJO(1, "asd");

        customContainer.addFactoryMethod(
                SimplePOJO.class,
                (container) -> {
                    int number = container.getInstance(Integer.class);
                    String string = container.getInstance(String.class);
                    return new SimplePOJO(number, string);
                });

        SimplePOJO actual =
                customContainer.create(
                        SimplePOJO.class,
                        Map.of(
                                Integer.class.getName(),
                                expected.getNumber(),
                                String.class.getName(),
                                expected.getString()));
        assertEquals(expected, actual);
    }

    @Test
    void testCreateWithParametersOverride() {
        SimplePOJO expected = new SimplePOJO(1, "asd");

        customContainer.addInstance(1, "number");
        customContainer.addInstance("asd", "string");

        customContainer.addFactoryMethod(
                SimplePOJO.class,
                (container) -> {
                    int number = container.getInstance(Integer.class, "number");
                    String string = container.getInstance(String.class, "string");
                    return new SimplePOJO(number, string);
                });

        SimplePOJO actual = customContainer.create(SimplePOJO.class);

        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getString(), actual.getString());
    }

    @Test
    void testCreateDifferentInstances() {
        Object storedInstance = new Object();
        customContainer.addInstance(storedInstance);
        customContainer.addFactoryMethod(Object.class, (container) -> new Object());
        Object actual = customContainer.create(Object.class);
        assertNotEquals(storedInstance, actual);
    }

    @Test
    void testCreateWithParametersDifferentInstances() {
        SimplePOJO other = new SimplePOJO(2, "sda");

        customContainer.addInstance(other);
        customContainer.addFactoryMethod(
                SimplePOJO.class,
                (container) -> {
                    int number = container.getInstance(Integer.class);
                    String string = container.getInstance(String.class);
                    return new SimplePOJO(number, string);
                });

        SimplePOJO actual =
                customContainer.create(
                        SimplePOJO.class,
                        Map.of(Integer.class.getName(), 1, String.class.getName(), "asd"));
        assertNotEquals(other, actual);
    }
}
