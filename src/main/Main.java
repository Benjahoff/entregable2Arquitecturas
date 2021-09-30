package main;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import DTO.ReporteCarreras;
import registro.estudiantes.CSVtoMYSQL;
import registro.estudiantes.Queries;
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroestudiantesdb");

		CarreraImplementation carreraImplementation = new CarreraImplementation(emf.createEntityManager());
		CiudadImplementation ciudadImplementation = new CiudadImplementation(emf.createEntityManager());
		EstudianteImplementation estudianteImplementation = new EstudianteImplementation(emf.createEntityManager());
		FacultadImplementation facultadImplementation = new FacultadImplementation(emf.createEntityManager());
		SituacionAcademicaImplementation situacionAcademicaImplementation = new SituacionAcademicaImplementation(
				emf.createEntityManager());

		Queries queries = new Queries();
		CSVtoMYSQL importador = new CSVtoMYSQL();

		importador.importarCSVFacultad(facultadImplementation);
		importador.importarCSVCarrera(queries);
		importador.importarCSVCiudad(queries);
		importador.importarCSVEstudiante(queries);
		importador.importarCSVSituacionAcademica(queries);

		queries.closeConnection();
	}

}
