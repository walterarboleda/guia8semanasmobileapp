package com.example.app8semanas.Datos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OperacionesBD 
{
	public static final String Table1="Participantes";
	public static final String cedula="cedula";
	public static final String nombre="nombre";
	public static final String apellido="apellido";
	public static final String telefono="telefono";
	public static final String email="email";
	public static final String alias="alias";
	
	public static final String Table2="Antropometricos";
	public static final String iddato="id_dato";
	public static final String edad="edad";
	public static final String peso="peso";
	public static final String estatura="estatura";
	public static final String imc="imc";
	public static final String perimetro="p_abdominal";	
	
	public static final String id_meta="id_meta";
	public static final String meta="meta";
	
	public static final String Table3="Semanas";
	public static final String Table4="Actividades";
	public static final String Table5="Metas";
	public static final String Table6="Metas_Personales";	
	public static final String Table7="Companero";
    public static final String Table8="Control";
	
	private CrearBD objcreate;
	private SQLiteDatabase db;	
	

	public static final String sentencia1="CREATE TABLE "+Table1+ " (" 
	   +cedula+ " VARCHAR( 12 ) PRIMARY KEY NOT NULL,"
       +nombre+ " VARCHAR( 40 ) NOT NULL,"
       +apellido+ " VARCHAR( 40 ) NOT NULL,"
       +alias+ " VARCHAR( 40 ),"
       +telefono+ " INT NOT NULL,"
       +email+ " VARCHAR( 100 )  NOT NULL);";
	
	public static final String sentencia2="CREATE TABLE "+Table2+ " (" 
    +iddato+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
    +edad+ " INT NOT NULL, "+peso+ " INT NOT NULL,"
    +estatura+ " INT NOT NULL, "+imc+ " DOUBLE NOT NULL,"
    +perimetro+ " INT NOT NULL,"
    +cedula+ " VARCHAR( 12 ) NOT NULL REFERENCES Participantes ( Cedula ));";
	
	public static final String sentencia3="CREATE TABLE "+Table3+ " (" +
			"id_semana VARCHAR( 5 ) PRIMARY KEY NOT NULL," +
			"nombre VARCHAR( 50 ) NOT NULL UNIQUE," +
			"tematica VARCHAR( 20 ) NOT NULL UNIQUE," +
			"asignacion VARCHAR( 20 ) NOT NULL UNIQUE," +
			"recomendacion VARCHAR( 20 ));";
	
	public static final String sentencia4="CREATE TABLE "+Table4+ "(" +
			"id_actividad VARCHAR( 5 ) PRIMARY KEY NOT NULL," +
			"actividad TEXT NOT NULL, descripcion VARCHAR( 20 )," +
			"explicacion VARCHAR( 20 ), puntos INT NOT NULL," +
			"total_puntos INT NOT NULL,"+
			"id_semana VARCHAR( 5 ) NOT NULL References "+Table3+" (id_semana));";
			
	
	public static final String sentencia5="CREATE TABLE "+Table5+ "(" +
			"id_meta VARCHAR( 7 ) PRIMARY KEY NOT NULL," +
			"meta TEXT NOT NULL UNIQUE," +
			"id_actividad VARCHAR( 5 ) References "+Table4+" (id_actividad));";
	
	public static final String sentencia6="CREATE TABLE "+Table6+ "(" 
			+cedula+ " VARCHAR( 12 ) NOT NULL REFERENCES Participantes ( cedula )," +
			"id_meta VARCHAR( 7 ) NOT NULL REFERENCES "+Table5+" (id_meta)," +
			"meta TEXT NOT NULL UNIQUE,"+ "PRIMARY KEY (cedula ,id_meta));";
	
	public static final String sentencia7="CREATE TABLE "+Table7+ "("+
			"id_companero INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+nombre+ " VARCHAR( 40 ) NOT NULL,"
		    +apellido+ " VARCHAR( 40 ) NOT NULL,"
		    +telefono+ " INT NOT NULL,"
		    +cedula+ " VARCHAR( 12 ) NOT NULL REFERENCES Participantes ( Cedula ));";	
	
	public static final String sentencia8="CREATE TABLE "+Table8+ "("+
			"id_control INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
			"id_actividad VARCHAR(5) NOT NULL, puntos INT NOT NULL,"+
			"semana INT NOT NULL, fecha VARCHAR(50) NOT NULL);";
	
	public static final String sentencia9="CREATE TABLE EdadFisiologica (ejercicio INTEGER," +
			" desayuno INTEGER, cena INTEGER," +
			" descanso INTEGER, fumar INTEGER," +
			" alcohol INTEGER);";
	
	public static final String sentencia10="CREATE TABLE Calendario (id_calendario INTEGER PRIMARY KEY," +
				" fecha_inicio VARCHAR(10));";
	
	public OperacionesBD(Context context) 
	{
		objcreate= new CrearBD(context);
		db= objcreate.getWritableDatabase();
	}
	
	private ContentValues GenerarParticipante(String ced, String nom, String ape, String ali,  long tel, String mail)
	{
		ContentValues datos=new ContentValues();
		datos.put(cedula, ced);
		datos.put(nombre, nom);
		datos.put(apellido, ape);
		datos.put(alias, ali);
		datos.put(telefono, tel);
		datos.put(email, mail);
		return datos;
	}
	
	private ContentValues GenerarAntropometrico(int age, int kg, int est, double masa, int abdomen, String ced)
	{
		ContentValues datos=new ContentValues();
		datos.put(edad, age);
		datos.put(peso, kg);
		datos.put(estatura, est);
		datos.put(imc, masa);
		datos.put(perimetro, abdomen);
		datos.put(cedula, ced);
		return datos;
	}	
	
	private ContentValues GenerarFisiologia(int ejercicio, int desayuno, int cena, int descanso, int fumar, int alcohol)
	{
		ContentValues datos = new ContentValues();
		datos.put("ejercicio", ejercicio);
		datos.put("desayuno", desayuno);
		datos.put("cena", cena);
		datos.put("descanso", descanso);
		datos.put("fumar", fumar);
		datos.put("alcohol", alcohol);
		return datos;
	}
	
	private ContentValues GenerarMetas(String ced,String idmeta, String met) 
	{
		ContentValues datos=new ContentValues();
		datos.put(cedula, ced);
		datos.put(id_meta, idmeta);
		datos.put(meta, met);		
		return datos;
	}
	
	private ContentValues GenerarCompanero(String nom, String ape, long tel, String ced)
	{
		ContentValues datos= new ContentValues();
		datos.put(nombre, nom);
		datos.put(apellido, ape);
		datos.put(telefono, tel);
		datos.put(cedula, ced);
		return datos;
	}
	
	private ContentValues GenerarControl(String act, int puntaje, String semana, String fecha)
	{
		ContentValues datos= new ContentValues();
		datos.put("id_actividad", act);
		datos.put("puntos", puntaje);
		datos.put("semana", semana);
		datos.put("fecha", fecha);
		return datos;
	}
		
	public void InsertarParticipante(String ced, String nom, String ape, String ali, long tel, String mail)
	{		
		db.insert(Table1, null, GenerarParticipante(ced,nom,ape,ali,tel,mail));
	}
	
	public void InsertarAntropometrico(int edad, int peso, int est, double imc, int abdomen, String cedula)
	{
		db.insert(Table2, null, GenerarAntropometrico(edad, peso, est, imc, abdomen, cedula));
	}
	
	public void insertarEdadFisiologica(int ejercicio, int desayuno, int cena, int descanso, int fumar, int alcohol)
	{			
		db.insert("EdadFisiologica", null, GenerarFisiologia(ejercicio, desayuno, cena, descanso, fumar, alcohol));
	}
	
	public void InsertarMeta(String sql)
	{
		db.execSQL(sql);
	}
	
	public void InsertarMetaPersonal(String cedula, String idmeta, String meta) 
	{		
		try{
		db.insert(Table6, null, GenerarMetas(cedula, idmeta, meta));
		}catch(Exception e){}
	}	
	
	public void InsertarCompanero(String nom, String ape, long tel, String ced)
	{
		db.insert(Table7, null, GenerarCompanero(nom,ape,tel,ced));
	}
	
	public void InsertarControl(String act, int pun, String sem, String fecha)
	{
		db.insert(Table8, null, GenerarControl(act,pun,sem,fecha));
	}
	
	@SuppressLint("SimpleDateFormat")
	public void crearCalendario ()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = sdf.format(new Date());
		ContentValues datos = new ContentValues();
		datos.put("fecha_inicio", fecha);
		db.insert("Calendario", null, datos);
	}
	
	public Cursor select(String tabla)
	{		
		return db.rawQuery("SELECT * FROM "+tabla+"", null);
	}
	
	public List<String> Metas(String consulta)
	{
		List<String> lista= new ArrayList<String>();
		db=objcreate.getReadableDatabase();
		Cursor cursor=db.rawQuery(consulta, null);
		int aux=0;
		lista.add("");
		while(cursor.moveToNext())
		{
			if (aux==0){
				aux=1;
			}
			else
			lista.add(cursor.getString(0).toString());	
		}		
		return (lista);
	}
	
	public List<String> MetasQuery(String consulta)
	{
		List<String> lista= new ArrayList<String>();
		db=objcreate.getReadableDatabase();
		Cursor cursor=db.rawQuery(consulta, null);
		while(cursor.moveToNext())
		{
			lista.add(cursor.getString(0).toString());
				
		}		
		return (lista);
	}
	
	public int Comprobar(String sql)
	{
		db=objcreate.getReadableDatabase();
		Cursor cursor=db.rawQuery(sql, null);
		cursor.moveToFirst();
		int res=cursor.getInt(0);
		cursor.close();
		return res;
	}
	
	public void Actualizar(String query)
	{
		db.execSQL(query);
	}
	
	public Cursor select_row (String tabla, String columna, String id){
		return db.rawQuery("select * from " + tabla + " where " + columna + "='" + id + "'" , null);
	}
	
	public void close()
	{
		db.close();
	}
	
	public String composeJSONfromSQLite()
	{
    	HashMap<String, HashMap<String, HashMap<String, String>>> elementos = new HashMap<String, HashMap<String, HashMap<String, String>>>();
    	HashMap<String, HashMap<String, String>> datos_generales = new HashMap<String, HashMap<String, String>>();
        Cursor c = select("Participantes");
        if (c.moveToFirst()) 
        {
        	HashMap<String, String> participante = new HashMap<String, String>();
        	participante.put("cedula", c.getString(0));
        	participante.put("nombre", c.getString(1));
        	participante.put("apellido", c.getString(2));
        	participante.put("telefono", c.getString(4));
        	participante.put("alias", c.getString(5));
        	participante.put("email", c.getString(3));
        	datos_generales.put("participante", participante);
		}
        c = select("EdadFisiologica");
        if (c.moveToFirst()) 
        {
        	HashMap<String, String> edad_fisiologica = new HashMap<String, String>();
        	edad_fisiologica.put("ejercicio", c.getString(0));
        	edad_fisiologica.put("desayuno", c.getString(1));
        	edad_fisiologica.put("cena", c.getString(2));
        	edad_fisiologica.put("descanso", c.getString(3));
        	edad_fisiologica.put("fumar", c.getString(4));
        	edad_fisiologica.put("alcohol", c.getString(5));
        	datos_generales.put("edad_fisiologica", edad_fisiologica);
		}
        c = select("Calendario");
        if (c.moveToFirst()) 
        {
        	HashMap<String, String> calendario = new HashMap<String, String>();
        	calendario.put("fecha_inicio", c.getString(1));
        	datos_generales.put("calendario", calendario);
		}
        elementos.put("datos_generales", datos_generales);
        c = select("Antropometricos");
        HashMap<String, HashMap<String, String>> antropometricos = new HashMap<String, HashMap<String, String>>();
        while (c.moveToNext()) 
        {
        	HashMap<String, String> id_dato = new HashMap<String, String>();
        	id_dato.put("edad", c.getString(1));
        	id_dato.put("peso", c.getString(2));
        	id_dato.put("estatura", c.getString(3));
        	id_dato.put("imc", c.getString(4));
        	id_dato.put("p_abdominal", c.getString(5));
        	antropometricos.put(c.getString(0), id_dato);
        }
        elementos.put("antropometricos", antropometricos);
        c = select("Metas_Personales");
        HashMap<String, HashMap<String, String>> metas_personales = new HashMap<String, HashMap<String, String>>();
        while (c.moveToNext()) 
        {
        	HashMap<String, String> id_meta = new HashMap<String, String>();
        	id_meta.put("id_meta", c.getString(1));
        	metas_personales.put(c.getString(1), id_meta);
        }
        elementos.put("metas_personales", metas_personales);
        c = select("Control");
        HashMap<String, HashMap<String, String>> control = new HashMap<String, HashMap<String, String>>();
        while (c.moveToNext()) 
        {
        	HashMap<String, String> id_control = new HashMap<String, String>();
        	id_control.put("id_actividad", c.getString(1));
        	id_control.put("fecha", c.getString(2));
        	control.put(c.getString(0), id_control);
        }
        elementos.put("control", control);
        Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON
        return gson.toJson(elementos);
    }
	
}
