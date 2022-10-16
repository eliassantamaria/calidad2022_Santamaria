package com.fca.calidad.calidad2022_Santamaria;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Calculadora {

private algebra aritmetica;
	
	@Before
	public void setUp() throws Exception {
		aritmetica = new algebra();
		System.out.println("Este es el before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Este es el after");
	}

	@Test
	public void sumaTest() {
		//Inicializar
		double resultadoEsperado= 4; //2+2
		double resultadoEjecucion = 0;
		//Ejercicio del codigo
		resultadoEjecucion = aritmetica.suma(2,2);
		//Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}

	@Test
	public void restaTest() {
		//Inicializar
		double resultadoEsperado= 1; //3-2
		double resultadoEjecucion = 0;
		//Ejercicio del codigo
		resultadoEjecucion = aritmetica.resta(3,2);
		//Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void multiplicaTest() {
		//Inicializar
		double resultadoEsperado= 9; //3x3
		double resultadoEjecucion = 0;
		//Ejercicio del codigo
		resultadoEjecucion = aritmetica.multiplicacion(3,3);
		//Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	
	@Test
	public void divideTestEntre0() {
		//Inicializar
		double pInfiniteDouble = Double.POSITIVE_INFINITY; 
		double resultadoEsperado= pInfiniteDouble; //10/
		double resultadoEjecucion = 0;
		//Ejercicio del codigo
		resultadoEjecucion = aritmetica.division(10,0);
		//Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test(expected = ArithmeticException.class)
	public void divideTestExcepcion() {
		//No se verifica, solo se llama a la ejecucion cuando son excepciones
		aritmetica.divisionEntera(10,0);
	}
	
}
