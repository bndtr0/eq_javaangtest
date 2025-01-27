package model;

public class Usuario
{
	private String nombre_input;
	private String rut_input;
	private Dato   datos;
	public String getNombre_input() {
		return nombre_input;
	}
	public void setNombre_input(String nombre_input) {
		this.nombre_input = nombre_input;
	}
	public String getRut_input() {
		return rut_input;
	}
	public void setRut_input(String rut_input) {
		this.rut_input = rut_input;
	}
	public Dato getDatos() {
		return datos;
	}
	public void setDatos(Dato datos) {
		this.datos = datos;
	}	
}
