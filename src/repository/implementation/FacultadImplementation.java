package repository.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import registro.estudiantes.dao.Facultad;
import repository.FacultadRepository;

public class FacultadImplementation implements FacultadRepository {

	private EntityManager em;

	public FacultadImplementation(EntityManager em) {
		this.em = em;

	}

	/**
	 * Permite recuperar una facultad por su id
	 * 
	 * @param id es el id de la facultad
	 * @return retorna un objeto facultad
	 */
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

	/**
	 * Permite recuperar una facultad por su nombre
	 * 
	 * @param name es el nombre de la facultad
	 * @return retorna un objeto facultad
	 */
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

	/**
	 * Permite guardar una facultad
	 * 
	 * @param facultad es la facultad a guardar
	 * @return retorna la facultad guardada
	 */
	@Override
	public Facultad saveFacultad(Facultad facultad) {
		em.getTransaction().begin();
		em.persist(facultad);
		em.getTransaction().commit();
		return facultad;
	}

	/**
	 * Permite eliminar una facultad
	 * @param facultad es la facultad a eliminar
	 */
	@Override
	public void deleteFacultad(Facultad facultad) {
		int idFacultad = facultad.getIdFacultad();
		em.createQuery("DELETE FROM Facultad f WHERE f.idFacultad=:idFacultad").setParameter("idFacultad", idFacultad);
	}

	public void closeConnection() {
		this.em.close();
	}

}
