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

	@Override
	public SituacionAcademica getSituacionAcademicaByID(int id) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
		return null;
	}

	@Override
	public SituacionAcademica getSituacionAcademicaByName(String name) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
		return null;

	}

	@Override
	public SituacionAcademica saveSituacionAcademica(SituacionAcademica situacionacademica) {
		em.getTransaction().begin();
		em.persist(situacionacademica);
		em.getTransaction().commit();
		return situacionacademica;
	}

	@Override
	public void deleteSituacionAcademica(SituacionAcademica situacionacademica) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();

	}

}
