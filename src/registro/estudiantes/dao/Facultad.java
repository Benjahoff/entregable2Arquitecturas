package registro.estudiantes.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Facultad {

	@Id
	private int idFacultad;
	@Column
	private String nombreFacultad;
	@OneToMany(mappedBy = "facultad")
	private List<Carrera> carreras;
	
	public Facultad(int idFacultad, String nombreFacultad) {
		this.idFacultad = idFacultad;
		this.nombreFacultad = nombreFacultad;
	}


	public String getNombreFacultad() {
		return nombreFacultad;
	}


	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}


	public int getIdFacultad() {
		return idFacultad;
	}
}
