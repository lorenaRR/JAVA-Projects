package com.lorena.calificaciones.dao;

import javax.persistence.Query;

import com.lorena.calificaciones.dominios.Asignatura;

public class AsignaturaDao extends AbstractDao<Asignatura> {

	public AsignaturaDao() {
		setClazz(Asignatura.class);
	}
	
	public Asignatura getAsignatura(String nombre) {
		String qlString= "FROM "+Asignatura.class.getName()+" WHERE nomAsignatura = '"+nombre+"'";
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
		return (Asignatura)query.getSingleResult();
	}
	
	public Asignatura getAsignaturaPorId(String id) {
		String qlString= "FROM "+Asignatura.class.getName()+" WHERE id = '"+id+"'";
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
		return (Asignatura)query.getSingleResult();
	}
	
}