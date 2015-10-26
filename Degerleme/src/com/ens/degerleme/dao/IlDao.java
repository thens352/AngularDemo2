package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.ens.degerleme.model.entity.Il;

@Stateless
public class IlDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Il ilKaydet(Il il) {
		entityManager.persist(il);
		return il;
	}

	public Il ilSil(Il il) {
		entityManager.remove(ilGuncelle(il));
		return il;
	}

	public Il ilGuncelle(Il il) {
		il = entityManager.merge(il);
		return il;
	}

	public Il ilGetirById(Long id) {
		return entityManager.find(Il.class, id);
	}

	public Il ilGetirById(Integer id) {

		return (Il) entityManager
				.createQuery("select i from Il i where i.id=:id")
				.setParameter("id", id).getSingleResult();

	}

	public Il ilGetirByAd(String adi) {
		try {
			return (Il) entityManager
					.createQuery("select i from Il i where i.adi=:adi")
					.setParameter("adi", adi).getSingleResult();
		} catch (NoResultException e) {
			e.getMessage();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Il> hepsiniGetir() {

		return entityManager.createQuery("select i from Il i").getResultList();

	}

}
