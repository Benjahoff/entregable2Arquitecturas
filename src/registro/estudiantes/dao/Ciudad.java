package registro.estudiantes.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ciudad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCiudad;
	@Column
	private String nombreCiudad;
	@Column
	private String provincia;
	@Column
	private String pais;
	@OneToMany(mappedBy = "ciudad")
	private List<Estudiante> estudiantes;

	public Ciudad(String nombreCiudad, String provincia, String pais) {
		this.nombreCiudad = nombreCiudad;
		this.provincia = provincia;
		this.pais = pais;
	}

	/**
	 * Instanciador con superclase
	 */
	public Ciudad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	@Override
	public String toString() {
		return "Ciudad [idCiudad=" + idCiudad + ", nombreCiudad=" + nombreCiudad + ", provincia=" + provincia
				+ ", pais=" + pais + "]";
	}

}
