package com.ens.degerleme.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ens.degerleme.model.entity.Onlem;

@Stateless
public class OnlemDao {

	@PersistenceContext
	EntityManager entityManager;

	public void onlemKaydet(Onlem onlem) {
		entityManager.persist(onlem);
	}

	public Onlem onlemGuncelle(Onlem onlem) {
		return entityManager.merge(onlem);
	}

}
