package com.fca.calidad.dbunit;

public class Estudiante {

	private int id = 0;
	private String nombre = "";
	private String apellido = "";
	private String email = "";
	private String carrera = "";
	
	public Estudiante(int id, String nombre,String apellido, String email, String carrera){
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.carrera = carrera;
		
	}
	
	public Estudiante (String nombre,String apellido, String email, String carrera) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.carrera = carrera;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
}
