package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Carrera;
import repository.CarreraRepository;

public class CarreraImplementation implements CarreraRepository{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Override
	public Carrera getCarreraByID(int id) {
		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.id=:id")
				.setParameter("id", id).getResultList();

		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	@Override
	public Carrera getCarreraByName(String name) {
		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.nombreCarrera=:nombreCarrera")
				.setParameter("nombreCarrera", name).getResultList();

		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	@Override
	public Carrera saveCarrera(Carrera carrera) {
		em.persist(carrera);
		return carrera;
	}

	@Override
	public void deleteCarrera(Carrera carrera) {
		// TODO Auto-generated method stub
	}
	
	// f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad
	// de inscriptos.
	public List<Carrera> getCarrerasConEstudiantesSortByCantidad() {
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em.createQuery(
				"SELECT c FROM Carrera c JOIN  c.estudiantes s GROUP BY c.idCarrera ORDER BY COUNT(s.estudiante)")
				.getResultList();
//					.createQuery("SELECT c FROM Carrera c JOIN  c.estudiantes s ").getResultList();

		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}


}
