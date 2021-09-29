package repository;

import registro.estudiantes.dao.Facultad;

public interface FacultadRepository {
	Facultad getFacultadByID(int id);

	Facultad getFacultadByName(String name);

	Facultad saveFacultad(Facultad facultad);

	void deleteFacultad(Facultad facultad);
}
