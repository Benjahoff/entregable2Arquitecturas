package main;

import registro.estudiantes.CSVtoMYSQL;
import registro.estudiantes.Queries;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queries queries = new Queries();
		CSVtoMYSQL importador= new CSVtoMYSQL();
		
//		importador.importarCSVFacultad(queries);
//		queries.darAltaEstudiante("Martin", "Aguirre", (long) 39117327 , "male", "Bolivar");
		queries.matricularEstudiante(5,"Three-banded plover");
		queries.closeConnection();
	}

}
