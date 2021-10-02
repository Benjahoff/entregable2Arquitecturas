package registro.estudiantes.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SituacionAcademica {

//	Me dice que todas las clases deben llevar un identificador, sino me tira error: CONSULTAR
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "nroEstudiante")
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "idCarrera")
	private Carrera carrera;

	@Column
	private int antiguedad;

	@Column
	private boolean egresado;

	@Column
	private Timestamp fechaInscripcion;

	@Column(nullable = true)
	private Timestamp fechaEgreso;

	public SituacionAcademica(Estudiante estudiante, Carrera carrera, int antiguedad, boolean egresado,
			Timestamp fechaInscripcion, Timestamp fechaEgreso) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.antiguedad = antiguedad;
		this.egresado = egresado;
		this.fechaInscripcion = fechaInscripcion;
		this.fechaEgreso = fechaEgreso;
	}

	/**
	 * Instanciador con superclase
	 */
	public SituacionAcademica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public boolean isEgresado() {
		return egresado;
	}

	public void setEgresado(boolean egresado) {
		this.egresado = egresado;
	}

	public Timestamp getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Timestamp fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Timestamp getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Timestamp fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public int getId() {
		return id;
	}

}
