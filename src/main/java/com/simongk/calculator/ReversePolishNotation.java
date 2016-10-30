package com.simongk.calculator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ListIterator;

public class ReversePolishNotation implements Calculator {

	protected Deque<Double> stack;
	protected double firstOperand;
	protected double secondOperand;
	protected double result;
	protected ListIterator<String> iterator;
	protected List<String> inputList;
	protected String number;

	private static final String ADD = "+";
	private static final String SUBTRACT = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";

	public double calculate(String input) throws ArithmeticException, NumberFormatException, EmptyStackException {
		stack = new ArrayDeque<>();
		inputList = Arrays.asList(input.split(" "));
		iterator = inputList.listIterator();

		while (iterator.hasNext()) {
			number = iterator.next();
			calculation();
		}

		return getElement();
	}

	protected void calculation() {
		startsFromZeroException(number);
		chooseOperation(number);
	}

	protected Double getElement() {
		return stack.pop();
	}

	private void chooseOperation(String number) {
		switch (number) {
		case ADD:
			stack.push(operation(ADD));
			break;
		case SUBTRACT:
			stack.push(operation(SUBTRACT));
			break;
		case MULTIPLY:
			stack.push(operation(MULTIPLY));
			break;
		case DIVIDE:
			stack.push(operation(DIVIDE));
			break;
		default:
			getLastNumberFromInput(number);
		}
	}

	public double operation(String operator) {
		secondOperand = getElement();
		firstOperand = getElement();
		operationExecution(operator);
		return result;
	}

	protected void operationExecution(String operator) {
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
	}

	private void startsFromZeroException(String number) {
		if (number.startsWith("0") && number.length() > 1)
			throw new NumberFormatException();
	}

	private void getLastNumberFromInput(String number) {
		stack.push(Double.parseDouble(number));
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