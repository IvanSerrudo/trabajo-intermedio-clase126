package Ejercicio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;

public class Intermedio {
	private static List<Materia> listaMaterias = new ArrayList<Materia>();
	private static List<Alumno> listaAlumnos = new ArrayList<Alumno>();
	private static List<Inscripcion> listaInscripciones = new ArrayList<Inscripcion>();
	public static void main(String[] args) {
		instanciarMaterias();
		instanciarAlumnos();
		try {
			List<String> archivo = Files.readAllLines(Paths.get("src//main//java//Archivo//inscripciones.csv"));
			Path respuesta = Paths.get("src//main//java//Archivo//respuesta.csv");
			for (int i = 0; i < archivo.size(); i++) {
				String[] inscr = archivo.get(i).split(";");
				boolean AlumnoExiste = buscarAlumno(inscr[0],Integer.parseInt(inscr[1]));
				boolean MateriaExiste = buscarMateria(inscr[2],Integer.parseInt(inscr[3]));
				Alumno inscrAlum = null;
				Materia inscrMat = null;
				Inscripcion inscripcion = null;
				
				if (AlumnoExiste) {
					inscrAlum = buscarAlumno(Integer.parseInt(inscr[1]));
				}
				if (MateriaExiste) {
					inscrMat = buscarMateria(Integer.parseInt(inscr[3]));
				}
				// Si se verifica se crea la inscripción, ella indica si es válida o no
				if (AlumnoExiste && MateriaExiste) {
					inscripcion = new Inscripcion(inscrAlum, inscrMat);
					listaInscripciones.add(inscripcion);
				}
				
				// Empieza a escribir el archivo respuesta.csv
				if (!AlumnoExiste) {
					Files.write(respuesta, (inscr[0]+";"+inscr[2]+";"+ "No es alumno regular"+System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);
				} else if (!MateriaExiste) {
					Files.write(respuesta, (inscr[0]+";"+inscr[2]+";"+ "La materia no existe"+System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);
				} else if (!inscripcion.getInscripcionValida()) {
					Files.write(respuesta, (inscr[0]+";"+inscr[2]+";"+ "No cumple con las materias correlativas aprobadas o ya tiene la materia aprobada"+System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);
				} else {
					Files.write(respuesta, (inscr[0]+";"+inscr[2]+";"+ "Inscripcion exitosa"+System.lineSeparator()).getBytes(),StandardOpenOption.APPEND);
				}
			}
			System.out.println("Finalizado, ver resultados en el archivo: "+respuesta);
			
		} catch (IOException e) {
			System.out.println("Hubo un error al leer el archivo inscripciones.csv");
			e.printStackTrace();
		}
	}
	
	private static void instanciarMaterias() {
		Materia materia1 = new Materia("Programacion 1", 1);
		Materia materia2 = new Materia("Programacion 2", 2);
		materia2.getCorrelativas().add(materia1);
		Materia materia3 = new Materia("Bases de datos 1", 4);
		Materia materia4 = new Materia("Bases de datos 2", 5);
		materia4.getCorrelativas().add(materia1);
		materia4.getCorrelativas().add(materia3);
		listaMaterias.add(materia1);
		listaMaterias.add(materia2);
		listaMaterias.add(materia3);
		listaMaterias.add(materia4);
	}
	
	private static void instanciarAlumnos() {
		Alumno alumno1 = new Alumno("Ivan",1001);
		Alumno alumno2 = new Alumno("Sebita",1002);
		alumno2.getmateriasAprobadas().add(buscarMateria(1));
		Alumno alumno3 = new Alumno("Elias",1003);
		alumno3.getmateriasAprobadas().add(buscarMateria(1));
		alumno3.getmateriasAprobadas().add(buscarMateria(4));
		listaAlumnos.add(alumno1);
		listaAlumnos.add(alumno2);
		listaAlumnos.add(alumno3);
	}
	
	private static boolean buscarAlumno (String nombre, int legajo) {
		for (Alumno alumno : listaAlumnos) {
			if (nombre.equals(alumno.getNombre()) && legajo == alumno.getLegajo()) {
				return true;
			}
		}
		return false;
	}
	
	private static Alumno buscarAlumno (int legajo) {
		for (Alumno alumno : listaAlumnos) {
			if (legajo == alumno.getLegajo()) {
				return alumno;
			}
		}
		return null;
	}
	
	private static boolean buscarMateria (String nombre, int codigo) {
		for (Materia materia : listaMaterias) {
			if (nombre.equals(materia.getNombre()) && codigo == materia.getCodigo()) {
				return true;
			}
		}
		return false;
	}
	
	private static Materia buscarMateria (int codigo) {
		for (Materia materia : listaMaterias) {
			if (codigo == materia.getCodigo()) {
				return materia;
			}
		}
		return null;
	}
}
