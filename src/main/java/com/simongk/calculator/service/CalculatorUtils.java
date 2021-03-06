package com.simongk.calculator.service;

import java.util.Scanner;

import lombok.Getter;

@Getter
public class CalculatorUtils {

	private Calculator calculator;
	private Scanner scanner;
	private String input;
	private boolean again;

	public CalculatorUtils(Calculator calculator) {
		this.calculator = calculator;
	}

	public void doConsoleCalculation() {

		scanner = new Scanner(System.in);
		System.out.println("Input your data: ");
		input = scanner.nextLine();

		try {
			System.out.println(String.format("%.12f", getCalculator().calculate(input)));

		} catch (Exception e) {
			System.out.println("Wrong data provided.");
		}
	}
}
