package com.simongk.calculator;

import java.util.Scanner;

public class CalculatorApplication {

	private static Calculator calculator;
	private static boolean again;
	private static Scanner scanner;
	private static final String WELCOMETEXT = "Welcome to the calculator, provide the data in ";
	private static final String RPN = "Reverse Polish Notation.";
	private static final String NPN = "Normal Polish Notation.";

	public static void main(String[] args) {
		
		System.out.println(WELCOMETEXT + NPN);
		scanner = new Scanner(System.in);
		calculator = new NormalPolishNotation();
		again = true;
		CalculatorUtils calculatorUtils = new CalculatorUtils(calculator);

		do {
			calculatorUtils.mainCalculation();
			isAgain();
		} while (again);

	}

	private static void isAgain() {
		System.out.println("Again? Y/N");
		if (scanner.next().equalsIgnoreCase("N")){
			System.out.println("Thank you for using our calculator!");
			again = false;
		}
	}
}
