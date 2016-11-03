package com.simongk.calculator;

import java.util.Scanner;

import com.simongk.calculator.service.Calculator;
import com.simongk.calculator.service.CalculatorUtils;

public class CalculatorApplication {

	private static final String WELCOMETEXT = "Welcome to the calculator.";
	private static CalculatorUtils utils;
	private static Calculator notationType;
	private static String notationName;
	private static Scanner scanner;
	private static boolean again;

	public static void main(String[] args) {

		System.out.println(WELCOMETEXT);
		scanner = new Scanner(System.in);

		do {
			System.out.println("Type in NPN if u want to provide data in Normal Polish Notation "
					+ "or RPN to provide data in Reverse Polish Notation .");
			notationName = scanner.next();
			notationType = new ChooseNotation().chooseNotationType(notationName);
			if (notationType == null) {
				again = isAgain();
				continue;
			}
			utils = new CalculatorUtils(notationType);

			utils.doConsoleCalculation();
			again = isAgain();

		} while (again);

	}

	private static boolean isAgain() {
		System.out.println("Again? Y/N");
		if (scanner.next().equalsIgnoreCase("Y")) {
			return true;
		} else
			return false;
	}

}
