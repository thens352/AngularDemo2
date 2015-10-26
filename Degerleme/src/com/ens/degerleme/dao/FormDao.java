package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.FormSearch;
import com.ens.degerleme.model.entity.Form;

@Stateless
public class FormDao {

	@PersistenceContext
	EntityManager entityManager;

	public Form formSil(Integer id) {
		Form form = entityManager.find(Form.class, id);
		entityManager.remove(form);
		return form;
	}

	public void formKaydet(Form form) {
		entityManager.persist(form);
	}

	public Form formGuncelle(Form form) {
		return entityManager.merge(form);
	}

	@SuppressWarnings("unchecked")
	public List<Form> hepsiniGetir() {
		return entityManager.createQuery("select i from Form i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Form> formGetir(FormSearch formSearch) {
		String sql = "select a from Form a where 1=1 ";

		if (formSearch.getId() != null && 0 != formSearch.getId()) {
			sql = sql + " and a.id=:id";
		}
		if (formSearch.getName() != null && !!"".equals(formSearch.getName())) {
			sql = sql + " and UPPER(a.name) like :name";
		}

		Query query = entityManager.createQuery(sql);

		if (formSearch.getId() != null && 0 != formSearch.getId()) {
			query.setParameter("id", formSearch.getId());
		}
		if (formSearch.getName() != null && !!"".equals(formSearch.getName())) {
			query.setParameter("name", "%" + formSearch.getName().toUpperCase()
					+ "%");
		}
		return query.getResultList();
	}

	public Form formGetir(Integer id) {
		return entityManager.find(Form.class, id);
	}

}
