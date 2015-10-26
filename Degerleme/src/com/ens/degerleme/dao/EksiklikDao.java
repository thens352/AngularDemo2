package com.ens.degerleme.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ens.degerleme.model.entity.Eksiklik;

@Stateless
public class EksiklikDao {

	@PersistenceContext
	EntityManager entityManager;

	public void eksiklikKaydet(Eksiklik eksiklik) {
		entityManager.persist(eksiklik);
	}

	public Eksiklik eksiklikGuncelle(Eksiklik eksiklik) {
		return entityManager.merge(eksiklik);
	}
}
