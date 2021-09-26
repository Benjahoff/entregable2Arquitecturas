package registro.estudiantes;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Ciudad;
import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.Facultad;
import registro.estudiantes.dao.SituacionAcademica;

public class CSVtoMYSQL {

	public CSVtoMYSQL() {

	}

	public void importarCSVFacultad(Queries queries) {
		ArrayList<Facultad> facultades = new ArrayList<Facultad>();
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facultad.csv"));
			for (CSVRecord row : parser) {
				// int , String
				int idFacultad = Integer.parseInt(row.get("idFacultad"));
				String nombreFacultad = row.get("nombreFacultad");
				Facultad temp = new Facultad(idFacultad, nombreFacultad);
				facultades.add(temp);
			}
			// insertar
			Iterator<Facultad> it = facultades.iterator();
			while (it.hasNext()) {
				queries.insertFacultad(it.next());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void importarCSVCarrera(Queries queries) {
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("carrera.csv"));
			for (CSVRecord row : parser) {
				// int idCarrera, Facultad facultad, String nombreCarrera
				int idCarrera = Integer.parseInt(row.get("idCarrera"));
				int idfacu=Integer.parseInt(row.get("facultad"));
				String nombreCarrera=row.get("nombreCarrera");
//				Carrera temp = new Carrera()
			}
			// insertar

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void importarCSVCiudad(Queries queries) {
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facultad.csv"));
			for (CSVRecord row : parser) {
				// int , String

			}
			// insertar

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void importarCSVEstudiante(Queries queries) {
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facultad.csv"));
			for (CSVRecord row : parser) {
				// int , String

			}
			// insertar

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void importarCSVSituacionAcademica(Queries queries) {
		ArrayList<SituacionAcademica> situacionesAcad = new ArrayList<SituacionAcademica>();
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("facultad.csv"));
			for (CSVRecord row : parser) {
				// int , String

			}
			// insertar

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
