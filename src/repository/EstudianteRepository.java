package repository;

import registro.estudiantes.dao.Estudiante;

public interface EstudianteRepository {
	Estudiante getEstudianteByID(int nroEstudiante);

	Estudiante getEstudianteByName(String name);

	Estudiante saveEstudiante(Estudiante estudiante);

	void deleteEstudiante(Estudiante estudiante);
}
