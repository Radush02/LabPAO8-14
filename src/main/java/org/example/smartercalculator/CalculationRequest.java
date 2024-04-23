package org.example.smartercalculator;

public class CalculationRequest {
    private final Object leftOperand;
    private final Object rightOperand;
    private final String operation;

    public CalculationRequest(Object leftOperand, Object rightOperand, String operation) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operation = operation;
    }

    // In case of Java 21 you can uncomment this method and remove the one blow
    //    public RequestType getRequestType() {
    //        // Switch patterns are reserved for Java 21+
    //        return switch (this.leftOperand) {
    //            case Boolean b -> RequestType.Boolean;
    //            case Integer i -> this.rightOperand instanceof Integer ? RequestType.Integer :
    // RequestType.Double;
    //            case Double d -> RequestType.Double;
    //            default -> RequestType.Undefined;
    //        };
    //    }

    public RequestType getRequestType() {
        // Switch patterns are reserved for Java 21+

        if (this.leftOperand instanceof Boolean) {
            return RequestType.Boolean;
        } else if (this.leftOperand instanceof Integer) {
            return this.rightOperand instanceof Integer ? RequestType.Integer : RequestType.Double;
        } else if (this.leftOperand instanceof Double) {
            return RequestType.Double;
        }

        return RequestType.Undefined;
    }

    @Override
    public String toString() {
        return leftOperand + " " + operation + " " + rightOperand;
    }

    public Object getLeftOperand() {
        return leftOperand;
    }

    public Object getRightOperand() {
        return rightOperand;
    }

    public String getOperation() {
        return operation;
    }

    public enum RequestType {
        Undefined,
        Integer,
        Double,
        Boolean
    }
}
