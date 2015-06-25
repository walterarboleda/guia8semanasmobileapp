package com.example.app8semanas;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.app8semanas.Datos.OperacionesBD;
import com.example.app8semanas.Model.Antropometria;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainAntropometrico extends Activity implements OnClickListener
{
	private EditText fecha;
	private EditText peso;
	private EditText est;
	private EditText abs;
	private Button btnContinuar;
	String ced;
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.antropometrico);
		
		ced = getIntent().getStringExtra("cedula");
		
		fecha=(EditText)findViewById(R.id.edtdate);		
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			fecha.setText(formato.format(new Date()));
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		peso=(EditText)findViewById(R.id.edtpeso);	
		est=(EditText)findViewById(R.id.edtestatura);
		abs=(EditText)findViewById(R.id.edtabd);
		btnContinuar=(Button)findViewById(R.id.continuar);
		btnContinuar.setOnClickListener(this);
	}
	
	

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			
		case R.id.continuar:
			if( fecha.getText().toString().length() == 0 ) {
				fecha.setError( "¡La fecha es requerida!" );
				break;
			}
			if( peso.getText().toString().length() == 0 ) {
				peso.setError( "¡El peso es requerido!" );
				break;
			}
			if( isNumeric(peso.getText().toString()) == false ) {
				peso.setError( "El peso debe de ser un valor numérico (sin puntos, comas, espacios o letras)" );
				break;
			}
			if( est.getText().toString().length() == 0 ) {
				est.setError( "¡La estatura es requerida!" );
				break;
			}
			if( isNumeric(est.getText().toString()) == false ) {
				est.setError( "La estatura debe de ser un valor numérico (sin puntos, comas, espacios o letras)" );
				break;
			}
			if( abs.getText().toString().length() == 0 ) {
				abs.setError( "¡El perimetraje abdominal es requerido!" );
				break;
			}
			if( isNumeric(abs.getText().toString()) == false ) {
				abs.setError( "El perimetraje abdominal debe de ser un valor numérico (sin puntos, comas, espacios o letras)" );
				break;
			}
			OperacionesBD crear=new OperacionesBD(this);
			String date=fecha.getText().toString();
			int edad=Calculos.edad(date);
			int kg=Integer.valueOf(peso.getText().toString());
			int talla=Integer.valueOf(est.getText().toString());			
			double imc=Calculos.IMC(kg, talla);
			int abdomen=Integer.valueOf(abs.getText().toString());
			Antropometria objantro=new Antropometria(edad, kg, talla, imc, abdomen,ced );			
			crear.InsertarAntropometrico(objantro.edad, objantro.peso, objantro.estatura, objantro.imc, objantro.abdomen, objantro.cedula);
			limpiar();
			Toast.makeText(MainAntropometrico.this, "Felicitaciones  Usted ingreso sus datos antropometricos", Toast.LENGTH_SHORT).show();
			crear.close();
			Intent intent=new Intent(this, MainFisiologia.class);
			intent.putExtra("cedula", ced +"");	
			startActivity(intent);
			break;
		}
	}
	
	public void salir(View view)
	{
		finish();
	}
	
	public void limpiar()
	{
		fecha.setText("");
		peso.setText("");
		est.setText("");
		abs.setText("");		
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
}
