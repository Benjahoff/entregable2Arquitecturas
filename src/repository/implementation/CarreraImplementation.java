package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import DTO.ReporteCarreras;
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

	/**
	 * Permite obtener las carreras que tienen estudiantes inscriptos ordenadas por
	 * cantidad de inscriptos
	 * 
	 * @return retorna una lista de carreras
	 */
	public List<Carrera> getCarrerasConEstudiantesSortByCantidad() {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em.createQuery(
				"SELECT c FROM Carrera c JOIN  c.estudiantes s GROUP BY c.idCarrera ORDER BY COUNT(s.estudiante)")
				.getResultList();
		em.getTransaction().commit();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}

	/**
	 * Permite obtener un reporte de las carreras con la cantidad de inscriptos y
	 * egresados por año
	 * 
	 * @return retorna una lista de ReportesCarrera, un DTO creado especificamente
	 *         para esta consulta
	 */
	public List<ReporteCarreras> getReporte() {
		@SuppressWarnings("unchecked")
		List<ReporteCarreras> retornoDTOCarreras = em.createQuery(
				"SELECT new DTO.ReporteCarreras(c.idCarrera, c.nombreCarrera, YEAR(s.fechaInscripcion),sum(s.egresado+0), COUNT(s.estudiante))"
						+ "FROM Carrera c JOIN c.estudiantes s GROUP BY (c.idCarrera) ORDER BY YEAR(s.fechaInscripcion) ASC, c.nombreCarrera ASC")
				.getResultList();

		if (!retornoDTOCarreras.isEmpty()) {
			return retornoDTOCarreras;
		}
		return null;

	}

	public void closeConnection() {
		this.em.close();
	}
}
