package registro.estudiantes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import registro.estudiantes.dao.Ciudad;
import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.Facultad;

public class Queries {
	private EntityManagerFactory emf;
	private EntityManager em;

	public Queries() {
		this.emf = Persistence.createEntityManagerFactory("registroestudiantesdb");
		this.em = this.emf.createEntityManager();
		em.getTransaction().begin();
	}

	public void darAltaEstudiante(String nombre, String apellido, Long dni, String genero, String Ciudad) {
		Estudiante estudiante = new Estudiante(nombre, apellido, dni, genero);
		em.persist(estudiante);
	}

	public void closeConnection() {
		em.getTransaction().commit();
		em.close();
		this.emf.close();
	}

	public void insertFacultad(Facultad facultad) {
		em.persist(facultad);
	}

	public Ciudad seleccionarCiudad(int id, String ciudad) {
		List c =  em.createQuery("SELECT c FROM Ciudad c WHERE c.nombreCiudad=:nombreCiudad")
				.setParameter("nombreCiudad", ciudad).getResultList();
		return (Ciudad) c.get(0);
	}
}
