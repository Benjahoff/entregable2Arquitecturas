package repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.SituacionAcademica;
import repository.SituacionAcademicaRepository;

public class SituacionAcademicaImplementation implements SituacionAcademicaRepository {

	private EntityManager em;

	public SituacionAcademicaImplementation(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param id de situacion academica
	 * @return retorna un objeto estudiante
	 */
	@Override
	public SituacionAcademica getSituacionAcademicaByID(int id) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
		return null;
	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param nombre de situacion academica
	 * @return retorna un objeto estudiante
	 */
	@Override
	public SituacionAcademica getSituacionAcademicaByName(String name) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
		return null;

	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param situacion academica a guardar
	 * @return retorna un objeto estudiante
	 */
	@Override
	public SituacionAcademica saveSituacionAcademica(SituacionAcademica situacionacademica) {
		em.getTransaction().begin();
		em.persist(situacionacademica);
		em.getTransaction().commit();
		return situacionacademica;
	}
	
	/**
	 * Permite recuperar un estudiante por su nombre
	 * 
	 * @param situacion academica a borrar
	 * @return retorna un objeto estudiante
	 */
	@Override
	public void deleteSituacionAcademica(SituacionAcademica situacionacademica) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();

	}

	public void closeConnection() {
		this.em.close();
	}
}
