package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Ciudad;
import repository.CiudadRepository;

public class CiudadImplementation implements CiudadRepository{
	
	private EntityManagerFactory emf;
	private EntityManager em;

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

	@Override
	public Ciudad saveCiudad(Ciudad ciudad) {
		em.persist(ciudad);
		return ciudad;
	}

	@Override
	public void deleteCiudad(Ciudad ciudad) {
		// TODO Auto-generated method stub
		
	}

}
