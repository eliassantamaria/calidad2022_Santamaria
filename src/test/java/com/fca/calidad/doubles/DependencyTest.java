package com.fca.calidad.doubles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DependencyTest {
	
	Dependency dependency;
	
	@Before
	public void setUp() throws Exception {
		dependency = Mockito.mock(Dependency.class);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void test() {
	assertThat(dependency.getClassName(),is(nullValue()));
	}

	private void setBehavior() {
		when (dependency.getClassName()).thenReturn("Nombre de la clase");
	}
	
	@Test
	public void getClassNameTest() {
		setBehavior();
		String resultadoEsperado = "Nombre de la clase";
		String resultadoEjecucion = dependency.getClassName();
		assertThat(resultadoEjecucion,is(resultadoEsperado));
	}
	
	//thenThrow
	@Test
	public void addTwoTest() {
		int expectResult = 2;
		int expectResult2 = 5;
		when(dependency.addTwo(0)).thenReturn(2);
		int runningResult = dependency.addTwo(2);
		int runningResult2 = dependency.addTwo(5);
		//Verificamos
		//assertThat(expectResult,is(runningResult));
		//assertThat(expectResult2,is(runningResult2));
	}
	
	@Test
	public void addTwoTest2() {
		int expectResult = 2;
		int expectResult2 = 2;
		when(dependency.addTwo(anyInt())).thenReturn(2);
		int runningResult = dependency.addTwo(7);
		int runningResult2 = dependency.addTwo(56);
		
		assertThat(expectResult,is(runningResult));
		assertThat(expectResult2,is(runningResult2));
	}
	
	@Test(expected = ArithmeticException.class)
	public void mockException() {
		//No se verifica, solo se llama a la ejecucion cuando son excepciones
		when (dependency.getClassName()).thenThrow(ArithmeticException.class);
		dependency.getClassName();
	}
	
	@Test
	public void mockArgument_Test() {
		int expectResult = 2;
		int expectResult2 = 5;
		when(dependency.addTwo(anyInt())).thenCallRealMethod();
		int runningResult = dependency.addTwo(45);
		int runningResult2 = dependency.addTwo(56);
		
		//assertThat(expectResult,is(runningResult));
		//assertThat(expectResult2,is(runningResult2));
	}
	
	@Test
	public void mockAnswer_Test2() {
		when (dependency.addTwo(anyInt())).thenAnswer(
				new Answer<Integer>() {
					
					public Integer answer(InvocationOnMock invocation) throws Throwable {
						
						int arg = (Integer) invocation.getArguments()[0];
						
						return arg + 10;
					}
				}
		
			);
		//assertThat(expectResult,is(runningResult));
		assertThat(20,is(dependency.addTwo(10)));
		assertThat(49,is(dependency.addTwo(39)));
		
	}
}
