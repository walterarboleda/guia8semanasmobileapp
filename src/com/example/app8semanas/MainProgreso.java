package com.example.app8semanas;

import com.example.app8semanas.Datos.OperacionesBD;
import com.example.app8semanas.Model.Control;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainProgreso extends Activity implements OnClickListener, OnCheckedChangeListener
{
	private TabHost th;
	private TextView dia, meta1, meta2, sem, act;
	private RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7;	
	private ImageButton btns1, btns2, btns3, btns4, btns5, btns6, btns7;
	private Button btnanimar;
	
	String fecha, m1, m2, semana, actividad, numero;
	int punactfis, punfrve, pundor, punm1, punm2, punsem, aux, punpro;
	OperacionesBD datos;
	Control objcontrol;
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progreso);
		
		fecha = getIntent().getStringExtra("fecha");
		dia=(TextView)findViewById(R.id.txt2);
		dia.setText(fecha);
		semana=getIntent().getStringExtra("semana");
		sem=(TextView)findViewById(R.id.txt1);
		sem.setText("Semana "+semana);
		m1=getIntent().getStringExtra("m1");
		meta1=(TextView)findViewById(R.id.txtm1);
		meta1.setText(m1);
		m2=getIntent().getStringExtra("m2");
		meta2=(TextView)findViewById(R.id.txtm2);
		meta2.setText(m2);
		aux=Integer.parseInt(semana);
		actividad=Calculos.actividad(aux);
		act=(TextView)findViewById(R.id.txtsem);
		act.setText(actividad);
		numero=getIntent().getStringExtra("numero");
		
		rg1=(RadioGroup)findViewById(R.id.rgactfis);
		rg2=(RadioGroup)findViewById(R.id.rgfruveg);
		rg3=(RadioGroup)findViewById(R.id.rgdormir);
		rg4=(RadioGroup)findViewById(R.id.rgm1);
		rg5=(RadioGroup)findViewById(R.id.rgm2);
		rg6=(RadioGroup)findViewById(R.id.rgsem);
		rg7=(RadioGroup)findViewById(R.id.rgpro);
		rg1.setOnCheckedChangeListener(this);
		rg2.setOnCheckedChangeListener(this);
		rg3.setOnCheckedChangeListener(this);
		rg4.setOnCheckedChangeListener(this);
		rg5.setOnCheckedChangeListener(this);
		rg6.setOnCheckedChangeListener(this);
		rg7.setOnCheckedChangeListener(this);
		
		btns1=(ImageButton)findViewById(R.id.btnsave);
		btns2=(ImageButton)findViewById(R.id.btnsave1);
		btns3=(ImageButton)findViewById(R.id.btnsave2);
		btns4=(ImageButton)findViewById(R.id.btnsave3);
		btns5=(ImageButton)findViewById(R.id.btnsave4);
		btns6=(ImageButton)findViewById(R.id.btnsave5);
		btns7=(ImageButton)findViewById(R.id.btnsave6);
		btnanimar=(Button)findViewById(R.id.btnanimar);
		btns1.setOnClickListener(this);
		btns2.setOnClickListener(this);
		btns3.setOnClickListener(this);
		btns4.setOnClickListener(this);
		btns5.setOnClickListener(this);
		btns6.setOnClickListener(this);
		btns7.setOnClickListener(this);
		btnanimar.setOnClickListener(this);
		datos=new OperacionesBD(this);		
		
		th=(TabHost)findViewById(android.R.id.tabhost);		
		th.setup();		
		
		TabSpec ts1=th.newTabSpec("Tab1");
		ts1.setIndicator("1");
		ts1.setContent(R.id.tab1);	
		th.addTab(ts1);
		
		TabSpec ts2=th.newTabSpec("Tab2");
		ts2.setIndicator("2");
		ts2.setContent(R.id.tab2);				
		th.addTab(ts2);
		
		TabSpec ts3=th.newTabSpec("Tab3");
		ts3.setIndicator("3");
		ts3.setContent(R.id.tab3);		
		th.addTab(ts3);
		
		TabSpec ts4=th.newTabSpec("Tab4");
		ts4.setIndicator("4");
		ts4.setContent(R.id.tab4);		
		th.addTab(ts4);
		
		TabSpec ts5=th.newTabSpec("Tab5");
		ts5.setIndicator("5");
		ts5.setContent(R.id.tab5);		
		th.addTab(ts5);
		
		TabSpec ts6=th.newTabSpec("Tab6");
		ts6.setIndicator("6");
		ts6.setContent(R.id.tab6);		
		th.addTab(ts6);
		
		TabSpec ts7=th.newTabSpec("Tab7");
		ts7.setIndicator("7");
		ts7.setContent(R.id.tab7);		
		th.addTab(ts7);	
	}
	
	public void salir(View view)
	{
		finish();
	}
	
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
	public void onCheckedChanged(RadioGroup group, int checkedId) 
	{
		switch(checkedId)
		{
		case R.id.rbaf1:
			punactfis=2;
			break;
		case R.id.rbaf2:
			punactfis=4;
			break;
		case R.id.rbaf3:
			punactfis=6;
			break;
		case R.id.rbaf4:
			punactfis=8;
			break;
		}
		switch(checkedId)
		{
		case R.id.rbfv1:
			punfrve=3;
			break;
		case R.id.rbfv2:
			punfrve=0;
			break;
		}
		switch (checkedId)
		{
		case R.id.rbd1:
			pundor=3;
			break;
		case R.id.rbd2:
			pundor=0;
			break;
		}
		switch (checkedId)
		{
		case R.id.rbm11:
			punm1=3;
			break;
		case R.id.rbm12:
			punm1=0;
			break;
		}
		switch (checkedId)
		{
		case R.id.rbm21:
			punm2=3;
			break;
		case R.id.rbm22:
			punm2=0;
			break;
		}
		switch (checkedId)
		{
		case R.id.rbsem1:
			punsem=4;
			break;
		case R.id.rbsem2:
			punsem=0;
			break;
		}
		switch (checkedId)
		{
		case R.id.rbpro1:
			punpro=1;
			break;
		case R.id.rbpro2:
			punpro=0;
			break;
		}
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId())
		{
		case R.id.btnsave:
			String  query="UPDATE Actividades SET puntos="+punactfis+" ,total_puntos=total_puntos +"+punactfis+" WHERE id_actividad='Act1';";
			String consulta="SELECT COUNT(*) FROM Control;";
			String consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act1' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act1", punactfis, semana, fecha);
			int res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (punactfis==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
					
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (punactfis==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro su actividad fisica hoy", Toast.LENGTH_SHORT).show();
			}		
			break;
			
		case R.id.btnsave1:
			query="UPDATE Actividades SET puntos="+punfrve+" ,total_puntos=total_puntos +"+punfrve+" WHERE id_actividad='Act2';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act2' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act2", punfrve, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (punfrve==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (punfrve==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro su consumo de frutas y verduras hoy", Toast.LENGTH_SHORT).show();
			}		
			break;
			
		case R.id.btnsave2:
			query="UPDATE Actividades SET puntos="+pundor+" ,total_puntos=total_puntos +"+pundor+" WHERE id_actividad='Act3';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act3' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act3", pundor, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (pundor==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();	
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (pundor==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro su tiempo de sueño", Toast.LENGTH_SHORT).show();
			}		
			break;	
			
		case R.id.btnsave3:
			query="UPDATE Actividades SET puntos="+punm1+" ,total_puntos=total_puntos +"+punm1+" WHERE id_actividad='Act4';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act4' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act4", punm1, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (punm1==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (punm1==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro ya realizo su meta 1", Toast.LENGTH_SHORT).show();
			}		
			break;	
			
		case R.id.btnsave4:
			query="UPDATE Actividades SET puntos="+punm2+" ,total_puntos=total_puntos +"+punm2+" WHERE id_actividad='Act5';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act5' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act5", punm2, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (punm2==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (punm2==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro su tiempo de sueño", Toast.LENGTH_SHORT).show();
			}		
			break;
			
		case R.id.btnsave5:
			String id=Calculos.id(aux);
			query="UPDATE Actividades SET puntos="+punsem+" ,total_puntos=total_puntos +"+punsem+" WHERE id_actividad='"+id+"';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='"+id+"' AND fecha='"+fecha+"'";
			objcontrol=new Control(id, punsem, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (punsem==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (punsem==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro su actividad semanal", Toast.LENGTH_SHORT).show();
			}		
			break;
			
		case R.id.btnsave6:
			query="UPDATE Actividades SET puntos="+punpro+" ,total_puntos=total_puntos +"+punpro+" WHERE id_actividad='Act15';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act15' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act15", punpro, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
				if (punpro==0)
				{
					Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);
					if (punpro==0)
					{
						Toast.makeText(MainProgreso.this, "Animo, mañana es otro dia, has un mejor esfuerzo", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(MainProgreso.this, "Bien hecho, sigue asi", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya registro su actividad especial hoy", Toast.LENGTH_SHORT).show();
			}		
			break;
			
		case R.id.btnanimar:
			sendSMS(numero,"Hola, Animo, continua con el programa, se que puedes");
			int punsms=1;
			query="UPDATE Actividades SET puntos="+punsms+" ,total_puntos=total_puntos +"+punsms+" WHERE id_actividad='Act14';";
			consulta="SELECT COUNT(*) FROM Control;";
			consulta2="SELECT COUNT(*) FROM Control WHERE id_actividad='Act14' AND fecha='"+fecha+"'";
			objcontrol=new Control("Act14", punsms, semana, fecha);
			res=datos.Comprobar(consulta);			
			if (res==0)
			{
				datos.Actualizar(query);
				datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);				
			}
			else
			{
				res=datos.Comprobar(consulta2);
				if(res==0)
				{
					datos.Actualizar(query);
					datos.InsertarControl(objcontrol.id_actividad, objcontrol.puntos, objcontrol.semana, objcontrol.fecha);					
				}
				else
					Toast.makeText(MainProgreso.this, "Usted ya animo a su compañero", Toast.LENGTH_SHORT).show();
			}		
			break;
			
		}
	}
	
	private void sendSMS(String phoneNumber, String message)
	{
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}
}
