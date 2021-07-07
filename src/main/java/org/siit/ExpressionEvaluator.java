package org.siit;


import java.util.List;

public class ExpressionEvaluator {

    public static int evaluate(Expression expression) {
        List<Object> elem = expression.getElements();
        int result = 0;
        if(elem.size() == 1) {
            return (int) elem.get(0);
        }
        result =evalBinary((int)elem.get(0),(int)elem.get(2),(BinaryOperator) elem.get(1));

        for(int i=1;i<elem.size()-2;i+=2){
             result = evalBinary(result, (int) elem.get(i + 3), (BinaryOperator) elem.get(i + 2));
        }

        return result;
    }
          private static int  evalBinary(int op1, int op2, BinaryOperator op){
        switch (op){
            case ADD:
            return op1 + op2;
            case SUBTRACT:
                return op1 - op2;
            case MULTIPLY:
                return op1 * op2;
            case DIVIDE:
                return op1/op2;
            case MODULUS:
                return op1%op2;
            default:
                throw new ValidationException("Unknown operator: " + op);
        }

    }

}
