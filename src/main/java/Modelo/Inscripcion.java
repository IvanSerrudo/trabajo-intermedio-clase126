package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion {
	
	private Alumno alumno;
	private Materia materia;
	private LocalDate fecha;
	private boolean inscripcionValida;
	
	public Inscripcion(Alumno alumno, Materia materia) {
		this.alumno = alumno;
		this.materia = materia;
		this.fecha = LocalDate.now();
		this.inscripcionValida = esValida();
	}

	public boolean esValida() {
		if (!materia.poseeCorrelativas()) {
			return true;
		} else if (alumno.getmateriasAprobadas().contains(materia)) {
			return false;
		}
		List<Boolean> correlativasAprobadas = new ArrayList<Boolean>();
		boolean TodoVerdadero = true;
		for (int i = 0; i < materia.getCorrelativas().size(); i++) {
			Materia mat = materia.getCorrelativas().get(i);
			if (alumno.getmateriasAprobadas().contains(mat)) {
				correlativasAprobadas.add(true);
			} else {
				correlativasAprobadas.add(false);
			}
		}
		for (boolean valor : correlativasAprobadas) {
			if (!valor) {
				TodoVerdadero = false;
				break;
			}
		}
		if (TodoVerdadero) {
			return true;
		}
		return false;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean getInscripcionValida() {
		return inscripcionValida;
	}

	public void setInscripcionValida(boolean inscripcionValida) {
		this.inscripcionValida = inscripcionValida;
	}
	
	
}
