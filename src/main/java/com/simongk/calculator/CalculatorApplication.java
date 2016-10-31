package com.simongk.calculator;

public class CalculatorApplication {

	private static final String WELCOMETEXT = "Welcome to the calculator.";

	public static void main(String[] args) {

		System.out.println(WELCOMETEXT);
		new ChooseNotation().chooseNotationType();

	}
}
