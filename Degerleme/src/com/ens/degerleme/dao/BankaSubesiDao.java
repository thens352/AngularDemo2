package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.BankaSubesiSearch;
import com.ens.degerleme.model.entity.BankaSubesi;

@Stateless
public class BankaSubesiDao {

	@PersistenceContext
	EntityManager entityManager;

	BankaSubesiSearch bankaSubesiSearch;

	public void bankaSubesiKaydet(BankaSubesi bankaSubesi) {
		entityManager.persist(bankaSubesi);
	}

	public BankaSubesi bankaSubesiGuncelle(BankaSubesi bankaSubesi) {
		return entityManager.merge(bankaSubesi);
	}

	@SuppressWarnings("unchecked")
	public List<BankaSubesi> bankaSubesiGetirByBankaId(Integer id) {
		return entityManager
				.createQuery("select i from BankaSubesi i where i.banka.id=:id")
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BankaSubesi> bankaSubesiGetir(
			BankaSubesiSearch bankaSubesiSearch) {
		String sql = "select a from BankaSubesi a where 1=1";

		if (bankaSubesiSearch.getId() != null && 0 != bankaSubesiSearch.getId()) {
			sql += " and a.id=:id";
		}
		if (bankaSubesiSearch.getName() != null
				&& !!"".equals(bankaSubesiSearch.getName())) {
			sql += " and UPPER(a.name) like :name";
		}

		Query query = entityManager.createQuery(sql);

		if (bankaSubesiSearch.getId() != null && 0 != bankaSubesiSearch.getId()) {
			query.setParameter("id", bankaSubesiSearch.getId());
		}
		if (bankaSubesiSearch.getName() != null
				&& !!"".equals(bankaSubesiSearch.getName())) {
			query.setParameter("name", "%"
					+ bankaSubesiSearch.getName().toUpperCase() + "%");
		}

		return query.getResultList();
	}

	public BankaSubesi bankaSubesiGetir(Integer id) {
		return entityManager.find(BankaSubesi.class, id);
	}
}
