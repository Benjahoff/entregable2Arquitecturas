package main;

import java.util.List;

import registro.estudiantes.CSVtoMYSQL;
import registro.estudiantes.Queries;
import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Estudiante;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queries queries = new Queries();
		CSVtoMYSQL importador = new CSVtoMYSQL();

//		importador.importarCSVFacultad(queries);
		// String str="2015-03-31";
		// Date date=Date.valueOf(str);//converting string into sql date
//		queries.darAltaEstudiante("Martin", "Aguirre", (long) 39117327, "Masculino", "Bolivar");
//		queries.darAltaEstudiante("Franco", "Viera", (long) 43185316, "Masculino", "Bolivar");
//		queries.darAltaEstudiante("Benjamin", "Hoffman", (long) 43100029, "Masculino", "Bolivar");
//		queries.darAltaEstudiante("Lady", "Gaga", (long) 14235341, "Femenino", "Urdampilleta");
//		queries.darAltaEstudiante("Vaca", "Lola", (long) 03034561, "Femenino", "Diareux");
		
		List<Estudiante> retornedList = queries.getEstudiantesByCiudad(5, 1);
				for (Estudiante estudiante : retornedList) {
			System.out.println(estudiante.toString());
		}
//		List<Carrera> retornedListCarrera = queries.getCarrerasConEstudiantesSortByCantidad();
//		for (Carrera carrera : retornedListCarrera) {
//			System.out.println(carrera.toString());
//		}
//		queries.matricularEstudiante(5,"Three-banded plover");
		queries.closeConnection();
	}

}
