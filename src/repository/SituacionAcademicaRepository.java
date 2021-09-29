package repository;

import registro.estudiantes.dao.SituacionAcademica;

public interface SituacionAcademicaRepository {
	SituacionAcademica getSituacionAcademicaByID(int id);

	SituacionAcademica getSituacionAcademicaByName(String name);

	SituacionAcademica saveSituacionAcademica(SituacionAcademica situacionacademica);

	void deleteSituacionAcademica(SituacionAcademica situacionacademica);
}
