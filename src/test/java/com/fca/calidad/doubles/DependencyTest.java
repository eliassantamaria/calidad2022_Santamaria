package com.fca.calidad.doubles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
	//assertThatdependency.getClassName()
	}

}
