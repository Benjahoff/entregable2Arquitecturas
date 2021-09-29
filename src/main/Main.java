package main;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import registro.estudiantes.CSVtoMYSQL;
import registro.estudiantes.Queries;
import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Estudiante;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queries queries = new Queries();
		CSVtoMYSQL importador = new CSVtoMYSQL();
		
		importador.importarCSVFacultad(queries);
		importador.importarCSVCarrera(queries);
		importador.importarCSVCiudad(queries);
		importador.importarCSVEstudiante(queries);
		importador.importarCSVSituacionAcademica(queries);
		
		
//		List<Carrera> lista =queries.getReporte();
//		for(Carrera carrera: lista) {
//			System.out.println(carrera.toString());
//		}
		
//		Timestamp.valueOf("");
		
		
//		queries.darAltaEstudiante("Martin", "Aguirre", (long) 39117327, "Masculino", "Bolivar");
//		queries.darAltaEstudiante("Franco", "Viera", (long) 43185316, "Masculino", "Bolivar");
//		queries.darAltaEstudiante("Benjamin", "Hoffman", (long) 43100029, "Dudoso", "Bolivar");
//		queries.darAltaEstudiante("Lady", "Gaga", (long) 14235341, "Femenino", "Urdampilleta");
//		queries.darAltaEstudiante("Vaca", "Lola", (long) 03034561, "Femenino", "Diareux");
		
//		List<Estudiante> retornedList = queries.getEstudiantesByCiudad(5, 1);
//				for (Estudiante estudiante : retornedList) {
//			System.out.println(estudiante.toString());
//		}
//		List<Carrera> retornedListCarrera = queries.getAlumnosPorAnio();
//		for (Carrera carrera : retornedListCarrera) {
//			System.out.println(carrera.toString());
//		}
//		queries.matricularEstudiante(5,"Three-banded plover");
		queries.closeConnection();
	}

}
