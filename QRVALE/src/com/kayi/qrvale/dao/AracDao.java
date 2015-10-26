package com.kayi.qrvale.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kayi.qrvale.dao.search.AracSearch;
import com.kayi.qrvale.model.entity.Arac;

@Stateless
public class AracDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Arac AracKaydet(Arac arac) {
		entityManager.persist(arac);
		return arac;
	}

	public Arac AracSil(Arac arac) {
		if (entityManager.contains(arac)) {
			entityManager.remove(arac);
			return arac;
		} else {
			Arac yeniArac = entityManager.merge(arac);
			entityManager.remove(yeniArac);
			return yeniArac;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Arac> aracGetir(AracSearch aracSearch) {

		String sql = "select a from Arac as a " + " where 1=1 ";

		if (aracSearch.getPlaka() != null && !"".equals(aracSearch.getPlaka())) {
			sql += " and UPPER(a.plaka) like :plaka";
		}

		if (aracSearch.getMarka() != null) {
			sql = sql + " and a.marka=:marka";
		}

		if (aracSearch.getMarka() != null) {
			sql = sql + " and UPPER(a.model) like :model";
		}

		if (aracSearch.getRenk() != null) {
			sql = sql + " and a.renk=:renk";
		}

		if (aracSearch.getIsletme() != null) {
			sql = sql + " and a.isletme=:isletme";
		}

		Query query = entityManager.createQuery(sql);

		if (aracSearch.getPlaka() != null && !"".equals(aracSearch.getPlaka())) {
			query.setParameter("plaka", "%"
					+ aracSearch.getPlaka().toUpperCase() + "%");
		}

		if (aracSearch.getMarka() != null) {
			query.setParameter("marka", aracSearch.getMarka());
		}

		if (aracSearch.getMarka() != null) {
			query.setParameter("model", "%"
					+ aracSearch.getModel().toUpperCase() + "%");
		}

		if (aracSearch.getRenk() != null) {
			query.setParameter("renk", aracSearch.getRenk());
		}

		if (aracSearch.getIsletme() != null) {
			query.setParameter("isletme", aracSearch.getIsletme());
		}

		return query.getResultList();
	}

	public Arac AracGuncelle(Arac arac) {
		arac = entityManager.merge(arac);
		return arac;
	}

	public Arac AracGetirById(Integer id) {
		return (Arac) entityManager
				.createQuery("select a from Arac a where a.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Arac> hepsiniGetir() {
		return entityManager.createQuery("select i from Arac i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Arac> AracGetirByIsletmeId(Integer id) {
		return entityManager
				.createQuery("select i from Arac i where i.isletme.id=:id")
				.setParameter("id", id).getResultList();

	}

	public Arac AracGetirByPlaka(String plaka) {
		try {
			return (Arac) entityManager
					.createQuery("select a from Arac a where a.plaka=:plaka")
					.setParameter("plaka", plaka).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
