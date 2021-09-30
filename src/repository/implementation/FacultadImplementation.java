package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Ciudad;
import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.Facultad;
import registro.estudiantes.dao.SituacionAcademica;
import repository.FacultadRepository;

public class FacultadImplementation implements FacultadRepository {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private CarreraImplementation carrera;
	private EstudianteImplementation estudiante;
	
	@Override
	public Facultad getFacultadByID(int id) {
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f WHERE f.idFacultad=:idFacultad")
				.setParameter("idFacultad", id).getResultList();
		if (!c.isEmpty()) {
			return (Facultad) c.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Facultad getFacultadByName(String name) {
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f WHERE f.nombreFacultad=:nombreFacultad")
				.setParameter("nombreFacultad", name).getResultList();
		if (!c.isEmpty()) {
			return (Facultad) c.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Facultad saveFacultad(Facultad facultad) {
		em.persist(facultad);
		return facultad;
	}

	@Override
	public void deleteFacultad(Facultad facultad) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Permite matricular un estudiante en una carrera con el dni del estudiante y
	 * el nombre de la carrera
	 * 
	 * @param dni           el dni del estudiante
	 * @param nombreCarrera el nombre de la carrera
	 */
	public void matricularEstudiante(Long dni, String nombreCarrera) {
		Estudiante nroEstudiante = this.estudiante.getByDNI(dni);
		Carrera idCarrera = this.carrera.getCarreraByName(nombreCarrera);
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, null, null);
			em.persist(tempAcademica);
		}

	}
	
	/**
	 * Permite matricular un estudiante en una carrera con el nro de Libreta y el
	 * nombre de la carrera
	 * 
	 * @param nroLibreta    es el numero de estudiante
	 * @param nombreCarrera el nombre de la carrera
	 */
	public void matricularEstudiante(int nroLibreta, String nombreCarrera) {
		Estudiante nroEstudiante = this.estudiante.getEstudianteByID(nroLibreta);
		Carrera idCarrera = this.carrera.getCarreraByName(nombreCarrera);
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, null, null);
			em.persist(tempAcademica);
		}

	}
	
	/**
	 * Permite conocer el identificador de una carrera con el solo hecho de saber su
	 * nombre
	 * 
	 * @param nombreCarrera el nombre de la carrera
	 * @return el identificador de la carrera
	 */

	/**
	 * Generar un reporte de las carreras, que para cada carrera incluya información
	 * de los inscriptos y egresados por año. Se deben ordenar las carreras
	 * alfabéticamente, y presentar los años de manera cronológica. de carrera hay
	 * que ir a situacion academica por idCarrera y luego verificar que fecha
	 * inscripcion o ingreso esten dengro del año dado
	 * 
	 * @param nombreCarrera @return3)
	 */

	// PERIODISMO 
	// ANIO=> 2014 ID=> 1 NOMBRECARRERA => PERIODISMO CANTINSCRIPTOS=> 5 CANTEGRESADOS=>3
	// ANIO=> 2015 ID=> 1 NOMBRECARRERA => PERIODISMO CANTINSCRIPTOS=> 50 CANTEGRESADOS=>23
	public List<Carrera> getReporte() {
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em
				.createQuery("SELECT c FROM Carrera c JOIN c.estudiantes s")
				.getResultList();
		
		
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;

	}

}
