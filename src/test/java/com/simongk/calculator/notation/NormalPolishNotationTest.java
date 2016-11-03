package com.simongk.calculator.notation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.simongk.calculator.notations.NormalPolishNotation;
import com.simongk.calculator.service.Calculator;

public class NormalPolishNotationTest {

	private Calculator notation;

	@Before
	public void setup() {

		notation = new NormalPolishNotation();
	}

	@Test
	public void addingTest() {
		assertThat(notation.calculate("+ 12 5")).isEqualTo(17.0);
	}

	@Test
	public void subtractionTest() {
		assertThat(notation.calculate("- 12 5")).isEqualTo(7.0);
	}

	@Test
	public void divisionTest() throws NoSuchElementException {
		assertThat(notation.calculate("/ 10 5")).isEqualTo(2.0);
	}

	@Test(expected = NoSuchElementException.class)
	public void testNoSuchElementException() {
		notation.calculate("/ 3 0");
	}

	@Test
	public void multiplicationTest() {
		assertThat(notation.calculate("* 12345.4321 14")).isEqualTo(172836.0494);
	}

	@Test
	public void calculateSomethingComplicatedTest() {
		assertThat(notation.calculate("- * / 15 - 7 + 1 1 3 + 2 + 1 1")).isEqualTo(5.0);
	}

	@Test
	public void moduloTest() {
		assertThat(notation.calculate("% 14 10")).isEqualTo(4.0);
	}
}
