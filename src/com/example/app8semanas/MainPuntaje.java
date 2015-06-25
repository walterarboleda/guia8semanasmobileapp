package com.example.app8semanas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;


public class MainPuntaje extends Activity
{
	private TabHost th;
	private TextView dia, semana, total;
	private String day, week, all;
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puntaje);
		
		day=getIntent().getStringExtra("dia");
		dia=(TextView)findViewById(R.id.txtpundia);
		dia.setText(day +" puntos");
		week=getIntent().getStringExtra("semana");
		semana=(TextView)findViewById(R.id.txtpunsem);
		semana.setText(week +" puntos");
		all=getIntent().getStringExtra("total");
		total=(TextView)findViewById(R.id.txtpuntotal);
		total.setText(all +" puntos");
		
		th=(TabHost)findViewById(android.R.id.tabhost);		
		th.setup();		
		
		TabSpec ts1=th.newTabSpec("Tab1");
		ts1.setIndicator("X Dia");
		ts1.setContent(R.id.tab1);	
		th.addTab(ts1);
		
		TabSpec ts2=th.newTabSpec("Tab2");
		ts2.setIndicator("X Semana");
		ts2.setContent(R.id.tab2);				
		th.addTab(ts2);
		
		TabSpec ts3=th.newTabSpec("Tab3");
		ts3.setIndicator("Total");
		ts3.setContent(R.id.tab3);		
		th.addTab(ts3);
	}
	
	public void salir(View view)
	{
		finish();
	}
}
