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
	private double result;

	@GetMapping("onp")
	public String calculateRnp(@RequestParam(value = "input", required = false) String input) {
		if (input == "")
			return "No data provided";
		else
			return rpn(input);
	}

	@GetMapping("np")
	public String calculateNpn(@RequestParam(value = "input", required = false) String input) {
		if (input == "")
			return "No data provided";
		else
			return npn(input);
	}

	private String rpn(String input) throws ArithmeticException,NumberFormatException,NoSuchElementException {
		try {
			calculator = new ReversePolishNotation();
			result = calculator.calculate(input);
		} catch (NumberFormatException | NoSuchElementException e) {
			return "Wrong data provided.";
		} catch (ArithmeticException e) {
			return "Cannot do it by zero.";
		} catch (NullPointerException e){
			return "There is no data in parameter.";
		}
		return Double.toString(result);
	}

	private String npn(String input) throws ArithmeticException,NumberFormatException,NoSuchElementException {
		try {
			calculator = new NormalPolishNotation();
			result = calculator.calculate(input);
		} catch (NumberFormatException | NoSuchElementException e) {
			return "Wrong data provided.";
		} catch (ArithmeticException e) {
			return "Cannot do it by zero.";
		} catch (NullPointerException e){
			return "There is no data in parameter.";
		}
		return Double.toString(result);
	}
}
