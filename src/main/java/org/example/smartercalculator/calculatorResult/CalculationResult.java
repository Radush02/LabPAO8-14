package org.example.smartercalculator.calculatorResult;

import org.example.smartercalculator.CalculationRequest;

public interface CalculationResult {
    Object computeResult();

    CalculationRequest getRequest();
}
