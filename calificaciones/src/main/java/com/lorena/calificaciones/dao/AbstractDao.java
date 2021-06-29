package com.lorena.calificaciones.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.lorena.calificaciones.utiles.EntityManagerUtil;

public class AbstractDao <T> implements Dao<T> {
	public EntityManager entityManager = EntityManagerUtil.getEntityManager();
	private Class<T> clazz;


	public Optional<T> get(int id) {
		return Optional.ofNullable(entityManager.find(clazz, id));
	}

	public List<T> getAll() {
		String qlString = "FROM "+clazz.getName();
		Query query = entityManager.createQuery(qlString);
		return query.getResultList();
		
	}
	
	

	public void save(T t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
		
	}

	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
		
	}

	public void delete(T t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
		
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
			
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}
}
