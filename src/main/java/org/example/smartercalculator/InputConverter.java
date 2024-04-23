package org.example.smartercalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.example.smartercalculator.requestMapper.BooleanCalculatorRequestMapper;
import org.example.smartercalculator.requestMapper.CalculatorRequestMapper;
import org.example.smartercalculator.requestMapper.DoubleCalculatorRequestMapper;
import org.example.smartercalculator.requestMapper.IntegerCalculatorRequestMapper;

public class InputConverter {

    private static final List<CalculatorRequestMapper> MAPPERS =
            Arrays.asList(
                    new BooleanCalculatorRequestMapper(),
                    new IntegerCalculatorRequestMapper(),
                    new DoubleCalculatorRequestMapper());

    public static List<CalculationRequest> mapRequests(String[] args) {
        if (args.length < 3 || args.length % 3 != 0) {
            throw new IllegalArgumentException();
        }

        List<CalculationRequest> calculatorRequests = new ArrayList<>();

        for (int i = 0; i < args.length; i += 3) {
            calculatorRequests.add(mapRequest(args[i], args[i + 1], args[i + 2]));
        }

        return calculatorRequests;
    }

    private static CalculationRequest mapRequest(
            String leftOperandString, String operatorString, String rightOperandString) {
        for (CalculatorRequestMapper mapper : MAPPERS) {
            Optional<CalculationRequest> calculatorRequestOptional =
                    mapper.tryMapRequest(leftOperandString, operatorString, rightOperandString);
            if (calculatorRequestOptional.isPresent()) {
                return calculatorRequestOptional.get();
            }
        }

        return null;
    }
}
