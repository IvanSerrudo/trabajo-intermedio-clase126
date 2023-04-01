package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
	
	private String nombre;
	private int legajo;
	private List<Materia> materiasAprobadas;
	
	public Alumno(String nombre, int legajo) {
		this.nombre = nombre;
		this.legajo = legajo;
		this.materiasAprobadas = new ArrayList<Materia>();
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public List<Materia> getmateriasAprobadas() {
		return materiasAprobadas;
	}

	public void setmateriasAprobadas(List<Materia> materiasAprobadas) {
		this.materiasAprobadas = materiasAprobadas;
	}
	
}
