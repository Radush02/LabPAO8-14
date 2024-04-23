package org.example.smartercalculator.requestMapper;

import java.util.Optional;
import org.example.smartercalculator.CalculationRequest;

public class IntegerCalculatorRequestMapper extends NumberCalculatorRequestMapper {
    @Override
    protected Optional<CalculationRequest> mapNumbers(
            String leftOperandString, String operatorString, String rightOperandString) {
        try {
            Integer leftOperand = Integer.parseInt(leftOperandString);
            Integer rightOperand = Integer.parseInt(rightOperandString);

            return Optional.of(new CalculationRequest(leftOperand, rightOperand, operatorString));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
