package com.example.app8semanas.Model;

public class Control
{
	public String id_actividad;
	public String fecha;
	public String semana;
	public int puntos;
	public Control(String id_actividad, int puntos, String semana, String fecha) 
	{		
		this.id_actividad = id_actividad;	
		this.puntos=puntos;
		this.semana=semana; 
		this.fecha = fecha;
		
	}
	public String getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(String id_actividad) {
		this.id_actividad = id_actividad;
	}	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getSemana() {
		return semana;
	}
	public void setSemana(String semana) {
		this.semana = semana;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
