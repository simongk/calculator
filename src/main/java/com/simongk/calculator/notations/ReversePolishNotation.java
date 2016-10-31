package com.simongk.calculator.notations;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.simongk.calculator.service.Calculator;

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
	private static final String MODULO = "%";

	private static final String ADD_REST = "add";
	private static final String SUBTRACT_REST = "sub";
	private static final String MULTIPLY_REST = "mul";
	private static final String DIVIDE_REST = "div";
	private static final String MODULO_REST = "mod";

	public double calculate(String input) throws ArithmeticException, NumberFormatException, NoSuchElementException {
		stack = new ArrayDeque<>();
		inputList = Arrays.asList(input.split("\\s+"));
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
		case ADD_REST:
			stack.push(operation(ADD));
			break;
		case SUBTRACT:
		case SUBTRACT_REST:
			stack.push(operation(SUBTRACT));
			break;
		case MULTIPLY:
		case MULTIPLY_REST:
			stack.push(operation(MULTIPLY));
			break;
		case DIVIDE:
		case DIVIDE_REST:
			stack.push(operation(DIVIDE));
			break;
		case MODULO:
		case MODULO_REST:
			stack.push(operation(MODULO));
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
			byZeroException();
			result = firstOperand / secondOperand;
		} else if (isModulo(operator)) {
			byZeroException();
			if (isInteger())
				result = firstOperand % secondOperand;
			else
				result = Math.IEEEremainder(firstOperand, secondOperand);
		}
	}

	private boolean isInteger() {
		return ((firstOperand == Math.floor(firstOperand)) && !Double.isInfinite(firstOperand))
				|| ((secondOperand == Math.floor(secondOperand)) && !Double.isInfinite(secondOperand));
	}

	private void byZeroException() {
		if (secondOperand == 0)
			throw new ArithmeticException("Cannot do it by zero.");
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

	private boolean isModulo(String operator) {
		return operator.equals(MODULO);
	}

}