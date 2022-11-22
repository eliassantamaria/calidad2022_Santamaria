package com.fca.calidad.dbunit;

//Inicio de exportaciones para el funcionamiento
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
//import com.anahuac.calidad.curdMOCK.*;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)


//Iniicio de la clase de los test.
public class DaoEstudiantesSqliteTest extends TestCase{

	
	public IDatabaseConnection connection;
	public DAOEstudianteSQLlite daoSQLite;
	
	public DaoEstudiantesSqliteTest(String name)
	{
		super(name);
	}

	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build
				(new File("./src/resources/initDB.xml"));
	}
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		daoSQLite = new DAOEstudianteSQLlite ();
		Connection jdbcConnection;
		
		jdbcConnection = DriverManager.getConnection
				("jdbc:sqlite:.\\src\\resources\\Alumnos.db");
		
		connection = new DatabaseConnection(jdbcConnection);
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = jdbcConnection.prepareStatement("Delete from sqlite_sequence WHERE name = ?");
			// Set the values to match in the ? on query
			
			
			preparedStatement.setString(1, "Estudiante");
			
			Boolean result = false;

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			preparedStatement.close();
			
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch(Exception e) {
			fail("Error in setup: " + e.getMessage());
			connection.close();
		} 
	}
	
	@After
	public void tearDown()
	{
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IDatabaseConnection getConnection()  {
		return connection;
	}
	
	
	
	
	
	//Test de la creación de un nuevo alumnos en nuestra base de datos.
	@Test
	public void testAddAlumno() {
		Estudiante alumno = new Estudiante ("alumnoElias","alumnoApSantamaria","Elias@email004.com" ,"carreraLATI");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//Verificamos
		int numEsperado = 4; //-->sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			assertThat(numEsperado,is(numReal));	
			System.out.println("Se agrego al usuario alumnoElias"+ "\n");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	//Finaliza la prueba de agregar a un nuevo usuario en la base de datos.
	
	
	
	//Inicio de la prueba de buscar un nuevo usuario.
	@Test
	 public void testSearchAlumno()  {
		Estudiante alumno = daoSQLite.findEstudiante(0);
		
		
		try {
			String tabla = "Estudiante";
			String sentencia = "SELECT * FROM estudiante where id = 0";
			ITable actualTable = getConnection().createQueryTable(tabla,sentencia); 
			assertThat(alumno.getNombre(), is (actualTable.getValue(0, "nombre")));
			assertThat(alumno.getApellido(), is (actualTable.getValue(0, "apellido")));
			assertThat(alumno.getEmail(), is (actualTable.getValue(0, "email")));
			assertThat(alumno.getCarrera(), is (actualTable.getValue(0, "carrera")));
		
			//Realizamos una impresión en consola a modo de validación.
			System.out.println("Busqueda realizada = Nombre del encontrado Estudiante: " + actualTable.getValue(0, "nombre")+ "\n");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
		}
		//Finaliza la prueba de busqueda
		
	
	
	//Iniciamos el test de actualizar/editar algún dato de algún alumno en nuestro caso cambiaremos el nombre y el email de un alumno.
	@Test
	public void testUpdateAlumno() throws Exception{
		
		Estudiante Estudiante2 = daoSQLite.findEstudiante(0);
		Estudiante2.setNombre("EliasEditado");
		daoSQLite.updateNombreEstudiante(Estudiante2);
		Estudiante2.setEmail("Elias@EDITADO.com");
		daoSQLite.updateEmailEstudiante(Estudiante2);
		
		
		try {
			//Declaramos variables que nos sirven para ejecutar el Query
			String tabla = "Estudiante";
			String sentencia = "SELECT * FROM estudiante where id = 0";
			
			ITable actualTable = getConnection().createQueryTable(tabla,sentencia);
			
			//Se hace la comparacion de cada campo para determinar 
			assertThat(Estudiante2.getNombre(), is(actualTable.getValue(0, "nombre")));
			assertThat(Estudiante2.getApellido(), is(actualTable.getValue(0, "apellido")));			
			assertThat(Estudiante2.getEmail(), is(actualTable.getValue(0, "email")));			
			assertThat(Estudiante2.getCarrera(), is(actualTable.getValue(0, "carrera")));
	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	//Finalizamos nuestro test de Actualizar/editar los datos de nombre y email de un alumno. En este caso el de id = 0
	
	
	
	//Iniciamos nuestro test de eliminar a un alumno.
	@Test
	public void testDeleteAlumno() throws Exception{
		
		Estudiante Estudiante = daoSQLite.findEstudiante(0);
		int idAlumno = Estudiante.getId();
		daoSQLite.deleteEstudiante(idAlumno);
		
		//Verificamos con un Try y catch para atrapar cualquier error.
		try {
			String tabla = "Estudiante";
			String sentencia = "SELECT * FROM estudiante where id = 0";
			int result = 0;
	
			ITable actualTable = getConnection().createQueryTable(tabla,sentencia);
			
			assertThat(actualTable.getRowCount(), is(result));
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	//Finalizamos nuestra prueba de elimnar al alumno.
	
	
}

