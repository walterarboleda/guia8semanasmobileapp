package com.example.app8semanas.Model;

public class MetasPersonales 
{
	public String idmeta;
	public String cedula;
	public String meta;
	
	public MetasPersonales(String cedula, String idmeta, String meta) 
	{
		super();
		this.idmeta = idmeta;
		this.cedula = cedula;
		this.meta = meta;
	}
	
	public String getIdmeta() {
		return idmeta;
	}
	
	public void setIdmeta(String idmeta) {
		this.idmeta = idmeta;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getMeta() {
		return meta;
	}
	
	public void setMeta(String meta) {
		this.meta = meta;
	}
}
