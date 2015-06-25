package com.example.app8semanas;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

@SuppressLint("SimpleDateFormat")
public class Calculos 
{
	public static int edad(String fecha)
	{
		Date nacimiento=null;
		try 
		{
			nacimiento=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Calendar objnacimiento=Calendar.getInstance();
		Calendar fechaactual=Calendar.getInstance();
		objnacimiento.setTime(nacimiento);
		int ano=fechaactual.get(Calendar.YEAR)-objnacimiento.get(Calendar.YEAR);
		int mes=fechaactual.get(Calendar.MONTH)-objnacimiento.get(Calendar.MONTH);
		int dia=fechaactual.get(Calendar.DATE)-objnacimiento.get(Calendar.DATE);
		if (mes<0||mes==0 && dia<0)
		{
			ano--;
		}
		return ano;
	}
	
	public static double IMC(int peso, double estatura)
	{
		double imc=0;
		estatura=estatura/100;
		estatura=Math.pow(estatura, 2);
		imc=peso/estatura;
		return imc;
	}
	
	public static int semana_actual(Date fecha, String fechaInicio) throws ParseException
	{
		Date fechaActual = fecha;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicioCalendario = format.parse(fechaInicio);
		int dias = ((Days.daysBetween(new DateTime(fechaActual), new DateTime(fechaInicioCalendario)).getDays() / 7) * -1) + 1;
		return dias;
	}
	
	public static String actividad(int semana)
	{
		String actividad="";
		switch (semana)
		{
		case 1:
			actividad="Cumplir las buenas practicas de salud";
			break;
		case 2:
			actividad="Hacer ejercicios de fuerza y estiramiento ";
			break;
		case 3:
			actividad="Consumir poca grasa";
			break;
		case 4:
			actividad="Consumir bastante fibra";
			break;
		case 5:
			actividad="Llevar una alimentacion sana";
			break;
		case 6:
			actividad="Controlar sus dependencias";
			break;
		case 7:
			actividad="Manejar el estres";
			break;
		case 8:
			actividad="Hacerse examenes de prevencion y cuidar su seguridad ";
			break;			
		}
		return actividad;
	}
	
	public static String id(int semana)
	{
		String id="";
		switch (semana)
		{
		case 1:
			id="Act6";
			break;
		case 2:
			id="Act7";
			break;
		case 3:
			id="Act8";
			break;
		case 4:
			id="Act9";
			break;
		case 5:
			id="Act10";
			break;
		case 6:
			id="Act11";
			break;
		case 7:
			id="Act12";
			break;
		case 8:
			id="Act13";
			break;			
		}
		return id;
	}
}
