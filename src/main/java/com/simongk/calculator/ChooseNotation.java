package com.simongk.calculator;

import java.util.Scanner;

import com.simongk.calculator.notations.*;
import com.simongk.calculator.service.*;

public class ChooseNotation {

	private CalculatorUtils calculatorUtils;
	private boolean again;
	private boolean switchNotations;
	private Scanner scanner;
	private final String RPN = "Reverse Polish Notation";
	private final String NPN = "Normal Polish Notation";

	public void chooseNotationType() {
		scanner = new Scanner(System.in);
		again = true;
		do {
			System.out.println("Type in NPN if u want to provide data in " + NPN
					+ " or RPN to provide data in Reverse Polish Notation.");
			String notationType = scanner.next();

			if (isNormalPolishNotation(notationType)) {
				System.out.println("Notation chosen: " + NPN);
				chosenCalculation(new NormalPolishNotation());

			}

			else if (isReversePolishNotation(notationType)) {
				System.out.println("Notation chosen: " + RPN);
				chosenCalculation(new ReversePolishNotation());

			} else {
				System.out.println("Did not recognize notation type.");
				switchNotations = true;
			}

		} while (switchNotations);
	}

	private boolean isReversePolishNotation(String notationType) {
		return notationType.equalsIgnoreCase("RPN");
	}

	private boolean isNormalPolishNotation(String notationType) {
		return notationType.equalsIgnoreCase("NPN");
	}

	private void chosenCalculation(Calculator calculator) {
		calculatorUtils = new CalculatorUtils(calculator);
		do {
			calculatorUtils.mainCalculation();
			isAgain();
		} while (again);
	}

	private void isAgain() {
		System.out.println("Again? Y/N");
		if (scanner.next().equalsIgnoreCase("N")) {
			again = false;

			System.out.println("Do you want to switch notations? Y/N");
			if (scanner.next().equalsIgnoreCase("Y")) {
				switchNotations = true;
			} else {
				System.out.println("Thank you for using our calculator!");
				System.exit(0);
			}
		}
	}

}
