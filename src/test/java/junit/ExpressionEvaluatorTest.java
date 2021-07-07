package junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.siit.BinaryOperator;
import org.siit.Expression;
import org.siit.ExpressionEvaluator;

import java.util.List;

class ExpressionEvaluatorTest {
	//given a list of elements-> should be the result os calling the constructor in StringExpression
	//verify the actual result of the operation

	private Expression mockExpression (List<Object> elements ){
		Expression expr = Mockito.mock(Expression.class);
		Mockito.when(expr.getElements()).thenReturn(elements);
		return expr;
	}

	@Test
	void testZeroConstant() {
		int result = ExpressionEvaluator.evaluate(mockExpression(List.of(7)));
		Assertions.assertEquals(7,result);

	}
	
	@Test
	void testFiveConstant() {
		int result = ExpressionEvaluator.evaluate(mockExpression(List.of(1,BinaryOperator.ADD,7,BinaryOperator.MULTIPLY,2)));
		Assertions.assertEquals(16,result);

	}
	
	@Test
	void testMultiDigitConstant() {
		int result = ExpressionEvaluator.evaluate(mockExpression(List.of(1,BinaryOperator.ADD,777777)));
		Assertions.assertEquals(777778,result);
	}
	
	@Test
	void testSimpleAddition() {
	// 1 + 1
		int result = ExpressionEvaluator.evaluate(mockExpression(List.of(1,BinaryOperator.ADD,7)));
		Assertions.assertEquals(8,result);
	}
	//TODO: test -> 1*2 ;
}
