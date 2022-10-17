package com.fca.calidad.calidad2022_Santamaria;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AritmeticaParametrizadaTest {
	private double arg1;
	private double arg2;
	private double expected;
	private Calculadora miCalculadora; //Object
	
	public AritmeticaParametrizadaTest (double arg1, double arg2, double expected) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.expected = expected;
	}

	//set parameters to test
	@Parameters
		public static Collection<Object[]>data() {
			return Arrays.asList(new Object[][] {
				{4,2,8},			//Integers
				{6,-3,-18},			//Negative Integer
				{-5,-5,25}
				});
		}
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
}
