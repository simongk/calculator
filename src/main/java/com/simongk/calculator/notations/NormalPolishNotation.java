package com.simongk.calculator.notations;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class NormalPolishNotation extends ReversePolishNotation {

	@Override
	public double calculate(String input) throws ArithmeticException, NumberFormatException, EmptyStackException, NoSuchElementException {
		stack = new ArrayDeque<>();
		inputList = Arrays.asList(input.split("\\s+"));
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
