package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.IsEmriSearch;
import com.ens.degerleme.model.entity.IsEmri;

@Stateless
public class IsEmriDao {

	@PersistenceContext
	EntityManager entityManager;

	public void isEmriKaydet(IsEmri isEmri) {
		entityManager.persist(isEmri);
	}

	public IsEmri isEmriGuncelle(IsEmri isEmri) {
		return entityManager.merge(isEmri);
	}

	@SuppressWarnings("unchecked")
	public List<IsEmri> isEmriGetir(IsEmriSearch isEmriSearch) {
		String sql = "select a from IsEmri a where 1=1";

		if (isEmriSearch.getId() != null && 0 != isEmriSearch.getId()) {
			sql = sql + " and a.id=:id";
		}

		if (isEmriSearch.getBanka() != null) {
			sql = sql + " and a.banka=:banka";
		}

		if (isEmriSearch.getKisi()!=null) {
			sql = sql + " and a.kisi=:kisi";
		}



		if (isEmriSearch.getFirma()!= null) {
			sql = sql + " and a.firma=:firma";
		}

		if (isEmriSearch.getTarih() != null){
			sql = sql + " and a.tarih=:tarih";
		}

		if (isEmriSearch.getKontrolEdenUser()!= null) {
			sql = sql + " and a.kontrolEdenUser=:kontrolEdenUser";
		}


		if (isEmriSearch.getAtananUser() != null) {
			sql = sql + " and a.atananUser=:atananUser";
		}


		if (isEmriSearch.getIsEmriDurumu() != null) {
			sql = sql + " and a.isEmriDurumu=:isEmriDurumu";
		}

		Query query = entityManager.createQuery(sql);

		if (isEmriSearch.getId() != null && 0 != isEmriSearch.getId()) {
			query.setParameter("id", isEmriSearch.getId());
		}

		if (isEmriSearch.getBanka() != null) {
			query.setParameter("banka", isEmriSearch.getBanka());
		}

		if (isEmriSearch.getKisi() != null) {
			query.setParameter("kisi", isEmriSearch.getKisi());
		}

		if (isEmriSearch.getFirma() != null) {
			query.setParameter("firma", isEmriSearch.getFirma());
		}

		if (isEmriSearch.getTarih() != null) {
			query.setParameter("tarih", isEmriSearch.getTarih());
		}

		if (isEmriSearch.getKontrolEdenUser() != null) {
			query.setParameter("kontrolEdenUser", isEmriSearch.getKontrolEdenUser());
		}

		if (isEmriSearch.getAtananUser() != null) {
			query.setParameter("atananUser", isEmriSearch.getAtananUser());
		}

		if (isEmriSearch.getIsEmriDurumu() != null) {
			query.setParameter("isEmriDurumu", isEmriSearch.getIsEmriDurumu());
		}

		return query.getResultList();
	}

	public IsEmri isEmriGetir(Integer id) {
		return entityManager.find(IsEmri.class, id);
	}
}
