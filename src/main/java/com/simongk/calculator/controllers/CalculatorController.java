package com.simongk.calculator.controllers;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simongk.calculator.notations.NormalPolishNotation;
import com.simongk.calculator.notations.ReversePolishNotation;
import com.simongk.calculator.service.Calculator;

@RestController
public class CalculatorController {

	private Calculator calculator;

	@GetMapping("onp")
	public String calculateRnp(@RequestParam(value = "input", required = false) String input) {
			return rpn(input);
	}

	@GetMapping("np")
	public String calculateNpn(@RequestParam(value = "input", required = false) String input) {
			return npn(input);
	}

	private String rpn(String input) throws ArithmeticException,NumberFormatException,NoSuchElementException {
		double result = 0;
		try {
			calculator = new ReversePolishNotation();
			result = calculator.calculate(input);
		} catch (Exception e) {
			return "Wrong data provided.";
		}
		return Double.toString(result);
	}

	private String npn(String input) throws ArithmeticException,NumberFormatException,NoSuchElementException {
		double result = 0;
		try {
			calculator = new NormalPolishNotation();
			result = calculator.calculate(input);
		} catch (Exception e) {
			return "Wrong data provided.";
		}
		return Double.toString(result);
	}
}
