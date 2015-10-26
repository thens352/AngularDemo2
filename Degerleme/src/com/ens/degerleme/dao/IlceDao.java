package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ens.degerleme.model.entity.Ilce;
import com.ens.degerleme.dao.searchobject.IlceSearch;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IlceDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Ilce ilceKaydet(Ilce ilce) {
		entityManager.persist(ilce);
		return ilce;
	}

	public Ilce ilceSil(Ilce ilce) {
		if (entityManager.contains(ilce)) {
			entityManager.remove(ilce);
			return ilce;
		} else {
			Ilce yeniIlce = entityManager.merge(ilce);
			entityManager.remove(yeniIlce);
			return yeniIlce;
		}
	}

	public Ilce ilceGuncelle(Ilce ilce) {
		ilce = entityManager.merge(ilce);
		return ilce;
	}

	public List<Ilce> ilceGetirByOrnek(IlceSearch ilceSearch) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ilce> cq = cb.createQuery(Ilce.class);
		Root<Ilce> ilce = cq.from(Ilce.class);

		if (ilceSearch.getId() != null && ilceSearch.getId() > 0) {
			cq.where(cb.equal(ilce.get("id"), ilceSearch.getId()));
		}
		if (ilceSearch.getAdi() != null && !ilceSearch.getAdi().equals("")) {
			cq.where(cb.like(cb.lower(ilce.<String> get("adi")), "%"
					+ ilceSearch.getAdi().toLowerCase() + "%"));
		}

		TypedQuery<Ilce> q = entityManager.createQuery(cq);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ilce> ilceGetirByAd(String adi) {

		return entityManager
				.createQuery("select i from Ilce i where i.adi=:adi")
				.setParameter("adi", adi).getResultList();

	}

	public Ilce ilceGetirById(Integer id) {

		return (Ilce) entityManager
				.createQuery("select i from Ilce i where i.id=:id")
				.setParameter("id", id).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<Ilce> hepsiniGetir() {

		return entityManager.createQuery("select i from Ilce i")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Ilce> ilceGetirByIlId(Integer id) {

		return entityManager
				.createQuery("select i from Ilce i where i.il.id=:id")
				.setParameter("id", id).getResultList();

	}

}
