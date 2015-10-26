package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.BolumSearch;
import com.ens.degerleme.model.entity.Bolum;

@Stateless
public class BolumDao {

	@PersistenceContext
	EntityManager entityManager;

	public Bolum bolumSil(Integer id) {
		Bolum bolum = entityManager.find(Bolum.class, id);
		entityManager.remove(bolum);
		return bolum;
	}

	public void bolumKaydet(Bolum bolum) {

		entityManager.persist(bolum);
	}

	public Bolum bolumGuncelle(Bolum bolum) {

		return entityManager.merge(bolum);

	}

	@SuppressWarnings("unchecked")
	public List<Bolum> hepsiniGetir() {
		return entityManager.createQuery("select i from Bolum i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Bolum> bolumGetirByFormId(Integer id) {
		return entityManager
				.createQuery("select i from Bolum i where i.form.id=:id")
				.setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Bolum> bolumGetir(BolumSearch bolumSearch) {
		String sql = "select a from Bolum a where 1=1 ";

		if (bolumSearch.getId() != null && 0 != bolumSearch.getId()) {
			sql = sql + " and a.id=:id";
		}
		if (bolumSearch.getName() != null && !!"".equals(bolumSearch.getName())) {
			sql = sql + " and UPPER(a.name) like :name";
		}
		if (bolumSearch.getForm() != null
				&& bolumSearch.getForm().getName() != null
				&& !!"".equals(bolumSearch.getForm().getName())) {
			sql = sql + " and a.form.name=:formAdi";
		}

		Query query = entityManager.createQuery(sql);

		if (bolumSearch.getId() != null && 0 != bolumSearch.getId()) {
			query.setParameter("id", bolumSearch.getId());
		}
		if (bolumSearch.getName() != null && !!"".equals(bolumSearch.getName())) {
			query.setParameter("name", "%"
					+ bolumSearch.getName().toUpperCase() + "%");
		}
		if (bolumSearch.getForm() != null
				&& bolumSearch.getForm().getName() != null
				&& !!"".equals(bolumSearch.getForm().getName())) {
			query.setParameter("formAdi", "%" + bolumSearch.getForm().getName()
					+ "%");
		}

		return query.getResultList();

	}

	public Bolum bolumGetir(Integer id) {
		return entityManager.find(Bolum.class, id);
	}

}
