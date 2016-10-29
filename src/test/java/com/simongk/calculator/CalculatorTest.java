package com.simongk.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.*;
import static com.googlecode.catchexception.CatchException.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private Calculator reversePolishNotation;

	
	@Before
	public void setup() {
		
		reversePolishNotation = new ReversePolishNotation();
	}

	@Test
	public void addingInRpnTest() {
		assertThat(reversePolishNotation.calculate("12 5 +")).isEqualTo(17.0);
	}

	@Test
	public void subtractionInRpnTest() {
		assertThat(reversePolishNotation.calculate("12 5 -")).isEqualTo(7.0);
	}

	@Test
	public void divisionInRpnTest() throws ArithmeticException {
		assertThat(reversePolishNotation.calculate("6 3 /")).isEqualTo(2.0);
	}

	@Test
	public void testArtihmeticException() {
		
		when(reversePolishNotation).calculate("3 0 /");
		
		then(caughtException())
		.isInstanceOf(ArithmeticException.class)
		.hasMessage("Cannot divide by zero.");
		
	}

	@Test
	public void multiplicationInRpnTest() {
		assertThat(reversePolishNotation.calculate("12345.4321 14 *")).isEqualTo(172836.0494);
	}

	@Test
	public void calculateSomethingComplicatedInRpnTest() {
		assertThat(reversePolishNotation.calculate("5 1 2 + 4 * + 3 -")).isEqualTo(14.0);
	}
}
