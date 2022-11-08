package com.fca.calidad.dbunit;

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

import com.fca.calidad.curdMOCK.Estudiante;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import com.fca.calidad.curdMOCK.


import junit.framework.TestCase;

public class DaoEstudiantesSqliteTest extends TestCase{

	public IDatabaseConnection connection;
	public DAOEstudianteSQLlite daoSQLite;
	
	public DaoEstudiantesSqliteTest (String name)
	{
		super(name);
	}

	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build
				(new File("src/resources/initDB.xml"));
	}
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		daoSQLite = new DAOEstudianteSQLlite ();
		Connection jdbcConnection;
		
		jdbcConnection = DriverManager.getConnection
				("jdbc:sqlite:/Users/patriciaortegon/Documents/Personales/Mayab/ENERO-MAYO-2022/calidad/parcial2/Alan/CAlidad2022/Alumnos.db");
		
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

	
	@Test
	public void testCrear() {
		Estudiante alumno = new Estudiante ("nombrePruebaCrear","appellidoCrear","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		int numEsperado = 4; //sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			assertThat(numEsperado,is(numReal));			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	@Test
	public void testCrearCompararQuery() {
		Estudiante alumno = new Estudiante ("nombrePruebaCrear","appellidoCrear","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		int numEsperado = 4; //sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			assertThat(numEsperado,is(numReal));			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	@Test
	public void testCrearCompararTabla() {
		//<Estudiante id="3" nombre="nombre1" apellido="apellido1" email="email" carrera = "carrera"/>
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante"); //tabla con los resultados del query
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("src/resources/insert2.xml")); //archivo xml con los datos esperados después de insertar
			 ITable expectedTable = expectedDataSet.getTable("estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);		 //comparamos las tablas
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	@Test
	public void testFindEstudiante() {
		Estudiante alumno;
		alumno = daoSQLite.findEstudiante(0);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
				"estudiante",
				"SELECT * FROM estudiante where id = 0");
			assertThat(alumno.getApellido(), is(actualTable.getValue(0, "apellido")));
			alumno.getCarrera()
			alumno.getNombre()
		}
		
	}
}
