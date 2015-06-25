package com.example.app8semanas;

import java.util.ArrayList;
import java.util.List;

import com.example.app8semanas.Datos.OperacionesBD;
import com.example.app8semanas.Model.MetasPersonales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainMetas extends Activity implements OnClickListener
{
	private Spinner sp1;
	private Spinner sp2;
	private EditText meta1;
	private EditText meta2;
	private List<String> lista;
	private List<String> lista2;
	private List<String> lista3;
	private Button btnContinuar;
	OperacionesBD datos;
	String ced;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.metas);		
		
		ced = getIntent().getStringExtra("cedula");		
		lista=new ArrayList<String>();
		sp1=(Spinner)findViewById(R.id.spmt1);
		sp2=(Spinner)findViewById(R.id.spmt2);
		meta1=(EditText)findViewById(R.id.edtmeta2);
		meta2=(EditText)findViewById(R.id.edtmeta1);
		btnContinuar=(Button)findViewById(R.id.continuar);
		btnContinuar.setOnClickListener(this);
		datos=new OperacionesBD(this);
		String consulta="SELECT meta FROM Metas;";
		lista=datos.Metas(consulta);
		ArrayAdapter<String> adpt=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp1.setAdapter(adpt);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3){
				meta1.setText(arg0.getItemAtPosition(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});		
		
		sp2.setAdapter(adpt);
		sp2.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3){
				meta2.setText(arg0.getItemAtPosition(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
	}	
	

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{	
		case R.id.continuar:
			if( meta1.getText().toString().length() == 0 ) {
				meta1.setError( "¡Debes de seleccionar la meta en el desplegable de arriba!" );
				break;
			}
			if( meta2.getText().toString().length() == 0 ) {
				meta2.setError( "¡Debes de seleccionar la meta en el desplegable de arriba!" );
				break;
			}
			String m1=meta1.getText().toString();
			String m2=meta2.getText().toString();
			String consulta1="SELECT id_meta FROM Metas WHERE meta='"+m1+"';";	
			String consulta2="SELECT id_meta FROM Metas WHERE meta='"+m2+"';";
			if (lista.contains(m1))
			{
				lista2=datos.MetasQuery(consulta1);	
			}
			else
			{				
				String sql="INSERT INTO Metas(id_meta, meta) VALUES ('mp1', '"+m1+"');";
				datos.InsertarMeta(sql);
				lista2=datos.MetasQuery(consulta1);
			}	
			String id1=""+lista2.get(0).toString();						
			
			if (lista.contains(m2))
			{
				lista3=datos.MetasQuery(consulta2);	
			}
			else
			{				
				String sql="INSERT INTO Metas(id_meta, meta) VALUES ('mp2', '"+m2+"');";
				datos.InsertarMeta(sql);
				lista3=datos.MetasQuery(consulta2);
			}	
			String id2=lista3.get(0).toString();
			
			MetasPersonales mpersonal=new MetasPersonales(ced, id1, m1);			
			datos.InsertarMetaPersonal( mpersonal.cedula,mpersonal.idmeta, mpersonal.meta);			
			MetasPersonales mpersonal1=new MetasPersonales(ced, id2, m2);			
			datos.InsertarMetaPersonal( mpersonal1.cedula,mpersonal1.idmeta, mpersonal1.meta);
			Toast.makeText(MainMetas.this, "Felicitaciones  Usted ingreso sus metas personales", Toast.LENGTH_SHORT).show();			
			datos.close();
			Intent intent=new Intent(this, MainCompanero.class);
			intent.putExtra("cedula", ced +"");	
			startActivity(intent);
			break;
		}		
	}
	
	public void salir(View view)
	{
		finish();
	}
	
}
