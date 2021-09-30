package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import registro.estudiantes.CSVtoMYSQL;
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
