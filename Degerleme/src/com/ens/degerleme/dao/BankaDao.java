package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.BankaSearch;
import com.ens.degerleme.model.entity.Banka;

@Stateless
public class BankaDao {

	@PersistenceContext
	EntityManager entityManager;

	BankaSearch bankaSearch;

	public void bankaKaydet(Banka banka) {
		entityManager.persist(banka);
	}

	public Banka bankaGuncelle(Banka banka) {
		return entityManager.merge(banka);
	}

	@SuppressWarnings("unchecked")
	public List<Banka> hepsiniGetir() {

		return entityManager.createQuery("select i from Banka i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Banka> bankaGetir(BankaSearch bankaSearch) {
		String sql = "select a from Banka a where 1=1";

		if (bankaSearch.getId() != null && 0 != bankaSearch.getId()) {
			sql += " and a.id=:id";
		}
		if (bankaSearch.getName() != null && !!"".equals(bankaSearch.getName())) {
			sql += " and UPPER(a.name) like :name";
		}

		Query query = entityManager.createQuery(sql);

		if (bankaSearch.getId() != null && 0 != bankaSearch.getId()) {
			query.setParameter("id", bankaSearch.getId());
		}
		if (bankaSearch.getName() != null && !!"".equals(bankaSearch.getName())) {
			query.setParameter("name", "%"
					+ bankaSearch.getName().toUpperCase() + "%");
		}

		return query.getResultList();
	}

	public Banka bankaGetir(Integer id) {
		return entityManager.find(Banka.class, id);
	}
}