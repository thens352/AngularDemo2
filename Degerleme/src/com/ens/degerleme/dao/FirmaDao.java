package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.FirmaSearch;
import com.ens.degerleme.model.entity.Firma;

@Stateless
public class FirmaDao {

	FirmaSearch firmaSearch;

	@PersistenceContext
	EntityManager entityManager;

	public void firmaKaydet(Firma firma) {
		entityManager.persist(firma);
	}

	public Firma firmaGuncelle(Firma firma) {
		return entityManager.merge(firma);
	}

	@SuppressWarnings("unchecked")
	public List<Firma> hepsiniGetir() {

		return entityManager.createQuery("select i from Firma i")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Firma> firmaGetir(FirmaSearch firmaSearch) {
		String sql = "select a from Firma a where 1=1";

		if (firmaSearch.getId() != null && 0 != firmaSearch.getId()) {
			sql += "and a.id=id";
		}
		if (firmaSearch.getName() != null && !!"".equals(firmaSearch.getName())) {
			sql += "and UPPER(a.name) like :name";
		}

		Query query = entityManager.createQuery(sql);

		if (firmaSearch.getId() != null && 0 != firmaSearch.getId()) {
			query.setParameter("id", firmaSearch.getId());
		}
		if (firmaSearch.getName() != null && !!"".equals(firmaSearch.getName())) {
			query.setParameter("name", "%"
					+ firmaSearch.getName().toUpperCase() + "%");
		}
		return query.getResultList();
	}

	public Firma firmaGetir(Integer id) {
		return entityManager.find(Firma.class, id);
	}
}
