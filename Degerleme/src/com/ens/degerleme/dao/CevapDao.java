package com.ens.degerleme.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ens.degerleme.model.entity.Cevap;

@Stateless
public class CevapDao {

	@PersistenceContext
	EntityManager entityManager;

	public void CevapKaydet(Cevap cevap) {
		entityManager.persist(cevap);
	}

	public Cevap CevapGuncelle(Cevap cevap) {
		return entityManager.merge(cevap);
	}

	public Cevap CevapSil(Integer id) {
		Cevap cevap = entityManager.find(Cevap.class, id);
		entityManager.remove(cevap);
		return cevap;
	}

}
