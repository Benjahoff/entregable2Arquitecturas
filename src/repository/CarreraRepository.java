package repository;

import registro.estudiantes.dao.Carrera;

public interface CarreraRepository {
	Carrera getCarreraByID(int id);

	Carrera getCarreraByName(String name);

	Carrera saveCarrera(Carrera carrera);

	void deleteCiudad(Carrera carrera);
}
