package com.fca.calidad.dbunit;

public class DBDriver {

	public static void main(String[] args) {
	     Estudiante e = new Estudiante("nombre","apellido","email","carrera");
	     Estudiante e2 = new Estudiante(2,"nombre","apellido","email2","carrera");
	     
	     DAOEstudianteSQLlite dao =new  DAOEstudianteSQLlite();
	     
	     dao.createEstudiante(e);
	     //dao.deleteEstudiante(2);
	     //dao.updateEmailEstudiante(e2);
	     //dao.findEstudiante(2);

		}

}
