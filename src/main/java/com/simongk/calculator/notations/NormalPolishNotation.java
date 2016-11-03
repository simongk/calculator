package com.simongk.calculator.notations;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import com.simongk.calculator.service.Calculator;

public class NormalPolishNotation implements Calculator {

	private List<String> inputList;
	private ListIterator<String> iterator;
	private String number;
	private CalculatorOperations operations;

	@Override
	public double calculate(String input) throws ArithmeticException, NumberFormatException, NoSuchElementException {
		operations = new CalculatorOperations();
		operations.setRPN(false);
		inputList = Arrays.asList(input.split("\\s+"));
		iterator = inputList.listIterator(inputList.size());

		while (iterator.hasPrevious()) {
			number = iterator.previous();
			operations.doChosenCalculation(number);
		}

		return operations.getStack().pop();
	}

}
