package com.simongk.calculator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.EmptyStackException;

public class NormalPolishNotation extends ReversePolishNotation {

	@Override
	public double calculate(String input) throws ArithmeticException, NumberFormatException, EmptyStackException {
		stack = new ArrayDeque<>();
		inputList = Arrays.asList(input.split(" "));
		iterator = inputList.listIterator(inputList.size());

		while (iterator.hasPrevious()) {
			number = iterator.previous();
			calculation();
		}

		return getElement();
	}

	@Override
	public double operation(String operator) {
		firstOperand = getElement();
		secondOperand = getElement();
		operationExecution(operator);
		return result;
	}
}
