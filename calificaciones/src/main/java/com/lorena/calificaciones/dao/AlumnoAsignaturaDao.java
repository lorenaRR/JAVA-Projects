package com.lorena.calificaciones.dao;

import java.util.List;

import javax.persistence.Query;

import com.lorena.calificaciones.dominios.AlumnoAsignatura;

public class AlumnoAsignaturaDao extends AbstractDao<AlumnoAsignatura> {
	public AlumnoAsignaturaDao() {
		setClazz(AlumnoAsignatura.class);
	}

	public List<AlumnoAsignatura> getNotas(String dni) {
		String qlString = "FROM " + AlumnoAsignatura.class.getName() + " WHERE alumno_dni = '" + dni + "'";
		Query query = getEntityManager().createQuery(qlString);
		return query.getResultList();
	}
	
	public AlumnoAsignatura getAlumnoAsignatura(String dni, String idAsignatura) {
		String qlString= "FROM "+AlumnoAsignatura.class.getName()+" WHERE alumno_dni = '"+dni+"' AND asignatura_asigId ='"+idAsignatura+"'";
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
		return (AlumnoAsignatura)query.getSingleResult();
	}
	
	
}
