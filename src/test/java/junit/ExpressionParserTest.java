package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.objenesis.strategy.BaseInstantiatorStrategy;
import org.siit.BinaryOperator;
import org.siit.StringExpression;
import org.siit.ValidationException;

import java.util.List;

public class ExpressionParserTest {

    @Test
    public void constantTest() {
        StringExpression e = new StringExpression("0");
        Assertions.assertEquals(List.of(0), e.getElements());
    }

    @Test
    public void multiDigitConstantTest() {
        StringExpression e = new StringExpression("423523453");
        Assertions.assertEquals(List.of(423523453), e.getElements());

    }

    @Test
    public void oneBinaryOperandTest() {
        StringExpression e = new StringExpression("1 + 24");
        Assertions.assertEquals(List.of(1, BinaryOperator.ADD,24 ), e.getElements());
    }

    @Test
    public void incorrectNumberThrowsException() {
        Assertions.assertThrows(ValidationException.class,()-> new StringExpression("345g"));
    }

    @Test
    public void incorrectNumberInExpressionThrowsException() {
        Assertions.assertThrows(ValidationException.class, ()-> new StringExpression("1 + abc"));
    }

    @Test
    public void incorrectNumberOfTokensThrowsException() {
        Assertions.assertThrows(ValidationException.class, ()-> new StringExpression("1 +"));

    }


    @Test
    public void testOperatorAndMultipleSpaces() {
        StringExpression e = new StringExpression("1 +         24");
        Assertions.assertEquals(List.of(1, BinaryOperator.ADD,24 ), e.getElements());

    }

    @Test
    public void testConstantWithWhitespace() {
        StringExpression e = new StringExpression("1        +         24");
        Assertions.assertEquals(List.of(1, BinaryOperator.ADD,24 ), e.getElements());
    }

}
