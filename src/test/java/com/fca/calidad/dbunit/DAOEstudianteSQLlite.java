package com.fca.calidad.dbunit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fca.calidad.curdMOCK.Estudiante;
import com.fca.calidad.curdMOCK.IDAOEstudiante;

public class DAOEstudianteSQLlite implements IDAOEstudiante{

	private static final String DRIVER_NAME = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:C:PONER AQUI SU RUTA\\Alumnos.db";
	private static final String ID= "";
	private static final String PASS= "";
	
	private Connection getConnection() {
		
		try {
			
			return DriverManager.getConnection(DB_URL);
			
		}catch(Exception e){
			
			throw new RuntimeException(e);
		}
	}
		
		private static void close(Connection con) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	@Override
	public int createEstudiante(Estudiante e) {

			Connection conn = null;
			PreparedStatement stmt = null;
			
			int id = 0;
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement("INSERT INTO Estudiante(nombre,apellido,email,carrera) VALUES( ?, ?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			
				stmt.setString(1,e.getNombre());
				stmt.setString(2, e.getApellido());
				stmt.setString(3, e.getEmail());
				stmt.setString(4, e.getCarrera());
				
				int result = stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				
				if (rs.next()) {
					e.setId(rs.getInt(1));
					
					id = rs.getInt(1);
				}
				
				
				
			} catch (SQLException e2) {
				// e.printStackTrace();
				throw new RuntimeException(e2);
			} finally {
				
				close(conn);
			}
       
       
		
		
		return id;
	}

	@Override
	public boolean deleteEstudiante(int id) {
		
		Connection conn = getConnection();
		boolean result = false;

		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement("Delete from Estudiante WHERE id = ?");
			// Set the values to match in the ? on query
			
			String aux = Integer.toString(id);
			
			preparedStatement.setString(1, aux);

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			System.out.println("\n");
			System.out.println("Estudiante eliminado con exito");
			System.out.println(">> Return: " + result + "\n");
			// Close connection with the database
			conn.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
		
	}

	@Override
	public boolean updateEmailEstudiante(Estudiante e) {
		
		Connection conn = getConnection();
		boolean result = false;

		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement("UPDATE Estudiante SET email = ? WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, e.getEmail());
			
			String aux = Integer.toString(e.getId());
			
			preparedStatement.setString(2, aux);

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			System.out.println("\n");
			System.out.println("Correo de alumno con ID: " + aux + " actualizado");
			System.out.println(">> Return: " + result + "\n");
			// Close connection with the database
			conn.close();
			preparedStatement.close();

		} catch (Exception e2) {
			System.out.println(e2);
		}
		// Return statement
		return result;
		
		
	}

	@Override
	public Estudiante findEstudiante(int id) {
		
		Connection conn = getConnection();
		PreparedStatement preparedStatement;
		ResultSet rs;
		Estudiante retrieved = null;

		try {
			// Declare statement query to run
			preparedStatement = conn.prepareStatement("SELECT * from Estudiante WHERE id = ?");
			// Set the values to match in the ? on query
	
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();

			// Obtain the pointer to the data in generated table
			rs.next();

			int retrivedId = rs.getInt(1);
			String retrivedName = rs.getString(2);
			String retrivedLastname= rs.getString(3);
			String retrivedEmail = rs.getString(4);
			String retrivedCareer = rs.getString(5);
			
			//System.out.println(retrivedId);
			//System.out.println(retrivedName);
			//System.out.println(retrivedLastname);
			//System.out.println(retrivedEmail);
			//System.out.println(retrivedCareer);
			

			retrieved = new Estudiante(retrivedId, retrivedName,retrivedLastname,retrivedEmail, retrivedCareer);
			

			// Close connection with the database
			conn.close();
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return retrieved;	
	
	}
	
}
