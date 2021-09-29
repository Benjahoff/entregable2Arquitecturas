package registro.estudiantes;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import registro.estudiantes.dao.Carrera;
import registro.estudiantes.dao.Ciudad;
import registro.estudiantes.dao.Estudiante;
import registro.estudiantes.dao.Facultad;
import registro.estudiantes.dao.SituacionAcademica;

public class Queries {
	private EntityManagerFactory emf;
	private EntityManager em;

	public Queries() {
		this.emf = Persistence.createEntityManagerFactory("registroestudiantesdb");
		this.em = this.emf.createEntityManager();
		em.getTransaction().begin();
	}

	/**
	 * Permite dar de alta un estudiante en el registro Hace una busqueda para ver
	 * si la ciudad ingresada se encuentra en sistema y si no la encuentra da null
	 * 
	 * @param nombre   el nombre del estudiante
	 * @param apellido apellido del estudiante
	 * @param dni      el documento nacional de identidad
	 * @param genero   el genero del estudiante, puede ser cualquier cosa ya que es
	 *                 String
	 * @param Ciudad   el nombre de la ciudad
	 */
	public void darAltaEstudiante(String nombre, String apellido, Long dni, String genero, String Ciudad) {
		Ciudad ciudadIns = seleccionarCiudadPorNombre(Ciudad);
		Estudiante estudiante = new Estudiante(nombre, apellido, dni, genero, ciudadIns);

		em.persist(estudiante);
	}


