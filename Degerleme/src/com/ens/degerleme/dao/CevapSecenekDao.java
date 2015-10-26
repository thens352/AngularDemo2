package com.ens.degerleme.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ens.degerleme.model.entity.CevapSecenek;

@Stateless
public class CevapSecenekDao {

	@PersistenceContext
	EntityManager entityManager;

	public void CevapSecenekKaydet(CevapSecenek cevapSecenek) {
		entityManager.persist(cevapSecenek);
	}

	public CevapSecenek CevapSecenekGuncelle(CevapSecenek cevapSecenek) {
		return entityManager.merge(cevapSecenek);
	}

	public CevapSecenek CevapSecenekSil(Integer id) {
		CevapSecenek cevapSecenek = entityManager.find(CevapSecenek.class, id);
		entityManager.remove(cevapSecenek);
		return cevapSecenek;
	}
}
