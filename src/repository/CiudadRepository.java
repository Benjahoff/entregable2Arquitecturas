package repository;

import registro.estudiantes.dao.Ciudad;

public interface CiudadRepository {
	Ciudad getCiudadByID(int id);
	
	Ciudad getCiudadByName(String name);
	
	Ciudad saveCiudad(Ciudad ciudad);
	
	void deleteCiudad(Ciudad ciudad);
	
}
