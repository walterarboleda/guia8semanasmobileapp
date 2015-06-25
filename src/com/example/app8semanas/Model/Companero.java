package com.example.app8semanas.Model;

public class Companero 
{
	public int idpartner;
	public String nombre;
	public String apellido;
	public long telefono;
	public String cedula;
	
	public Companero(String nombre, String apellido,
			long telefono, String cedula) {
		super();		
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.cedula = cedula;
	}

	public int getIdpartner() {
		return idpartner;
	}

	public void setIdpartner(int idpartner) {
		this.idpartner = idpartner;
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

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}	
}
