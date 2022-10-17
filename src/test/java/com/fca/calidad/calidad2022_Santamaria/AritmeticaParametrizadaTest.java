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
	private float arg1;
	private float arg2;
	private float arg3;
	private Calculadora miCalculadora; //Object
	private algebra mycalculadora;
	
	public AritmeticaParametrizadaTest (float arg1, float arg2, float arg3) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}

	//set parameters to test
	@Parameters
		public static Collection<Object[]>data() {
			return Arrays.asList(new Object[][] {
				{4,2,2},			//Integers
				{6,-3,-2},			//Negative Integer
				{5,5,1},
				{5,2,2.5f},
				{5,-2,-2.5f},
				});
		}
	@Before
	public void setUp() throws Exception {
		//Create new object
		mycalculadora = new algebra();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void Division_Test() {
		//Using MamCrest
		//Exercise code to run and test
		float resEjecucion = mycalculadora.division(this.arg1, this.arg2);
		
		//Verify
		assertThat(this.arg3, is(resEjecucion));
	}
}
