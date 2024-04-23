package org.example.smartercalculator.requestMapper;

import java.util.Optional;
import org.example.smartercalculator.CalculationRequest;

public interface CalculatorRequestMapper {
    Optional<CalculationRequest> tryMapRequest(
            String leftOperandString, String operatorString, String rightOperandString);
}
