import static org.junit.jupiter.api.Assertions.*;

import org.example.smartercalculator.CalculationRequest;
import org.example.smartercalculator.calculatorResult.BooleanCalculationResult;
import org.example.smartercalculator.calculatorResult.DoubleCalculationResult;
import org.example.smartercalculator.calculatorResult.IntegerCalculationResult;
import org.junit.jupiter.api.Test;

public class SmarterCalculatorTest {
    @Test
    public void testBooleanCalculationResult() {
        CalculationRequest request = new CalculationRequest(true, true, "&&");
        BooleanCalculationResult result = new BooleanCalculationResult(request);
        assertEquals(true, result.computeResult());
    }

    @Test
    public void testIntegerCalculationResult() {
        CalculationRequest request = new CalculationRequest(5, 5, "+");
        IntegerCalculationResult result = new IntegerCalculationResult(request);
        assertEquals(10, result.computeResult());
    }

    @Test
    public void testDoubleCalculationResult() {
        CalculationRequest request = new CalculationRequest(2.5, 2.5, "*");
        DoubleCalculationResult result = new DoubleCalculationResult(request);
        assertEquals(6.25, result.computeResult());
    }

    //    @Test
    //    public void testBooleanCalculationResultNullRequest() {
    //        CalculationRequest request = null;
    //        assertThrows(IllegalArgumentException.class, () -> new
    // DoubleCalculationResult(request));
    //    }
    @Test
    public void mismatchCalculationResult() {
        CalculationRequest request = new CalculationRequest(2.5, true, "*");
        assertThrows(
                IllegalArgumentException.class,
                () -> new DoubleCalculationResult(request).computeResult());
    }

    @Test
    public void testDoubleCalculationResultNullRequest() {
        CalculationRequest request = new CalculationRequest(null, null, null);
        assertThrows(
                IllegalArgumentException.class,
                () -> new DoubleCalculationResult(request).computeResult());
    }
}
