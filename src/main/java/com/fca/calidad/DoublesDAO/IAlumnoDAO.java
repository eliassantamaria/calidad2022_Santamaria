package com.fca.calidad.DoublesDAO;

public interface IAlumnoDAO {
	public Boolean addAlumno(Alumno a);
	public Boolean deleteAlumno(Alumno a);
	public Boolean updateEmail(Alumno a);
	public Alumno searchAlumno(String id);
	
	

}
