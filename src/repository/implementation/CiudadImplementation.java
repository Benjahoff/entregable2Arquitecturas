package repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import registro.estudiantes.dao.Ciudad;
import repository.CiudadRepository;

public class CiudadImplementation implements CiudadRepository{
	
	private EntityManager em;

	public CiudadImplementation(EntityManager em) {
		this.em=em;
	}
	
	@Override
	public Ciudad getCiudadByID(int id) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.idCiudad =: idCiudad")
				.setParameter("idCiudad", id).getResultList();
		em.getTransaction().commit();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		}
		return null;
	}

	@Override
	public Ciudad getCiudadByName(String name) {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombreCiudad=:nombreCiudad")
				.setParameter("nombreCiudad", name).getResultList();
		em.getTransaction().commit();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Ciudad saveCiudad(Ciudad ciudad) {
		em.getTransaction().begin();
		em.persist(ciudad);
		em.getTransaction().commit();
		return ciudad;
	}

	@Override
	public void deleteCiudad(Ciudad ciudad) {
		em.getTransaction().begin();
		// TODO Auto-generated method stub
		em.getTransaction().commit();
		
	}

}
