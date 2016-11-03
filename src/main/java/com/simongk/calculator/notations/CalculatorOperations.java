package com.simongk.calculator.notations;

import java.util.ArrayDeque;
import java.util.Deque;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorOperations {

	private final Deque<Double> stack = new ArrayDeque<>();
	private double firstOperand;
	private double secondOperand;
	private double valueToPush;
	private boolean isRPN;

	void doChosenCalculation(String number) {
		startsFromZeroException(number);
		pushChosenOperation(number);
	}

	private void pushChosenOperation(String number) {
		if (isAdd(number)) {
			stack.push(calculateInCertainNotation(Operators.ADD.toString()));
		} else if (isSubtract(number)) {
			stack.push(calculateInCertainNotation(Operators.SUBTRACT.toString()));
		} else if (isMultiply(number)) {
			stack.push(calculateInCertainNotation(Operators.MULTIPLY.toString()));
		} else if (isDivide(number)) {
			stack.push(calculateInCertainNotation(Operators.DIVIDE.toString()));
		} else if (isModulo(number)) {
			stack.push(calculateInCertainNotation(Operators.MODULO.toString()));
		} else {
			getLastNumberFromInput(number);
		}
	}

	private double calculateInCertainNotation(String operator) {
		checkNotation();
		notANumberException(firstOperand, secondOperand);
		doChosenOperation(operator, firstOperand, secondOperand);
		return valueToPush;
	}

	private void checkNotation() {
		if (isRPN == true && !Double.isNaN(secondOperand)) {
			secondOperand = getElement();
			firstOperand = getElement();
		} else {
			firstOperand = getElement();
			secondOperand = getElement();
		}
	}

	private void doChosenOperation(String operator, double firstOperand, double secondOperand) {
		if (isAdd(operator)){
			valueToPush = firstOperand + secondOperand;
			}
		else if (isSubtract(operator)){
			valueToPush = firstOperand - secondOperand;
		}
		else if (isMultiply(operator)){
			valueToPush = firstOperand * secondOperand;
		}
		else if (isDivide(operator)) {
			byZeroException();
			valueToPush = firstOperand / secondOperand;
		} else if (isModulo(operator)) {
			byZeroException();
			if (isInteger())
				valueToPush = firstOperand % secondOperand;
			else
				valueToPush = Math.IEEEremainder(firstOperand, secondOperand);
		}
	}

	private boolean isInteger() {
		return ((firstOperand == Math.floor(firstOperand)) && !Double.isInfinite(firstOperand))
				|| ((secondOperand == Math.floor(secondOperand)) && !Double.isInfinite(secondOperand));
	}

	private void byZeroException() {
		if (!(secondOperand > 0 || secondOperand < 0))
			stack.pop();
	}
	
	private void notANumberException(double firstOperand, double secondOperand){
		if(Double.isNaN(firstOperand) || Double.isNaN(secondOperand)){
			stack.pop();
		}
	}
	

	private void startsFromZeroException(String number) {
		if (number.startsWith("0") && number.length() > 1 && !number.startsWith("0."))
			stack.pop();
	}
	
	

	private void getLastNumberFromInput(String number) {
		if(!isDouble(number)){
			stack.pop();
		}
		else
			stack.push(Double.parseDouble(number));
	}
	
	private boolean isDouble(String number){
		try {
			Double.parseDouble(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isModulo(String number) {
		return number.equals(Operators.MODULO.toString()) || number.equals(Operators.MODULO_REST.toString());
	}

	private boolean isDivide(String number) {
		return number.equals(Operators.DIVIDE.toString()) || number.equals(Operators.DIVIDE_REST.toString());
	}

	private boolean isMultiply(String number) {
		return number.equals(Operators.MULTIPLY.toString()) || number.equals(Operators.MULTIPLY_REST.toString());
	}

	private boolean isSubtract(String number) {
		return number.equals(Operators.SUBTRACT.toString()) || number.equals(Operators.SUBTRACT_REST.toString());
	}

	private boolean isAdd(String number) {
		return number.equals(Operators.ADD.toString()) || number.equals(Operators.ADD_REST.toString());
	}

	private Double getElement() {
		return stack.pop();
	}
}