package com.example.app8semanas;

import com.example.app8semanas.Datos.OperacionesBD;
import com.example.app8semanas.Model.Participante;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainParticipante extends Activity implements OnClickListener
{
	private EditText nom;
	private EditText ape;
	private EditText ced;
	private EditText mail;
	private EditText tel;
	private EditText ali;
	private Button btnContinuar;
	public boolean control=false;
	public String aux;	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{			
		super.onCreate(savedInstanceState);
		setContentView(R.layout.participante);	
		
		nom=(EditText)findViewById(R.id.edtnombre);	
		ape=(EditText)findViewById(R.id.edtapellido);	
		ced=(EditText)findViewById(R.id.edtcedula);	
		mail=(EditText)findViewById(R.id.edtmail);	
		tel=(EditText)findViewById(R.id.edttelefono);
		ali=(EditText)findViewById(R.id.edtalias);
		btnContinuar=(Button)findViewById(R.id.continuar);
		btnContinuar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			
		case R.id.continuar:
			if( ced.getText().toString().length() == 0 ) {
				ced.setError( "¡La cédula es requerida!" );
				break;
			}
			if( isNumeric(ced.getText().toString()) == false ) {
				ced.setError( "La cédula debe de ser un valor numérico (sin puntos ni comas)" );
				break;
			}
			
			if( nom.getText().toString().length() == 0 ) {
				nom.setError( "¡El nombre es requerido!" );
				break;
			}
			if( mail.getText().toString().length() == 0 ) {
				mail.setError( "¡El alias es requerido!" );
				break;
			}
			if( ape.getText().toString().length() == 0 ) {
				ape.setError( "¡El apellido es requerido!" );
				break;
			}
			if( tel.getText().toString().length() == 0 ) {
				tel.setError( "¡El teléfono es requerido!" );
				break;
			}
			if( isNumeric(tel.getText().toString()) == false ) {
				tel.setError( "El teléfono debe de ser un valor numérico (sin puntos, comas o espacios)" );
				break;
			}
			if( mail.getText().toString().length() == 0 ) {
				mail.setError( "¡El email es requerido!" );
				break;
			}
			OperacionesBD crear=new OperacionesBD(this);
			String nom1=nom.getText().toString();
			String ape1=ape.getText().toString();
			String ced1=ced.getText().toString();
			aux=ced1;
			String mail1=mail.getText().toString();
			String ali1=ali.getText().toString();
			long tel1=Long.valueOf(tel.getText().toString());
			Participante objpart=new Participante(ced1,nom1,ape1,ali1,mail1,tel1);
			crear.InsertarParticipante(objpart.cedula, objpart.nombre, objpart.apellido, objpart.alias, objpart.telefono, objpart.mail);
			Toast.makeText(MainParticipante.this, "Felicitaciones "+objpart.nombre+" Usted hace parte del programa de las 8 semanas", Toast.LENGTH_SHORT).show();
			control=true;
			crear.close();
			Intent intent=new Intent(this, MainAntropometrico.class);
			intent.putExtra("cedula", aux +"");			
			startActivity(intent);
			break;
		}
	}
	
	public void limpiar()
	{
		nom.setText("");
		ced.setText("");
		ape.setText("");
		mail.setText("");
		tel.setText("");
		ali.setText("");
	}
	
	public void salir(View view)
	{
		finish();
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
