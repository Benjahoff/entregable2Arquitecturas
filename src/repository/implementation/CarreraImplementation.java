package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Carrera;
import repository.CarreraRepository;

public class CarreraImplementation implements CarreraRepository {

	private EntityManager em;

	public CarreraImplementation(EntityManager em) {
		this.em = em;
	}

	@Override
	public Carrera getCarreraByID(int id) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.id=:id").setParameter("id", id)
				.getResultList();
		em.getTransaction().commit();
		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	@Override
	public Carrera getCarreraByName(String name) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.nombreCarrera=:nombreCarrera")
				.setParameter("nombreCarrera", name).getResultList();
		em.getTransaction().commit();
		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	@Override
	public Carrera saveCarrera(Carrera carrera) {
		em.getTransaction().begin();
		em.persist(carrera);
		em.getTransaction().commit();
		return carrera;
	}

	@Override
	public void deleteCarrera(Carrera carrera) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
	}

	// f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad
	// de inscriptos.
	public List<Carrera> getCarrerasConEstudiantesSortByCantidad() {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em.createQuery(
				"SELECT c FROM Carrera c JOIN  c.estudiantes s GROUP BY c.idCarrera ORDER BY COUNT(s.estudiante)")
				.getResultList();
//					.createQuery("SELECT c FROM Carrera c JOIN  c.estudiantes s ").getResultList();
		em.getTransaction().commit();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
	
	/**
	 * PONER ACA EL DEL DTO
	 */

}
