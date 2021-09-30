package registro.estudiantes;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.internal.build.AllowSysOut;

import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Ciudad;
import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.Facultad;
import registro.estudiantes.dao.SituacionAcademica;
import repository.implementation.FacultadImplementation;

public class CSVtoMYSQL {
	private static boolean isFacultadImported = false;
	private static boolean isCarreraImported = false;
	private static boolean isEstudianteImported = false;
	private static boolean isCiudadImported = false;
	private static boolean isSituacionAcademicaImported = false;

	public CSVtoMYSQL() {

	}

	public void importarCSVFacultad( FacultadImplementation facu) {
		if (!isFacultadImported) {
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
					facu.saveFacultad(it.next());
				}
				isFacultadImported = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	public void importarCSVCarrera(Queries queries) {
		if (!isCarreraImported) {
			ArrayList<Carrera> carreras = new ArrayList<Carrera>();
			CSVParser parser;
			try {
				parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("carrera.csv"));
				for (CSVRecord row : parser) {
					// int idCarrera, Facultad facultad, String nombreCarrera
					int idCarrera = Integer.parseInt(row.get("idCarrera"));
					int idfacu = Integer.parseInt(row.get("idFacultad"));
					String nombreCarrera = row.get("nombreCarrera");
					Facultad temp;
					temp = queries.getFacultadById(idfacu);
					Carrera carreraTemp = new Carrera(idCarrera, temp, nombreCarrera);
					carreras.add(carreraTemp);
				}
				// insertar
				Iterator<Carrera> it = carreras.iterator();
				while (it.hasNext()) {
					queries.insertCarrera(it.next());
				}
				isCarreraImported = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}

	public void importarCSVCiudad(Queries queries) {
		if (!isCiudadImported) {
			ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
			CSVParser parser;
			try {
				parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("ciudad.csv"));
				for (CSVRecord row : parser) {
					int idCiudad = Integer.parseInt(row.get("idCiudad"));
					String nombreCiudad = row.get("nombreCiudad");
					String pais = row.get("pais");
					String provincia = row.get("provincia");
					Ciudad Ciudadtemp = new Ciudad(nombreCiudad, provincia, pais);
					ciudades.add(Ciudadtemp);
				}
				// insertar
				Iterator<Ciudad> it = ciudades.iterator();
				while (it.hasNext()) {
					queries.insertCiudad(it.next());
				}
				isCiudadImported = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}

	public void importarCSVEstudiante(Queries queries) {
		if (!isEstudianteImported) {
			ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
			CSVParser parser;
			try {
				parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("estudiante.csv"));
				for (CSVRecord row : parser) {
					int nroEstudiante = Integer.parseInt(row.get("nroEstudiante"));
					String apellido = row.get("apellido");
					Long dni = Long.parseLong(row.get("dni"));
					String genero = row.get("genero");
					String nombre = row.get("nombre");
					int idCiudad = Integer.parseInt(row.get("idCiudad"));
					Ciudad ciudadtemp;
					ciudadtemp = queries.getCiudadForId(idCiudad);
					Estudiante Estudiantetemp = new Estudiante(nombre, apellido, dni, genero, ciudadtemp);
					estudiantes.add(Estudiantetemp);
				}
				// insertar
				Iterator<Estudiante> it = estudiantes.iterator();
				while (it.hasNext()) {
					queries.insertEstudiante(it.next());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	public void importarCSVSituacionAcademica(Queries queries) {
		if (!isSituacionAcademicaImported) {
			ArrayList<SituacionAcademica> situacionesAcad = new ArrayList<SituacionAcademica>();
			CSVParser parser;
			try {
				parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("situacion_academica.csv"));
				for (CSVRecord row : parser) {
					int id = Integer.parseInt(row.get("id"));
					int antiguedad = Integer.parseInt(row.get("antiguedad"));
					int egresadoInt=Integer.parseInt(row.get("egresado"));
					boolean egresado= egresadoInt !=0 ? true : false;
					int idCarrera = Integer.parseInt(row.get("idCarrera"));
					int nroEstudiante = Integer.parseInt(row.get("nroEstudiante"));
					String fechaEgreso = row.get("fechaEgreso");
					String fechaInscripcion = row.get("fechaInscripcion");

					Carrera carreraTemp = queries.getCarreraById(idCarrera);
					Estudiante estudianteTemp = queries.getEstudiante(nroEstudiante); // por alguna extraña razon le suma 500 en la db
					
					SituacionAcademica temp = new SituacionAcademica(estudianteTemp, carreraTemp, antiguedad, egresado,
							Timestamp.valueOf(fechaInscripcion), Timestamp.valueOf(fechaEgreso));
					situacionesAcad.add(temp);
				}
				// insertar
				Iterator<SituacionAcademica> it = situacionesAcad.iterator();
				while (it.hasNext()) {
					queries.insertSituacionAcademica(it.next());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}

}
