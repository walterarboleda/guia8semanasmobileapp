package com.example.app8semanas;

import com.example.app8semanas.R;
import com.example.app8semanas.Datos.OperacionesBD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainFisiologia extends Activity implements OnClickListener {
	private Button btnContinuar;
	private Spinner sEjercicio, sDesayuno, sCena, sDescanso, sFumar, sAlcohol;
	private int ejercicio, desayuno, cena, descanso, fumar, alcohol;
	String ced;
	String fecha;
	OperacionesBD db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fisiologica);	
		ced = getIntent().getStringExtra("cedula");
		btnContinuar=(Button)findViewById(R.id.continuar);
		btnContinuar.setOnClickListener(this);
		
		
	}
	
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.continuar:
			sEjercicio = (Spinner) findViewById(R.id.ejercicio);
			ejercicio = sEjercicio.getSelectedItemPosition();
			sDesayuno = (Spinner) findViewById(R.id.desayuno);
			desayuno = sDesayuno.getSelectedItemPosition();
			sCena = (Spinner) findViewById(R.id.cena);
			cena = sCena.getSelectedItemPosition();
			sDescanso = (Spinner) findViewById(R.id.descanso);
			descanso = sDescanso.getSelectedItemPosition();
			sFumar = (Spinner) findViewById(R.id.fumar);
			fumar = sFumar.getSelectedItemPosition();
			sAlcohol = (Spinner) findViewById(R.id.alcohol);
			alcohol = sAlcohol.getSelectedItemPosition();
			db = new OperacionesBD(getApplicationContext());
			db.insertarEdadFisiologica(ejercicio, desayuno, cena, descanso, fumar, alcohol);
			Toast.makeText(MainFisiologia.this, "Se ha ingresado el cálculo de la edad fisiológica exitosamente", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(this, MainMetas.class);
			intent.putExtra("cedula", ced +"");	
			startActivity(intent);
			break;			
		}
	}	
}
