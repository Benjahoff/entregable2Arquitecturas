package repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.SituacionAcademica;
import repository.SituacionAcademicaRepository;

public class SituacionAcademicaImplementation implements SituacionAcademicaRepository{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Override
	public SituacionAcademica getSituacionAcademicaByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SituacionAcademica getSituacionAcademicaByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SituacionAcademica saveSituacionAcademica(SituacionAcademica situacionacademica) {
		em.persist(situacionacademica);
		return situacionacademica;
	}

	@Override
	public void deleteSituacionAcademica(SituacionAcademica situacionacademica) {
		// TODO Auto-generated method stub
		
	}

}
