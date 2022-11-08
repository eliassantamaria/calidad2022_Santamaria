package com.fca.calidad.curdMOCK;

public interface IDAOEstudiante {
	
	public int createEstudiante(Estudiante e);
	
	
	
	public boolean deleteEstudiante(int id);
			
		
		
	public boolean updateEmailEstudiante(Estudiante e);
			
		
		
	public Estudiante findEstudiante(int id); 

}
