package registro.estudiantes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Queries {
	private EntityManagerFactory emf;
	private EntityManager em;

	public Queries() {
		this.emf = Persistence.createEntityManagerFactory("registroestudiantesdb");
		this.em = this.emf.createEntityManager();
	}
	
	public void closeConnection() {
		this.emf.close();
	}
}
