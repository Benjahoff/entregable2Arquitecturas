package registro.estudiantes.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	private int idCarrera;
	@ManyToOne
	@JoinColumn(name="idFacultad")
	private Facultad facultad;
	@Column 
	private String nombreCarrera;
	@OneToMany(mappedBy = "carrera")
	private List<SituacionAcademica> estudiantes;
	
	public Carrera(Facultad facultad, String nombreCarrera) {
		this.facultad = facultad;
		this.nombreCarrera = nombreCarrera;
	}

	public int getIdCarrera() {
		return idCarrera;
	}
	
	public Facultad getFacultad() {
		return facultad;
	}


	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}


	public String getNombreCarrera() {
		return nombreCarrera;
	}


	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
}
