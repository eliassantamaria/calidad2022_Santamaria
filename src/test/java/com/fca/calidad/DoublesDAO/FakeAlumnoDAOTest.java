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
	
	//Realizamos la prueba de agregar a la base de datos.
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
	
	}//Terminar el test de agregar a la base de datos.

	
	
	//Realizamos el teste de buscar
	public void bucarTest() {
		when(DAO.searchAlumno(any(String.class))).thenAnswer(new Answer<Alumno>() {
			public Alumno answer(InvocationOnMock invocation) throws Throwable{
				String id = (String) invocation.getArguments()[0];
				Alumno a = baseDatos.get(id);
				return a;
			}
		});
		// Añadimos a la base de datos los datos que posteriormente buscaremos
		Alumno a = new Alumno("nombre", "1", "email", 21);
		baseDatos.put("1", a);

		// Llamamos al metodo de buscar
		Alumno res = DAO.searchAlumno("1");
		
		// Realizamos verificación
		String nomEsperado = res.getNombre();
		String idEsperado = res.getId();
		int edadEsperado = res.getEdad();
		String emailEsperado = res.getEmail();

		String nomEjecucion = baseDatos.get("1").getNombre();
		String idEjecucion = baseDatos.get("1").getId();
		int edadEjecucion = baseDatos.get("1").getEdad();
		String emailEjecucion = baseDatos.get("1").getEmail();
		
		assertThat(nomEjecucion,is(nomEsperado));
		assertThat(idEjecucion,is(idEsperado));
		assertThat(edadEjecucion,is(edadEsperado));
		assertThat(emailEjecucion,is(emailEsperado));


		System.out.println("Resultado searchAlumno: " + res);
		
	}//Terminamos el test de buscar
	
	
	//Realizamos la prueba de eliminar a la base de datos.
	@Test
	public void eliminarTest() {
		when(DAO.deleteAlumno(any(Alumno.class))).then(new Answer<Boolean>() {
			public Boolean answer (InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				Alumno alumno = new Alumno ("nombre","id","email",14);
				baseDatos.remove("1",alumno);
				System.out.println("Tamaño despues ="+baseDatos.size()+ "\n");
				return false;
			}
		}
				
		);
		
		Alumno a = new Alumno ("nombre","id","email",14);
		int sizeBefores = baseDatos.size();
		Boolean res = DAO.deleteAlumno(a);
		int sizeAfter = baseDatos.size();
		
		assertThat(sizeAfter,is(sizeBefores));
	
	}//Termina la parte de eliminación en la base de datos
	
	
	//Realizamos la prueba para editar la varible email
	@Test
	public void updateEmailTest() {
		when(DAO.updateEmail(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				baseDatos.put(arg.getId(),arg);
				return true;
			}
		});
		// Hacemos uso del metodo
		Alumno a = new Alumno("nombre", "id", "email", 21);
		baseDatos.put("1", a);
		String nuevoCorreo = "nuevoCorreo";
		a.setEmail(nuevoCorreo);
		Boolean res = DAO.updateEmail(a);
		String valorEsperado = nuevoCorreo;
		String valorEjecucion = baseDatos.get("1").getEmail();
		assertThat(valorEsperado,is(valorEjecucion));
		System.out.println("Resultado updateEmail: " + res+"\n");
	}
	//Terminamos la prueba de editar el email.
	
	
	/*@After
	public void tearDown() throws Exception {
	}

	//@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}
*/
}
