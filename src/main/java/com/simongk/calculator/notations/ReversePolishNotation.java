package com.simongk.calculator.notations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.simongk.calculator.service.Calculator;

public class ReversePolishNotation implements Calculator{

	private List<String> inputList;
	private CalculatorOperations operations;

	@Override
	public double calculate(String input) throws NoSuchElementException {
		operations = new CalculatorOperations();
		operations.setRPN(true);
		inputList = Arrays.asList(input.split("\\s+"));

		for(String number : inputList){
			operations.doChosenCalculation(number);
		}

		return operations.getStack().pop();
	}
}
