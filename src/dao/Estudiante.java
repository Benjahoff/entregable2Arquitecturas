package dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int nroEstudiante;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private Long dni;
	@Column
	private String genero;
	@ManyToOne
	@JoinColumn(name="idCiudad")
	Ciudad ciudad;
	
	
	public Estudiante(String nombre, String apellido, Long dni, String genero) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.genero = genero;
	}

	public int getNroEstudiante() {
		return nroEstudiante;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Estudiante [nroEstudiante=" + nroEstudiante + ", nombre=" + nombre + ", apellido=" + apellido + ", dni="
				+ dni + ", genero=" + genero + "]";
	}
	
	
}
