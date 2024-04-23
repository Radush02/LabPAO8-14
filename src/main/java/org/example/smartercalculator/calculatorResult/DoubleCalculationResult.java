package org.example.smartercalculator.calculatorResult;

import org.example.smartercalculator.CalculationRequest;

public class DoubleCalculationResult extends AbstractCalculationResult {
    public DoubleCalculationResult(CalculationRequest request) {
        super(request);
        if (request == null) {
            throw new IllegalArgumentException("Request-ul nu poate fi null");
        }
    }

    @Override
    public Object computeResult() {
        try {
            CalculationRequest request = getRequest();
            Double leftOperand = (Double) request.getLeftOperand();
            Double rightOperand = (Double) request.getRightOperand();

            return switch (request.getOperation()) {
                case "+" -> leftOperand + rightOperand;
                case "-" -> leftOperand - rightOperand;
                case "*" -> leftOperand * rightOperand;
                case "/" -> leftOperand / rightOperand;
                default -> throw new IllegalArgumentException();
            };
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Request null.");
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Tipuri diferite de operatori.");
        }
    }
}
