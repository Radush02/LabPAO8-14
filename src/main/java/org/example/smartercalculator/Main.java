package org.example.smartercalculator;

import java.sql.*;
import java.util.ArrayList;
import org.example.smartercalculator.calculatorResult.PrintingCalculationResult;
import org.example.smartercalculator.utils.DBConnector;

public class Main {
    static DBConnector db = DBConnector.getInstance();

    public static void main(String[] args) {
        args =
                new String[] {
                    "1", "+", "2", "2", "*", "5",
                    "1", "+", "5.0", "1.0", "-", "2",
                    "10.0", "/", "2.5",
                };

        runSmarterCalculator(args);
    }

    private static void runSmarterCalculator(String[] args) {
        Task1_4(args);
        Task2();
        Task3(CalculationRequest.RequestType.Integer);
    }

    private static void Task3(CalculationRequest.RequestType operationType) {
        try (Connection c =
                db.connectToDatabase("jdbc:mysql://localhost:3306/pao", "root", "gagaga")) {
            String deleteSql = "DELETE FROM smartercalculatorresults WHERE operation_type = ?;";
            try (PreparedStatement preparedStatement = c.prepareStatement(deleteSql)) {
                preparedStatement.setString(1, operationType.toString());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void Task2() {
        ArrayList<String> argsSQL = new ArrayList<>();
        try (Connection c =
                db.connectToDatabase("jdbc:mysql://localhost:3306/pao", "root", "gagaga")) {
            try (Statement statement = c.createStatement()) {
                String selectSql = "SELECT * FROM smartercalculatorresults;";
                try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                    while (resultSet.next()) {
                        String leftOperand = resultSet.getString("left_operand");
                        String rightOperand = resultSet.getString("right_operand");
                        String operation = resultSet.getString("operation");
                        /*
                        Realizați **citirea** tuturor intrărilor din baza de date.
                         */
                        String operationType = resultSet.getString("operation_type");
                        String result = resultSet.getString("result");
                        argsSQL.add(leftOperand);
                        argsSQL.add(operation);
                        argsSQL.add(rightOperand);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SmarterCalculator.calculate(argsSQL.toArray(new String[0])).stream()
                .map(PrintingCalculationResult::new)
                .forEach(PrintingCalculationResult::computeResult);
    }

    private static void Task1_4(String[] args) {
        try (Connection c =
                db.connectToDatabase("jdbc:mysql://localhost:3306/pao", "root", "gagaga")) {
            try (Statement s = c.createStatement()) {
                String createTable =
                        "CREATE TABLE IF NOT EXISTS smarterCalculatorResults (\n"
                                + "    id INT auto_increment,\n"
                                + "    operation_type VARCHAR(20) NOT NULL,\n"
                                + "    left_operand VARCHAR(254) NOT NULL,\n"
                                + "    right_operand VARCHAR(254) NOT NULL,\n"
                                + "    operation VARCHAR(2) NOT NULL,\n"
                                + "    result VARCHAR(255) NOT NULL\n"
                                + ");";
                s.execute(createTable);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SmarterCalculator.calculate(args).stream()
                .map(PrintingCalculationResult::new)
                .forEach(
                        result -> {
                            try {
                                result.computeSQLResult();
                            } catch (SQLException e) {
                                System.err.println(e.getMessage());
                            }
                        });
    }
}
