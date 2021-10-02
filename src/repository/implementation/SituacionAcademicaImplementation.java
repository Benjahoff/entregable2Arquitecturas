package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import registro.estudiantes.dao.SituacionAcademica;
import repository.SituacionAcademicaRepository;

public class SituacionAcademicaImplementation implements SituacionAcademicaRepository {

	private EntityManager em;

	public SituacionAcademicaImplementation(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * Permite recuperar una situacion academica por su id
	 * 
	 * @param id es el id de la situacion academica
	 * @return retorna un objeto situacionacademica
	 */
	@Override
	public SituacionAcademica getSituacionAcademicaByID(int id) {
		@SuppressWarnings("unchecked")
		List<SituacionAcademica> SituacionAcademicaList = em
				.createQuery("SELECT s FROM SituacionAcademica s WHERE s.id=:id")
				.setParameter("id", id).getResultList();
		if (!SituacionAcademicaList.isEmpty()) {
			return SituacionAcademicaList.get(0);
		}
		return null;
	}
	
	/**
	 * Permite recuperar una situacion academica por su nombre
	 * 
	 * @param name es el nombre de situacion academica
	 * @return retorna un objeto situacion academica
	 */
	@Override
	public SituacionAcademica getSituacionAcademicaByName(String name) {
		return null;
	}
	
	/**
	 * Permite guardar una situacion academica
	 * 
	 * @param situacionacademica es la situacion academica a guardar
	 * @return retorna la situacion academica guardada
	 */
	@Override
	public SituacionAcademica saveSituacionAcademica(SituacionAcademica situacionacademica) {
		em.getTransaction().begin();
		em.persist(situacionacademica);
		em.getTransaction().commit();
		return situacionacademica;
	}
	
	/**
	 * Permite borrar una situacion academica
	 * 
	 * @param situacionacademica es la situacion academica a borrar
	 */
	@Override
	public void deleteSituacionAcademica(SituacionAcademica situacionacademica) {
		int id = situacionacademica.getId();
		em.createQuery("DELETE FROM SituacionAcademica s WHERE s.id=:id").setParameter("id", id);
	}

	public void closeConnection() {
		this.em.close();
	}
}
