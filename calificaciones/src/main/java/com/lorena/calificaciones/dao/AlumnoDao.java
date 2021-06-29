package com.lorena.calificaciones.dao;

import javax.persistence.Query;

import com.lorena.calificaciones.dominios.Alumno;

public class AlumnoDao extends AbstractDao<Alumno> {
	
	public AlumnoDao() {
		setClazz(Alumno.class);
	}
	
	public Alumno getAlumno(String dni) {
		String qlString= "FROM "+Alumno.class.getName()+" WHERE dni = '"+dni+"'";
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
		return (Alumno)query.getSingleResult();
	}

}
