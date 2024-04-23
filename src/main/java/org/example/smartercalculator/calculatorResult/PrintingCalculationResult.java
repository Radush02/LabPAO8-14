package org.example.smartercalculator.calculatorResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.smartercalculator.CalculationRequest;
import org.example.smartercalculator.utils.DBConnector;

public class PrintingCalculationResult implements CalculationResult {

    private final CalculationResult calculationResult;
    private final DBConnector db;

    public PrintingCalculationResult(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        this.db = DBConnector.getInstance();
    }

    @Override
    public Object computeResult() {
        Object result = calculationResult.computeResult();
        CalculationRequest request = calculationResult.getRequest();
        System.out.println("Operation " + request + " has result " + result);
        return result;
    }

    public void computeSQLResult() throws SQLException {
        Object result = calculationResult.computeResult();
        CalculationRequest request = calculationResult.getRequest();
        try (Connection c =
                db.connectToDatabase("jdbc:mysql://localhost:3306/pao", "root", "gagaga")) {
            String insert =
                    "INSERT INTO smarterCalculatorResults(operation_type, left_operand,"
                            + " right_operand, operation, result) VALUES (?, ?, ?, ?, ?);";
            String delete =
                    "DELETE FROM smarterCalculatorResults\n"
                            + "WHERE operation_type = ?\n"
                            + "  AND left_operand = ?\n"
                            + "  AND right_operand = ?\n"
                            + "  AND operation = ?\n"
                            + "  AND result = ?\n"
                            + ";";
            applyStatement(result, request, c, delete);
            applyStatement(result, request, c, insert);
        }
    }

    private void applyStatement(
            Object result, CalculationRequest request, Connection c, String insert)
            throws SQLException {
        try (PreparedStatement preparedStatement = c.prepareStatement(insert)) {
            preparedStatement.setString(1, request.getRequestType().toString());
            preparedStatement.setString(2, request.getLeftOperand().toString());
            preparedStatement.setString(3, request.getRightOperand().toString());
            preparedStatement.setString(4, request.getOperation());
            preparedStatement.setString(5, result.toString());
            preparedStatement.execute();
        }
    }

    @Override
    public CalculationRequest getRequest() {
        return calculationResult.getRequest();
    }
}
