package com.example.app8semanas.Model;

public class Antropometria 
{
	public int iddato;
	public int edad;
	public int peso;
	public int estatura;
	public double imc;
	public int abdomen;	
	public String cedula;
	
	public Antropometria(int edad, int peso, int estatura, double imc,
			int abdomen, String cedula) {
		super();		
		this.edad = edad;
		this.peso = peso;
		this.estatura = estatura;
		this.imc = imc;
		this.abdomen = abdomen;	
		this.cedula=cedula;
	}

	public int getIddato() {
		return iddato;
	}

	public void setIddato(int iddato) {
		this.iddato = iddato;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public int getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(int abdomen) {
		this.abdomen = abdomen;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}	
	
	
}
