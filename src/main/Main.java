package main;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DTO.ReporteCarreras;
import registro.estudiantes.CSVtoMYSQL;
import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Estudiante;
import repository.implementation.CarreraImplementation;
import repository.implementation.CiudadImplementation;
import repository.implementation.EstudianteImplementation;
import repository.implementation.FacultadImplementation;
import repository.implementation.SituacionAcademicaImplementation;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * Apertura de conexiones
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroestudiantesdb");
		CarreraImplementation carreraImplementation = new CarreraImplementation(emf.createEntityManager());
		CiudadImplementation ciudadImplementation = new CiudadImplementation(emf.createEntityManager());
		EstudianteImplementation estudianteImplementation = new EstudianteImplementation(emf.createEntityManager());
		FacultadImplementation facultadImplementation = new FacultadImplementation(emf.createEntityManager(), carreraImplementation, estudianteImplementation);
		SituacionAcademicaImplementation situacionAcademicaImplementation = new SituacionAcademicaImplementation(
				emf.createEntityManager());
		

		/**
		 * Importar Datos del CSV
		 */
		CSVtoMYSQL importador = new CSVtoMYSQL();

		importador.importarCSVFacultad(facultadImplementation);
		importador.importarCSVCarrera(facultadImplementation, carreraImplementation);
		importador.importarCSVCiudad(ciudadImplementation);
		importador.importarCSVEstudiante(estudianteImplementation, ciudadImplementation);
		importador.importarCSVSituacionAcademica(situacionAcademicaImplementation, carreraImplementation,
				estudianteImplementation);

		/**
		 * Operaciones
		 */
		
		/**
		 * 2) A
		 * dar de alta a un estudiante
		 */
			Estudiante estudiante= new Estudiante("benjamin", "hoffman", (long) 4333333, "difuso");
		
			System.out.println(estudianteImplementation.saveEstudiante(estudiante));
			
		/**
		 * 2) B
		 * matricular un estudiante a una carrera
		 */
			facultadImplementation.matricularEstudiante((long) 4333333, "African black crake");
		
		/**
		 * 2) C
		 * recupera todos los estudiante con un ordenamiento por apellido
		 */
			List<Estudiante> estudiantes=estudianteImplementation.getEstudiantesSortByApellido();
			for (Estudiante estudiante1 : estudiantes) {
				System.out.println(estudiante1.toString());
			}
			
		
		/**
		 * 2) D
		 * recupera un estudiante en base de su libreta universitaria
		 */
			System.out.println(estudianteImplementation.getEstudianteByID(2));
		/**
		 * 2) E
		 * recupera todos los estudiante en base a su genero
		 */
			estudiantes=estudianteImplementation.getEstudiantesByGenero("Male");
			for (Estudiante estudiante2 : estudiantes) {
				System.out.println(estudiante2.toString());
			}
		/**
		 * 2) F
		 * recupera las carreras con estudiantes inscriptos y ordenar por cantidad de inscriptos
		 */
			List<Carrera>carreras=carreraImplementation.getCarrerasConEstudiantesSortByCantidad();
			for(Carrera carrera : carreras) {
				System.out.println(carrera.toString());
			}
		/**
		 * 2) G
		 * recupera los estudiantes por una determinada carrera, filtrados por ciudad de residencia
		 */
			estudiantes=estudianteImplementation.getEstudiantesByCiudad(254,22);
			for (Estudiante estudiante3 : estudiantes) {
				System.out.println(estudiante3.toString());
			}
		/**
		 * 3
		 * Generar un reporte de las carreras, que para cada carrera incluya información de los
		 *inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
		 *los años de manera cronológica.
		 */
			
			List<ReporteCarreras> reportesCarreras=carreraImplementation.getReporte();
			for(ReporteCarreras reporte : reportesCarreras) {
				System.out.println(reporte.toString());
			}
			
			

		/**
		 * Cierre de conexiones
		 */
		carreraImplementation.closeConnection();
		ciudadImplementation.closeConnection();
		estudianteImplementation.closeConnection();
		facultadImplementation.closeConnection();
		situacionAcademicaImplementation.closeConnection();
		emf.close();
	}

}
