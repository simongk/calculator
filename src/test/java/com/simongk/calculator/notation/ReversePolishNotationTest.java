package com.simongk.calculator.notation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.simongk.calculator.notations.ReversePolishNotation;
import com.simongk.calculator.service.Calculator;

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

	@Test(expected = ArithmeticException.class)
	public void testArtihmeticException() {
		notation.calculate("3 0 /");
	}

	@Test
	public void multiplicationTest() {
		assertThat(notation.calculate("12345.4321 14 *")).isEqualTo(172836.0494);
	}

	@Test
	public void calculateSomethingComplicatedTest() {
		assertThat(notation.calculate("5 1 2 + 4 * + 3 -")).isEqualTo(14.0);
	}

	@Test
	public void moduloTest() {
		assertThat(notation.calculate("14 10 %")).isEqualTo(4.0);
	}

}
