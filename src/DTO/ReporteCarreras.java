package DTO;

public class ReporteCarreras {
	private int idCarrera;
	private String nombreCarrera;
	private int anio;
	Long egresados;
	Long inscriptos;

	public ReporteCarreras() {
		super();
		// TODO Auto-generated constructor stub
	}

	// solo para probar
	public ReporteCarreras(int idCarrera, String nombreCarrera, int anio) {
		super();
		this.idCarrera = idCarrera;
		this.nombreCarrera = nombreCarrera;
		this.anio = anio;
	}

	public ReporteCarreras(int idCarrera, String nombreCarrera, int anio, Long egresados, Long inscriptos) {
		super();
		this.idCarrera = idCarrera;
		this.nombreCarrera = nombreCarrera;
		this.anio = anio;
		this.egresados = egresados;
		this.inscriptos = inscriptos;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		return "ReporteCarreras [idCarrera=" + idCarrera + ", nombreCarrera=" + nombreCarrera + ", anio=" + anio
				+ ", egresados=" + egresados + ", inscriptos=" + inscriptos + "]";
	}

}
