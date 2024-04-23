package org.example.smartercalculator.requestMapper;

import java.util.Optional;
import org.example.smartercalculator.CalculationRequest;

public class DoubleCalculatorRequestMapper extends NumberCalculatorRequestMapper {
    @Override
    protected Optional<CalculationRequest> mapNumbers(
            String leftOperandString, String operatorString, String rightOperandString) {
        try {
            Double leftOperand = Double.parseDouble(leftOperandString);
            Double rightOperand = Double.parseDouble(rightOperandString);

            return Optional.of(new CalculationRequest(leftOperand, rightOperand, operatorString));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
