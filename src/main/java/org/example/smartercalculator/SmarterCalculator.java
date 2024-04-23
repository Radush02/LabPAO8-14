package org.example.smartercalculator;

import java.util.List;
import java.util.stream.Collectors;
import org.example.smartercalculator.calculatorResult.BooleanCalculationResult;
import org.example.smartercalculator.calculatorResult.CalculationResult;
import org.example.smartercalculator.calculatorResult.DoubleCalculationResult;
import org.example.smartercalculator.calculatorResult.IntegerCalculationResult;

public class SmarterCalculator {

    public static List<CalculationResult> calculate(String[] args) {
        return InputConverter.mapRequests(args).stream()
                .map(SmarterCalculator::createCalculatorResponse)
                .collect(Collectors.toList());
    }

    private static CalculationResult createCalculatorResponse(CalculationRequest request) {
        try {
            return switch (request.getRequestType()) {
                case Boolean -> new BooleanCalculationResult(request);
                case Integer -> new IntegerCalculationResult(request);
                case Double -> new DoubleCalculationResult(request);
                default -> throw new IllegalArgumentException();
            };
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Request null sau tipuri diferite de operatori.");
        }
    }
}
