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

	private EntityManager em;

	private CarreraImplementation carrera;
	private EstudianteImplementation estudiante;

	public FacultadImplementation(EntityManager em) {
		this.em = em;
	}

	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param id de la facultad 
	 * @return retorna un objeto estudiante
	 */
	@Override
	public Facultad getFacultadByID(int id) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f WHERE f.idFacultad=:idFacultad")
				.setParameter("idFacultad", id).getResultList();
		em.getTransaction().commit();
		if (!c.isEmpty()) {
			return (Facultad) c.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param nombre de la facultad
	 * @return retorna un objeto estudiante
	 */
	@Override
	public Facultad getFacultadByName(String name) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Facultad> c = em.createQuery("SELECT f FROM Facultad f WHERE f.nombreFacultad=:nombreFacultad")
				.setParameter("nombreFacultad", name).getResultList();
		em.getTransaction().commit();
		if (!c.isEmpty()) {
			return (Facultad) c.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param facultad a guardar
	 * @return retorna un objeto estudiante
	 */
	@Override
	public Facultad saveFacultad(Facultad facultad) {
		em.getTransaction().begin();
		em.persist(facultad);
		em.getTransaction().commit();
		return facultad;
	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param facultad a borrar
	 * @return retorna un objeto estudiante
	 */
	@Override
	public void deleteFacultad(Facultad facultad) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();

	}

	/**
	 * Permite matricular un estudiante en una carrera con el dni del estudiante y
	 * el nombre de la carrera
	 * 
	 * @param dni           el dni del estudiante
	 * @param nombreCarrera el nombre de la carrera
	 */
	public void matricularEstudiante(Long dni, String nombreCarrera) {
		em.getTransaction().begin();
		Estudiante nroEstudiante = this.estudiante.getByDNI(dni);
		Carrera idCarrera = this.carrera.getCarreraByName(nombreCarrera);
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, null, null);
			em.persist(tempAcademica);
		}
		em.getTransaction().commit();
	}

	/**
	 * Permite matricular un estudiante en una carrera con el nro de Libreta y el
	 * nombre de la carrera
	 * 
	 * @param nroLibreta    es el numero de estudiante
	 * @param nombreCarrera el nombre de la carrera
	 */
	public void matricularEstudiante(int nroLibreta, String nombreCarrera) {
		em.getTransaction().begin();
		Estudiante nroEstudiante = this.estudiante.getEstudianteByID(nroLibreta);
		Carrera idCarrera = this.carrera.getCarreraByName(nombreCarrera);
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, null, null);
			em.persist(tempAcademica);
		}
		em.getTransaction().commit();

	}

	public void closeConnection() {
		this.em.close();
	}

}
