package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.Facultad;
import repository.EstudianteRepository;

public class EstudianteImplementation implements EstudianteRepository {

	private EntityManager em;

	public EstudianteImplementation(EntityManager em) {
		this.em = em;
	}

	/**
	 * Permite recuperar un estudiante por su numero de estudiante o numero de
	 * libreta
	 * 
	 * @param nroEstudiante el numero de libreta
	 * @return retorna un objeto estudiante
	 */
	@Override
	public Estudiante getEstudianteByID(int nroEstudiante) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudianteList = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.nroEstudiante=:nroEstudiante")
				.setParameter("nroEstudiante", nroEstudiante).getResultList();
		em.getTransaction().commit();
		if (!nroEstudianteList.isEmpty()) {
			return nroEstudianteList.get(0);
		}
		return null;
	}

	@Override
	public Estudiante getEstudianteByName(String name) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		Estudiante e = (Estudiante) em.createQuery("SELECT e FROM Estudiante e WHERE e.nombre=:nombre")
				.setParameter("nombre", name).getResultList();
		em.getTransaction().commit();
		return e;

	}

	@Override
	public Estudiante saveEstudiante(Estudiante estudiante) {
		em.getTransaction().begin();
		em.persist(estudiante);
		em.getTransaction().commit();
		return estudiante;
	}

	@Override
	public void deleteEstudiante(Estudiante estudiante) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
	}

	/**
	 * Permite buscar el numero de estudiante por su dni, por si el estudiante no lo
	 * recuerda
	 * 
	 * @param dni el dni
	 * @return un numero de estudiante si esta registrado o null si no lo esta
	 */
	public Estudiante getByDNI(Long dni) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudiante = em.createQuery("SELECT e FROM Estudiante e WHERE e.dni=:dni")
				.setParameter("dni", dni).getResultList();
		em.getTransaction().commit();
		if (!nroEstudiante.isEmpty()) {
			return nroEstudiante.get(0);
		}
		return null;
	}

//	g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.

	public List<Estudiante> getEstudiantesByCiudad(int idCiudad, int idCarrera) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em.createQuery(
				"SELECT e FROM Estudiante e JOIN e.carreras s WHERE s.carrera.idCarrera =: idCarrera AND  e.ciudad.idCiudad =: idCiudad")
				.setParameter("idCiudad", idCiudad).setParameter("idCarrera", idCarrera).getResultList();
		em.getTransaction().commit();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}

}
