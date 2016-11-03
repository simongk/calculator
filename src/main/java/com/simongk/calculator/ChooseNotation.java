package com.simongk.calculator;

import com.simongk.calculator.notations.NormalPolishNotation;
import com.simongk.calculator.notations.ReversePolishNotation;
import com.simongk.calculator.service.Calculator;

public class ChooseNotation {

	private final String RPN = "Reverse Polish Notation";
	private final String NPN = "Normal Polish Notation";

	public Calculator chooseNotationType(String notationName) {

		if (isNormalPolishNotation(notationName)) {
			System.out.println("Notation chosen: " + NPN);
			return new NormalPolishNotation();
		}

		else if (isReversePolishNotation(notationName)) {
			System.out.println("Notation chosen: " + RPN);
			return new ReversePolishNotation();

		} else {
			System.out.println("Did not recognize notation type.");
			return null;
		}
	}

	private boolean isReversePolishNotation(String notationName) {
		return notationName.equalsIgnoreCase("RPN");
	}

	private boolean isNormalPolishNotation(String notationName) {
		return notationName.equalsIgnoreCase("NPN");
	}

}