	/**
	 * Permite matricular un estudiante en una carrera con el dni del estudiante y
	 * el nombre de la carrera
	 * 
	 * @param dni           el dni del estudiante
	 * @param nombreCarrera el nombre de la carrera
	 */
	public void matricularEstudiante(Long dni, String nombreCarrera) {
		Estudiante nroEstudiante = this.getNroEstudiante(dni);
		Carrera idCarrera = this.getidCarrera(nombreCarrera);
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, null, null);
			em.persist(tempAcademica);
		}

	}

	/**
	 * Permite matricular un estudiante en una carrera con el nro de Libreta y el
	 * nombre de la carrera
	 * 
	 * @param nroLibreta    es el numero de estudiante
	 * @param nombreCarrera el nombre de la carrera
	 */
	public void matricularEstudiante(int nroLibreta, String nombreCarrera) {
		Estudiante nroEstudiante = this.getEstudiante(nroLibreta);
		Carrera idCarrera = this.getidCarrera(nombreCarrera);
		if (idCarrera != null && nroEstudiante != null) {
			SituacionAcademica tempAcademica = new SituacionAcademica(nroEstudiante, idCarrera, 0, false, null, null);
			em.persist(tempAcademica);
		}

	}

	/**
	 * Permite buscar el numero de estudiante por su dni, por si el estudiante no lo
	 * recuerda
	 * 
	 * @param dni el dni
	 * @return un numero de estudiante si esta registrado o null si no lo esta
	 */
	private Estudiante getNroEstudiante(Long dni) {
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudiante = em.createQuery("SELECT e FROM Estudiante e WHERE e.dni=:dni")
				.setParameter("dni", dni).getResultList();
		if (!nroEstudiante.isEmpty()) {
			return nroEstudiante.get(0);
		}
		return null;
	}

	/**
	 * Permite recuperar un estudiante por su numero de estudiante o numero de
	 * libreta
	 * 
	 * @param nroEstudiante el numero de libreta
	 * @return retorna un objeto estudiante
	 */
	public Estudiante getEstudiante(int nroEstudiante) {
		@SuppressWarnings("unchecked")
		List<Estudiante> nroEstudianteList = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.nroEstudiante=:nroEstudiante")
				.setParameter("nroEstudiante", nroEstudiante).getResultList();
		if (!nroEstudianteList.isEmpty()) {
			return nroEstudianteList.get(0);
		}
		return null;
	}
	
	public List<Estudiante> getEstudiantesByApellido(){	
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em
				.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido").getResultList();
		if(!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
	
	
	public List<Estudiante> getEstudiantesByGenero(String genero){	
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.genero =: genero")
				.setParameter("genero", genero).getResultList();
		if(!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
	
	
	//f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
	public List<Carrera> getCarrerasConEstudiantesSortByCantidad(){
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em
				.createQuery("SELECT c FROM Carrera c JOIN  c.estudiantes s GROUP BY c.idCarrera ORDER BY COUNT(s.estudiante)").getResultList();
//				.createQuery("SELECT c FROM Carrera c JOIN  c.estudiantes s ").getResultList();

		if(!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
	
//	g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
	
	public List<Estudiante> getEstudiantesByCiudad(int idCiudad, int idCarrera){
		@SuppressWarnings("unchecked")
		List<Estudiante> retornedList = em.createQuery("SELECT e FROM Estudiante e JOIN e.carreras s WHERE s.carrera.idCarrera =: idCarrera AND  e.ciudad.idCiudad =: idCiudad")
				.setParameter("idCiudad", idCiudad)
				.setParameter("idCarrera", idCarrera).getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
	}
	/**
	 * Permite conocer el identificador de una carrera con el solo hecho de saber su
	 * nombre
	 * 
	 * @param nombreCarrera el nombre de la carrera
	 * @return el identificador de la carrera
	 */
	
	/**
	 * 
	 * @param nombreCarrera
	 * @return3) Generar un reporte de las carreras, que para cada carrera incluya información de los
				inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
				los años de manera cronológica.
				de carrera hay que ir a situacion academica por idCarrera y luego verificar que fecha inscripcion o ingreso esten dengro del año dado
	 */
	
	public List<Carrera> getAlumnosPorAnio(){
		@SuppressWarnings("unchecked")
		List<Carrera> retornedList = em.createQuery("SELECT c FROM Carrera c JOIN c.estudiantes s GROUP BY (YEAR(s.fechaInscripcion))").getResultList();
		if (!retornedList.isEmpty()) {
			return retornedList;
		}
		return null;
		
	}
	private Carrera getidCarrera(String nombreCarrera) {
		@SuppressWarnings("unchecked")
		List<Carrera> idCarrera = em.createQuery("SELECT c FROM Carrera c WHERE c.nombreCarrera=:nombreCarrera")
				.setParameter("nombreCarrera", nombreCarrera).getResultList();

		if (!idCarrera.isEmpty()) {
			return idCarrera.get(0);
		}
		return null;
	}

	/**
	 * Inserta una facultad
	 * 
	 * @param facultad es un objeto de tipo Facultad
	 */
	public void insertFacultad(Facultad facultad) {
		em.persist(facultad);
	}

	/**
	 * Metodo auxiliar que busca una ciudad por nombre en el registro, sirve para
	 * asignar el id de ciudad correcto al estudiante dado de alta
	 * 
	 * @param ciudad Es el nombre de la ciudad
	 * @return retorna un objeto ciudad si la busqueda fue exitosa o null si no lo
	 *         fue
	 */
	private Ciudad seleccionarCiudadPorNombre(String ciudad) {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombreCiudad=:nombreCiudad")
				.setParameter("nombreCiudad", ciudad).getResultList();
		if (!c.isEmpty()) {
			return (Ciudad) c.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Cierre de conexiones
	 */
	public void closeConnection() {
		em.getTransaction().commit();
		em.close();
		this.emf.close();
	}

	public Facultad getFacultadById(int idfacu) {
		@SuppressWarnings("unchecked")
		List<Facultad> f = em.createQuery("SELECT f FROM Facultad f WHERE f.idFacultad =: idFacultad")
				.setParameter("idFacultad", idfacu).getResultList();
		if(!f.isEmpty()) {
			return (Facultad) f.get(0);
		}
		return null;
	}

	public void insertCarrera(Carrera carr) {
		em.persist(carr);
	}

	public void insertCiudad(Ciudad city) {
		em.persist(city);		
	}

	Ciudad getCiudadForId(int idCiudad) {
		@SuppressWarnings("unchecked")
		List<Ciudad> c = em.createQuery("SELECT c FROM Ciudad c WHERE c.idCiudad =: idCiudad")
				.setParameter("idCiudad", idCiudad).getResultList();
		if(!c.isEmpty()) {
			return (Ciudad) c.get(0);
		}
		return null;
	}

	public void insertEstudiante(Estudiante student) {
		em.persist(student);		
	}

	public void insertSituacionAcademica(SituacionAcademica situacion) {
		em.persist(situacion);				
	}

	public Carrera getCarreraById(int idCarrera) {
		@SuppressWarnings("unchecked")
		List<Carrera> c = em.createQuery("SELECT c FROM Carrera c WHERE c.idCarrera =: idCarrera")
				.setParameter("idCarrera", idCarrera).getResultList();
		if(!c.isEmpty()) {
			return (Carrera) c.get(0);
		}
		return null;
	}
}
