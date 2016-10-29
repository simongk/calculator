package com.simongk.calculator;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class ReversePolishNotation implements Calculator {

	private Stack<Double> stack;
	private double firstOperand;
	private double secondOperand;
	private double result;
	private static final String ADD = "+";
	private static final String SUBTRACT = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";

	public Double calculate(String input) throws ArithmeticException, NumberFormatException, EmptyStackException {

		stack = new Stack<>();

		Arrays.asList(input.split(" ")).stream().forEach(number -> {

			if(number.startsWith("0") && number.length() > 1) throw new NumberFormatException();	
			
			switch (number) {
			case ADD:
				operation(ADD);
				break;
			case SUBTRACT:
				operation(SUBTRACT);
				break;
			case MULTIPLY:
				operation(MULTIPLY);
				break;
			case DIVIDE:
				operation(DIVIDE);
				break;

			default:
				getLastNumberFromInput(number);
			}
		});

		return stack.pop();
	}

	private void getLastNumberFromInput(String number) {
		stack.push(Double.parseDouble(number));
	}

	private Double operation(String operator) {

		secondOperand = stack.pop();
		firstOperand = stack.pop();

		if (isAddition(operator))
			result = firstOperand + secondOperand;
		else if (isSubtraction(operator))
			result = firstOperand - secondOperand;
		else if (isMultiplication(operator))
			result = firstOperand * secondOperand;
		else if (isDivision(operator)) {
			if (secondOperand == 0)
				throw new ArithmeticException("Cannot divide by zero.");
			result = firstOperand / secondOperand;

		}

		return stack.push(result);

	}

	private boolean isAddition(String operator) {
		return operator.equals(ADD);
	}

	private boolean isSubtraction(String operator) {
		return operator.equals(SUBTRACT);
	}

	private boolean isMultiplication(String operator) {
		return operator.equals(MULTIPLY);
	}

	private boolean isDivision(String operator) {
		return operator.equals(DIVIDE);
	}

}
