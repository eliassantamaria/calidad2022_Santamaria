package com.fca.calidad.DoublesDAO;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FakeAlumnoDAOTest {
	
	private FakeAlumnoDAO DAO;
	public HashMap<String, Alumno> baseDatos;
	
	@Before
	public void setUp() throws Exception {}{
		DAO = Mockito.mock(FakeAlumnoDAO.class);
		baseDatos = new HashMap<String, Alumno>();
	}

	@Test
	public void agregarTest() {
		
		when(DAO.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				//Agregar a la base de datos
				baseDatos.put("1",arg);
				System.out.println("Tamaño despues =" +baseDatos.size() + "\n");
				return true;
			}
		}
		
	);
	Alumno a = new Alumno("nombre","id","email",14);
	int sizeBefore = baseDatos.size();
	Boolean res = DAO.addAlumno(a);
	int sizeAfter = baseDatos.size();
	
	assertThat(sizeAfter,is(sizeBefore+1));
		
	}

	
	@After
	public void tearDown() throws Exception {
	}

	//@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}

}
