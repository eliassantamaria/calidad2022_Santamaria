package com.fca.calidad.DoublesDAO;

public class Alumno {
	//Declaramos nuestros atributos
	private String nombre;
	private String id;
	private String email;
	private int edad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public Alumno(String nombre, String id, String email, int edad) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.email = email;
		this.edad = edad;
	}

}
