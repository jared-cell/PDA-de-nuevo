package modelo;

import java.sql.Date;

public class ReporteUsuario {
	private String Describe_el_problema;
	private String DireccionCompleta;
	private Date Fecha;
	private String localidad;
	public String getDescribe_el_problema() {
		return Describe_el_problema;
	}
	public void setDescribe_el_problema(String describe_el_problema) {
		Describe_el_problema = describe_el_problema;
	}  
	public String getDireccionCompleta() {
		return DireccionCompleta;
	}
	public void setDireccionCompleta(String direccionCompleta) {
		DireccionCompleta = direccionCompleta;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	

}
