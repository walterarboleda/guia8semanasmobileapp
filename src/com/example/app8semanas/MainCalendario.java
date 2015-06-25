package com.example.app8semanas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.app8semanas.CalendarioView.OnDispatchDateSelectListener;
import com.example.app8semanas.Datos.OperacionesBD;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainCalendario extends Activity implements OnDispatchDateSelectListener, OnClickListener 
{
	OperacionesBD db;
	private TextView mTextDate, mTextSemana;
	private SimpleDateFormat mFormat;
	private Button progreso, puntaje;
	private Button tematica, recomendacion, asignacion, sincronizar;
	private Integer semana;
	OperacionesBD datos;
	OnDispatchDateSelectListener OnDispatchDateSelectListener;
	String numero, fecha, fechaActual, id_semana;
	//Progress Dialog Object
	ProgressDialog prgDialog;
	
	
    @SuppressLint("SimpleDateFormat")
	@Override
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        
        numero=getIntent().getStringExtra("numero");        
        progreso=(Button)findViewById(R.id.registrar_progreso);
		progreso.setOnClickListener(this);
		tematica=(Button)findViewById(R.id.tematica);
		tematica.setOnClickListener(this);
		recomendacion=(Button)findViewById(R.id.recomendacion);
		recomendacion.setOnClickListener(this);
		asignacion=(Button)findViewById(R.id.asignacion);
		asignacion.setOnClickListener(this);
		sincronizar=(Button)findViewById(R.id.sincronizar);
		sincronizar.setOnClickListener(this);
		puntaje=(Button)findViewById(R.id.puntaje);
		puntaje.setOnClickListener(this);
        mTextDate=(TextView)findViewById(R.id.display_date); 
        mTextSemana=(TextView)findViewById(R.id.display_semana); 
        mFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
        datos = new OperacionesBD(getApplicationContext());
		datos.crearCalendario();
		Cursor c2 = datos.select("Calendario");
		if(c2.getCount() > 0) 
		{
            c2.moveToFirst();
            fecha = c2.getString(1);
        }	
        ((CalendarioView)findViewById(R.id.calendar)).setOnDispatchDateSelectListener(this);
        ((CalendarioView)findViewById(R.id.calendar)).fechaHoy(new Date());
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Sincronizando información. Por favor espere un momento...");
        prgDialog.setCancelable(false);
    }
    
    public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.registrar_progreso:
			String consulta="SELECT meta FROM Metas_Personales;";			
			List<String> lista=new ArrayList<String>();			
			lista=datos.MetasQuery(consulta);			
			String meta1=""+lista.get(0).toString();
			String meta2=""+lista.get(1).toString();
			String fecha=mTextDate.getText().toString();			
			String aux=String.valueOf(semana);			
			Intent intent=new Intent(this, MainProgreso.class);
			intent.putExtra("fecha", fecha +"");
			intent.putExtra("semana", aux);
			intent.putExtra("m1", meta1 +"");
			intent.putExtra("m2", meta2 +"");
			intent.putExtra("numero", numero);
			datos.close();
			startActivity(intent);
			break;
		case R.id.tematica:
			id_semana = "Sem" + semana;
			db = new OperacionesBD(getApplicationContext());
			Cursor c = db.select_row("Semanas", "id_semana", id_semana);
			if (c.moveToFirst()) {
				String elemento = c.getString(2);
				showMessage("Temática Semana "+semana, elemento);
				db.close();
				return;
	        	}
			break;
		case R.id.recomendacion:
			id_semana = "Sem" + semana;
			db = new OperacionesBD(getApplicationContext());
			Cursor c2 = db.select_row("Semanas", "id_semana", id_semana);
			if (c2.moveToFirst()) {
				String elemento = c2.getString(4);
				showMessage("Recomendación Semana "+semana, elemento);
				db.close();
				return;
	        	}
			break;
		case R.id.asignacion:
			id_semana = "Sem" + semana;
			db = new OperacionesBD(getApplicationContext());
			Cursor c3 = db.select_row("Semanas", "id_semana", id_semana);
			if (c3.moveToFirst()) {
				String elemento = c3.getString(3);
				showMessage("Asignación Semana "+semana, elemento);
				db.close();
				return;
			}
			break;
		case R.id.sincronizar:
			syncSQLiteMySQLDB();
			break;
		case R.id.puntaje:
			String dia=mTextDate.getText().toString();	
			String pundia="SELECT SUM (puntos) FROM Control WHERE fecha='"+dia+"'";
			String punsem="SELECT SUM (puntos) FROM Control WHERE semana="+semana+"";
			String total="SELECT SUM (puntos) FROM Control";
			int pdia= datos.Comprobar(pundia);
			int psem=datos.Comprobar(punsem);
			int total1=datos.Comprobar(total);
			String auxi=String.valueOf(pdia);			
			String auxi1=String.valueOf(psem);			
			String auxi2=String.valueOf(total1);			
			Intent intent1=new Intent(this, MainPuntaje.class);
			intent1.putExtra("dia", auxi);
			intent1.putExtra("semana", auxi1);
			intent1.putExtra("total", auxi2);
			startActivity(intent1);
			break;			
		}
	}

	@Override
	public void onDispatchDateSelect(Date date) 
	{
		mTextDate.setText(mFormat.format(date));
		try {
			semana = Calculos.semana_actual(date, fecha);
			mTextSemana.setText("Semana " + semana);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void syncSQLiteMySQLDB(){
        //Create AsycHttpClient object
		db = new OperacionesBD(getApplicationContext());
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        prgDialog.show();
        params.put("datosJSON", db.composeJSONfromSQLite().toString());
        db.close();
        client.post("http://192.168.1.61/app8semanasphp/android/insertar", params, new AsyncHttpResponseHandler() {
	        @Override
            public void onSuccess(String response) {
                System.out.println(response);
                prgDialog.hide();
                Toast.makeText(getApplicationContext(), "La sincronización se realizó exitosamente", Toast.LENGTH_LONG).show();
            }
	 
	        @Override
	        public void onFailure(int statusCode, Throwable error,
	            String content) {
	                // TODO Auto-generated method stub
	                prgDialog.hide();
	                if(statusCode == 404){
	                    Toast.makeText(getApplicationContext(), "(Error 404) No se encontró el recurso solicitado", Toast.LENGTH_LONG).show();
	                }else if(statusCode == 500){
	                    Toast.makeText(getApplicationContext(), "(Error 500) Ocurrió un error interno del servidor", Toast.LENGTH_LONG).show();
	                }else{
	                    Toast.makeText(getApplicationContext(), "¡Un error inesperado ocurrió! [Probablemente el dispositivo no se encuentra conectado a internet]", Toast.LENGTH_LONG).show();
	                }
	            }
	    });
    }
	
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
}
