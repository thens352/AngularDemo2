package com.kayi.qrvale.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.kayi.qrvale.dao.search.SokakSearch;
import com.kayi.qrvale.model.entity.Sokak;

@Stateless
public class SokakDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Sokak sokakKaydet(Sokak sokak) {
		entityManager.persist(sokak);
		return sokak;
	}

	public Sokak sokakGuncelle(Sokak sokak) {
		sokak = entityManager.merge(sokak);
		return sokak;
	}
	
	public Sokak sokakSil(Sokak sokak) {
		if (entityManager.contains(sokak)) {
			entityManager.remove(sokak);
			return sokak;
		} else {
			Sokak yeniSokak = entityManager.merge(sokak);
			entityManager.remove(yeniSokak);
			return yeniSokak;
		}
	}

	public List<Sokak> sokakGetirByOrnek(SokakSearch sokakSearch) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Sokak> cq = cb.createQuery(Sokak.class);
		Root<Sokak> sokak = cq.from(Sokak.class);

		if (sokakSearch.getId() != null && sokakSearch.getId() > 0) {
			cq.where(cb.equal(sokak.get("id"), sokakSearch.getId()));
		}
		if (sokakSearch.getAdi() != null && !sokakSearch.getAdi().equals("")) {
			cq.where(cb.like(cb.lower(sokak.<String> get("adi")), "%"
					+ sokakSearch.getAdi().toLowerCase() + "%"));
		}
		TypedQuery<Sokak> q = entityManager.createQuery(cq);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Sokak> sokakGetirByAd(String adi) {

		return entityManager
				.createQuery("select i from Sokak i where i.adi=:adi")
				.setParameter("adi", adi).getResultList();

	}

	public Sokak sokakGetirById(Integer id) {

		return (Sokak) entityManager
				.createQuery("select i from Sokak i where i.id=:id")
				.setParameter("id", id).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<Sokak> hepsiniGetir() {

		return entityManager.createQuery("select i from Sokak i")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Sokak> sokakGetirByMahalleId(Integer id) {

		return entityManager
				.createQuery("select i from Sokak i where i.mahalle.id=:id")
				.setParameter("id", id).getResultList();

	}
}
