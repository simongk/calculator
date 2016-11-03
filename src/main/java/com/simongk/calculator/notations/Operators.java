package com.simongk.calculator.notations;

public enum Operators {


	ADD("+"),
	SUBTRACT ("-"),
	MULTIPLY("*"),
	DIVIDE ("/"),
	MODULO ("%"),

	ADD_REST ("add"),
	SUBTRACT_REST ("sub"),
	MULTIPLY_REST ("mul"),
	DIVIDE_REST ("div"),
	MODULO_REST ("mod");

	private final String sign;

	private Operators(final String sign){
		this.sign = sign;
	}

	@Override
	public String toString() {
		return sign;
	}
	
	
}
