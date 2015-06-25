package com.example.app8semanas;

import com.example.app8semanas.Datos.OperacionesBD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	private Button btnComenzar;	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		btnComenzar=(Button)findViewById(R.id.btnComenzar);
		btnComenzar.setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
		case R.id.btnComenzar:
			OperacionesBD datos=new OperacionesBD(this);
			String sql="SELECT COUNT(*) FROM Participantes;";
			String aux,ced;
			int inicio=datos.Comprobar(sql);
			Intent intent=new Intent();
			if (inicio==0)			
				intent=new Intent(this, MainParticipante.class);			
			else
			{
				sql="SELECT COUNT(*) FROM Antropometricos;";
				String sql1="SELECT Cedula FROM Participantes;";
				inicio=datos.Comprobar(sql);
				int cedula=datos.Comprobar(sql1);
				ced=String.valueOf(cedula);				
				aux=String.valueOf(inicio);				
				if (inicio==0)
				{
					intent=new Intent(this, MainAntropometrico.class);
					intent.putExtra("cedula", ced +"");
				}
				else
				{
					sql="SELECT COUNT(*) FROM EdadFisiologica;";
					inicio=datos.Comprobar(sql);					
					aux=String.valueOf(inicio);					
					if (inicio==0)
						intent=new Intent(this, MainFisiologia.class);
					else
					{
						sql="SELECT COUNT(*) FROM Metas_Personales;";
						inicio=datos.Comprobar(sql);						
						aux=String.valueOf(inicio);						
						if (inicio==0)
						{
							intent=new Intent(this, MainMetas.class);
							intent.putExtra("cedula", ced +"");
						}
						else
						{
							sql="SELECT COUNT(*) FROM Companero;";
							inicio=datos.Comprobar(sql);							
							aux=String.valueOf(inicio);							
							if (inicio==0)
							{
								intent=new Intent(this, MainCompanero.class);
								intent.putExtra("cedula", ced +"");
							}
							else
							{
								intent=new Intent(this, MainCalendario.class);								
							}
						}
					}
				}
			}
			datos.close();
			startActivity(intent);
			break;
		}
	}
	
	
	
}