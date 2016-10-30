package com.simongk.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static com.googlecode.catchexception.apis.CatchExceptionAssertJ.*;
import static com.googlecode.catchexception.CatchException.*;

import org.junit.Before;
import org.junit.Test;

public class ReversePolishNotationTest {

	private Calculator notation;

	
	@Before
	public void setup() {
		
		notation = new ReversePolishNotation();
	}

	@Test
	public void addingTest() {
		assertThat(notation.calculate("12 5 +")).isEqualTo(17.0);
	}

	@Test
	public void subtractionTest() {
		assertThat(notation.calculate("12 5 -")).isEqualTo(7.0);
	}

	@Test
	public void divisionTest() throws ArithmeticException {
		assertThat(notation.calculate("6 3 /")).isEqualTo(2.0);
	}

	@Test
	public void testArtihmeticException() {
		
		when(notation).calculate("3 0 /");
		
		then(caughtException())
		.isInstanceOf(ArithmeticException.class)
		.hasMessage("Cannot divide by zero.");
		
	}

	@Test
	public void multiplicationTest() {
		assertThat(notation.calculate("12345.4321 14 *")).isEqualTo(172836.0494);
	}

	@Test
	public void calculateSomethingComplicatedTest() {
		assertThat(notation.calculate("5 1 2 + 4 * + 3 -")).isEqualTo(14.0);
	}
}
