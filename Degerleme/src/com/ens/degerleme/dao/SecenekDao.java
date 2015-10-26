package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.SecenekSearch;
import com.ens.degerleme.model.entity.Secenek;

@Stateless
public class SecenekDao {

	@PersistenceContext
	EntityManager entityManager;

	public void secenekKaydet(Secenek secenek) {
		entityManager.persist(secenek);
	}

	public Secenek secenekGuncelle(Secenek secenek) {
		return entityManager.merge(secenek);
	}

	public Secenek secenekGetir(Integer id) {
		return entityManager.find(Secenek.class, id);
	}

	public Secenek secenekSil(Integer id) {
		Secenek secenek = entityManager.find(Secenek.class, id);
		entityManager.remove(secenek);
		return secenek;
	}

	@SuppressWarnings("unchecked")
	public List<Secenek> secenekGetirBySoruId(Integer id) {
		return entityManager
				.createQuery("select i from Secenek i where i.soru.id=:id")
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Secenek> secenekGetir(SecenekSearch secenekSearch) {
		String sql = "select a from Secenek a where 1=1 ";

		if (secenekSearch.getId() != null && 0 != secenekSearch.getId()) {
			sql = sql + " and a.id=:id";
		}
		if (secenekSearch.getMetin() != null
				&& !!"".equals(secenekSearch.getMetin())) {
			sql = sql + " and UPPER(a.metin) like :metin";
		}
		if (secenekSearch.getSoru() != null
				&& secenekSearch.getSoru().getId() != null
				&& 0 != secenekSearch.getSoru().getId()) {
			sql = sql + " and a.soru.id=:soruId";
		}

		Query query = entityManager.createQuery(sql);

		if (secenekSearch.getId() != null && 0 != secenekSearch.getId()) {
			query.setParameter("id", secenekSearch.getId());
		}
		if (secenekSearch.getMetin() != null
				&& !!"".equals(secenekSearch.getMetin())) {
			query.setParameter("metin", "%"
					+ secenekSearch.getMetin().toUpperCase() + "%");
		}
		if (secenekSearch.getSoru() != null
				&& secenekSearch.getSoru().getId() != null
				&& !!"".equals(secenekSearch.getSoru())) {
			query.setParameter("soruId", secenekSearch.getSoru().getId());
		}

		return query.getResultList();

	}

}
