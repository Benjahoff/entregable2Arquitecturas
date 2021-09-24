package dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SituacionAcademica {
	
	@OneToOne
	@JoinColumn(name="nroEstudiante")
	private Estudiante estudiante;
	@ManyToOne
	@JoinColumn(name="idCarrera")
	private Carrera carrera;
	@Column
	private int antiguedad;
	@Column
	private boolean egresado;
	@Column
	private Date fechaInscripcion;
	@Column (nullable = false)
	private Date fechaEgreso;
	
	

	public SituacionAcademica(Estudiante estudiante, Carrera carrera, int antiguedad, boolean egresado,
			Date fechaInscripcion, Date fechaEgreso) {
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.antiguedad = antiguedad;
		this.egresado = egresado;
		this.fechaInscripcion = fechaInscripcion;
		this.fechaEgreso = fechaEgreso;
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

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	

}

