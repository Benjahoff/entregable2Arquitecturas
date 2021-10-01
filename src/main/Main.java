package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DTO.ReporteCarreras;
import registro.estudiantes.CSVtoMYSQL;
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
		FacultadImplementation facultadImplementation = new FacultadImplementation(emf.createEntityManager());
		SituacionAcademicaImplementation situacionAcademicaImplementation = new SituacionAcademicaImplementation(
				emf.createEntityManager());
		
		ReporteCarreras reporteCarreras= new ReporteCarreras();

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
			facultadImplementation.matricularEstudiante(4333333, "African black crake");
		
		/**
		 * 2) C
		 * recupera todos los estudiante con un ordenamiento por apellido
		 */
			System.out.println(estudianteImplementation.getEstudiantesSortByApellido());
		
		/**
		 * 2) D
		 * recupera un estudiante en base de su libreta universitaria
		 */
			System.out.println(estudianteImplementation.getEstudianteByID(2));
		/**
		 * 2) E
		 * recupera todos los estudiante en base a su genero
		 */
			System.out.println(estudianteImplementation.getEstudiantesByGenero("Male"));
		/**
		 * 2) F
		 * recupera las carreras con estudiantes inscriptos y ordenar por cantidad de inscriptos
		 */
			System.out.println(carreraImplementation.getCarrerasConEstudiantesSortByCantidad());
		/**
		 * 2) G
		 * recupera los estudiantes por una determinada carrera, filtrados por ciudad de residencia
		 */
			System.out.println(estudianteImplementation.getEstudiantesByCiudad(1, 2));
		/**
		 * 3
		 * Generar un reporte de las carreras, que para cada carrera incluya información de los
		 *inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
		 *los años de manera cronológica.
		 */
			System.out.println(reporteCarreras.toString());
			

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
