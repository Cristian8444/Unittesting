package org.siit;

import java.util.ArrayList;
import java.util.List;

public class StringExpression implements Expression {
	
	private List<Object> elements;
	
	public StringExpression(String expression) {
		elements = new ArrayList<>();
		//1 + 3 -> {"1","+","3"}
		String[] tokens=expression.split("\\s+");

		if(tokens.length%2==0|| tokens[0].isEmpty()){
			throw new ValidationException("Expression must have a even number of tokens.");
		}

			for (int i=0; i< tokens.length;i+=2 ){
				elements.add(parsenumber(tokens[i]));
				if(i<tokens.length-1){
				elements.add(parseOperator(tokens[i+1]));
				}
			}

//TODO : implement case when you have more than 3 tokens: 1 + 1 * 3 / 4 ....


	}
	private BinaryOperator parseOperator(String token){
		for (BinaryOperator operator: BinaryOperator.values()){
			if(operator.getSymbol().equals(token))
				return operator;
		}
		return null;
	}

	private Integer parsenumber(String number){
		boolean numeric = true;
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			numeric = false;
		}
		if(numeric) {
			return Integer.parseInt(number);
		}
		else throw new ValidationException("This is not a number");
	}

	public List<Object> getElements() {

		return elements;
	}
}
