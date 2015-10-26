package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ens.degerleme.model.entity.Mahalle;
import com.ens.degerleme.dao.searchobject.MahalleSearch;

@Stateless
public class MahalleDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Mahalle mahalleKaydet(Mahalle mahalle) {
		entityManager.persist(mahalle);
		return mahalle;
	}

	public Mahalle mahalleSil(Mahalle mahalle) {
		if (entityManager.contains(mahalle)) {
			entityManager.remove(mahalle);
			return mahalle;
		} else {
			Mahalle yeniMahalle = entityManager.merge(mahalle);
			entityManager.remove(yeniMahalle);
			return yeniMahalle;
		}
	}
	
	public Mahalle mahalleGuncelle(Mahalle mahalle) {
		mahalle = entityManager.merge(mahalle);
		return mahalle;
	}

	public List<Mahalle> mahalleGetirByOrnek(MahalleSearch mahalleSearch) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mahalle> cq = cb.createQuery(Mahalle.class);
		Root<Mahalle> mahalle = cq.from(Mahalle.class);

		if (mahalleSearch.getId() != null && mahalleSearch.getId() > 0) {
			cq.where(cb.equal(mahalle.get("id"), mahalleSearch.getId()));
		}
		if (mahalleSearch.getAdi() != null
				&& !mahalleSearch.getAdi().equals("")) {
			cq.where(cb.like(cb.lower(mahalle.<String> get("adi")), "%"
					+ mahalleSearch.getAdi().toLowerCase() + "%"));
		}

		TypedQuery<Mahalle> q = entityManager.createQuery(cq);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Mahalle> mahalleGetirByAd(String adi) {

		return entityManager
				.createQuery("select i from Mahalle i where i.adi=:adi")
				.setParameter("adi", adi).getResultList();

	}

	public Mahalle mahalleGetirById(Integer id) {

		return (Mahalle) entityManager
				.createQuery("select i from Mahalle i where i.id=:id")
				.setParameter("id", id).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<Mahalle> hepsiniGetir() {

		return entityManager.createQuery("select i from Mahalle i")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Mahalle> mahalleGetirByIlceId(Integer id) {

		return entityManager
				.createQuery("select i from Mahalle i where i.ilce.id=:id")
				.setParameter("id", id).getResultList();

	}
}
