package com.example.app8semanas.Model;

public class Metas 
{
	public int idmeta;
	public String meta;
	public String idactividad;
	
	public Metas(int idmeta, String meta, String idactividad)
	{
		super();
		this.idmeta = idmeta;
		this.meta = meta;
		this.idactividad = idactividad;
	}

	public int getIdmeta() {
		return idmeta;
	}

	public void setIdmeta(int idmeta) {
		this.idmeta = idmeta;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getIdactividad() {
		return idactividad;
	}

	public void setIdactividad(String idactividad) {
		this.idactividad = idactividad;
	}
	
	
	
	
}
