package com.example.app8semanas;

import com.example.app8semanas.Datos.OperacionesBD;
import com.example.app8semanas.Model.Companero;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.telephony.SmsManager;

public class MainCompanero extends Activity implements OnClickListener
{
	private EditText nom;
	private EditText ape;
	private EditText tel;
	private Button btnContinuar;
	String ced;
	long aux;
	
	protected void onCreate(Bundle savedInstanceState) 
	{			
		super.onCreate(savedInstanceState);
		setContentView(R.layout.companero);	
		
		ced = getIntent().getStringExtra("cedula");	
		
		nom=(EditText)findViewById(R.id.edtnombre);	
		ape=(EditText)findViewById(R.id.edtapellido);	
		tel=(EditText)findViewById(R.id.edttelefono);
		btnContinuar=(Button)findViewById(R.id.continuar);
		btnContinuar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			
		case R.id.continuar:
			if( nom.getText().toString().length() == 0 ) {
				nom.setError( "¡El nombre del compañero es requerido!" );
				break;
			}
			if( ape.getText().toString().length() == 0 ) {
				ape.setError( "¡El apellido del compañero es requerido!" );
				break;
			}
			if( tel.getText().toString().length() == 0 ) {
				tel.setError( "¡El número del compañero es requerido!" );
				break;
			}
			if( isNumeric(tel.getText().toString()) == false ) {
				tel.setError( "El teléfono debe de ser un valor numérico (sin puntos, comas o espacios)" );
				break;
			}
			OperacionesBD crear=new OperacionesBD(this);
			String nom1=nom.getText().toString();
			String ape1=ape.getText().toString();
			long tel1=Long.valueOf(tel.getText().toString());
			aux=tel1;
			Companero objcomp=new Companero(nom1,ape1,tel1,ced);
			crear.InsertarCompanero(objcomp.nombre, objcomp.apellido, objcomp.telefono, objcomp.cedula);
			limpiar();
			Toast.makeText(MainCompanero.this, "Felicitaciones  Usted guardo con exito su compañero en el programa de las 8 semanas", Toast.LENGTH_SHORT).show();
			crear.close();
			String num=String.valueOf(aux);
			sendSMS(num,"Hola, te invito a participar como mi compañero en el programa de las 8 semanas");
			Intent intent=new Intent(this, MainCalendario.class);
			intent.putExtra("numero", num);
			startActivity(intent);
		}	
	}
	
	private void sendSMS(String phoneNumber, String message)
	{
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}
	
	public void limpiar()
	{
		nom.setText("");		
		ape.setText("");		
		tel.setText("");		
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
