package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Ciudad;
import repository.CiudadRepository;

public class CiudadImplementation implements CiudadRepository {

	private EntityManager em;

	public CiudadImplementation(EntityManager em) {
		this.em = em;
	}

	/**
	 * Permite obtener una ciudad apartir de su id
	 * 
	 * @param id de la ciudad
	 * @return retorna una ciudad
	 */
	@Override
	public Ciudad getCiudadByID(int id) {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.idCiudad =: idCiudad")
				.setParameter("idCiudad", id).getResultList();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		}
		return null;
	}

	/**
	 * Permite obtener una ciudad apartir de su nombre
	 * 
	 * @param nombre de la ciudad
	 * @return retorna una ciudad
	 */
	@Override
	public Ciudad getCiudadByName(String name) {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombreCiudad=:nombreCiudad")
				.setParameter("nombreCiudad", name).getResultList();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Permite guardar una ciudad
	 * 
	 * @param la ciudad a guardar
	 * @return retorna la ciudad guardada
	 */
	@Override
	public Ciudad saveCiudad(Ciudad ciudad) {
		em.getTransaction().begin();
		em.persist(ciudad);
		em.getTransaction().commit();
		return ciudad;
	}

	/**
	 * Permite borrar una ciudad, la cual se pasa por parametro
	 * 
	 * @param la ciudad a borrar
	 */
	@Override
	public void deleteCiudad(Ciudad ciudad) {
		int idCiudad = ciudad.getIdCiudad();
		em.createQuery("DELETE FROM Ciudad c WHERE c.idCiudad=:idCiudad").setParameter("idCiudad", idCiudad);
	}

	public void closeConnection() {
		this.em.close();
	}
}
