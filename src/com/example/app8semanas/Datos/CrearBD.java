package com.example.app8semanas.Datos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class CrearBD extends SQLiteOpenHelper
{
	private static final String name ="ocho_semanas_db.sqlite";
	private static int version=1;
	Context aux;
	SQLiteDatabase dbaux;
	
	public CrearBD(Context context) 
	{
		super(context, name, null, version);
		aux=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		dbaux=db;
		db.execSQL(OperacionesBD.sentencia1);
		db.execSQL(OperacionesBD.sentencia2);
		db.execSQL(OperacionesBD.sentencia3);
		db.execSQL(OperacionesBD.sentencia4);
		db.execSQL(OperacionesBD.sentencia5);
		db.execSQL(OperacionesBD.sentencia6);	
		db.execSQL(OperacionesBD.sentencia7);
		db.execSQL(OperacionesBD.sentencia8);
		db.execSQL(OperacionesBD.sentencia9);
		db.execSQL(OperacionesBD.sentencia10);
		DatosIniciales();
	}

	private void DatosIniciales() 
	{
		InputStream is = null;
		try 
		{
			is = aux.getAssets().open("sentencias.sql");
			if (is != null) 
			{
				dbaux.beginTransaction();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = reader.readLine();
				while (!TextUtils.isEmpty(line)) 
				{
					dbaux.execSQL(line);
					line = reader.readLine();
				}
				dbaux.setTransactionSuccessful();
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			dbaux.endTransaction();
			if (is != null) 
			{
				try
				{
					is.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
	            }                
	        }
	    }
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
