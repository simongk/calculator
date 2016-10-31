package com.simongk.calculator.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class CalculatorControllerTest {

	@InjectMocks
	private CalculatorController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void addRpnTest() throws Exception {
		assertThat(performRpnRequest("5 5 add")).isEqualTo(10);
	}

	@Test
	public void subtractRpnTest() throws Exception {
		assertThat(performRpnRequest("15 5 sub")).isEqualTo(10.0);
	}

	@Test
	public void multiplyRpnTest() throws Exception {
		assertThat(performRpnRequest("5 5 mul")).isEqualTo(25.0);
	}

	@Test
	public void divideRpnTest() throws Exception {
		assertThat(performRpnRequest("25 5 div")).isEqualTo(5.0);
	}

	@Test
	public void moduloRpnTest() throws Exception {
		assertThat(performRpnRequest("6 4 mod")).isEqualTo(2.0);
	}
	
	@Test
	public void addNpnTest() throws Exception {
		assertThat(performNpnRequest("add 5 5")).isEqualTo(10);
	}

	@Test
	public void subtractNpnTest() throws Exception {
		assertThat(performNpnRequest("sub 15 5")).isEqualTo(10.0);
	}

	@Test
	public void multiplyNpnTest() throws Exception {
		assertThat(performNpnRequest("mul 5 5")).isEqualTo(25.0);
	}

	@Test
	public void divideNpnTest() throws Exception {
		assertThat(performNpnRequest("div 25 5")).isEqualTo(5.0);
	}

	@Test
	public void moduloNpnTest() throws Exception {
		assertThat(performNpnRequest("mod 6 4")).isEqualTo(2.0);
	}


	private double performRpnRequest(String input) throws Exception, UnsupportedEncodingException {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/onp?input=" + input))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		double calculationResult = Double.parseDouble(result.getResponse().getContentAsString());
		return calculationResult;
	}
	
	private double performNpnRequest(String input) throws Exception, UnsupportedEncodingException {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/np?input=" + input))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		double calculationResult = Double.parseDouble(result.getResponse().getContentAsString());
		return calculationResult;
	}
	
	
	
}
