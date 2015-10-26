package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.RaporSearch;
import com.ens.degerleme.model.entity.Rapor;

@Stateless
public class RaporDao {

	@PersistenceContext
	EntityManager entityManager;

	public void raporKaydet(Rapor rapor) {
		entityManager.persist(rapor);
	}

	public Rapor raporGuncelle(Rapor rapor) {
		return entityManager.merge(rapor);
	}

	public Rapor raporGetir(Integer id) {
		return entityManager.find(Rapor.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<Rapor> raporGetir(RaporSearch raporSearch) {

		String sql = "select a from Rapor as a "
				+ " join a.isEmri as i where 1=1 ";

		if (raporSearch.getFirmaAdi() != null
				&& !!"".equals(raporSearch.getFirmaAdi())) {
			sql = sql + " and i.firma.name=:firmaAdi";
		}

		if (raporSearch.getKisiAdi() != null
				&& !!"".equals(raporSearch.getKisiAdi())) {
			sql = sql + " and i.kisi.name=:kisiAdi";
		}

		if (raporSearch.getKisiSoyadi() != null
				&& !!"".equals(raporSearch.getKisiSoyadi())) {
			sql = sql + " and i.kisi.surName=:kisiSoyadi";
		}

		if (raporSearch.getRaporNo() != null && 0 != raporSearch.getRaporNo()) {
			sql = sql + " and a.id=:id";
		}

		if (raporSearch.getSigortaAdi() != null
				&& !!"".equals(raporSearch.getSigortaAdi())) {
			sql = sql
					+ " and i.banka.name LIKE :sigortaAdi or i.bankaSubesi.name LIKE :sigortaAdi";
		}

		if (raporSearch.getRaporTarihi() != null) {
			sql = sql + " and a.raporTarihi =:raporTarihi";
		}

		if (raporSearch.getIsEmriTarihi() != null) {
			sql = sql + " and i.tarih =:isEmriTarihi";
		}

		Query query = entityManager.createQuery(sql);

		if (raporSearch.getFirmaAdi() != null
				&& !!"".equals(raporSearch.getFirmaAdi())) {
			query.setParameter("firmaAdi", raporSearch.getFirmaAdi());
		}

		if (raporSearch.getKisiAdi() != null
				&& !!"".equals(raporSearch.getKisiAdi())) {
			query.setParameter("kisiAdi", raporSearch.getKisiAdi());
		}

		if (raporSearch.getKisiSoyadi() != null
				&& !!"".equals(raporSearch.getKisiSoyadi())) {
			query.setParameter("kisiSoyadi", raporSearch.getKisiSoyadi());
		}

		if (raporSearch.getRaporNo() != null && 0 != raporSearch.getRaporNo()) {
			query.setParameter("id", raporSearch.getRaporNo());
		}

		if (raporSearch.getSigortaAdi() != null
				&& !!"".equals(raporSearch.getSigortaAdi())) {
			query.setParameter("sigortaAdi", raporSearch.getSigortaAdi());
		}

		if (raporSearch.getRaporTarihi() != null) {
			query.setParameter("raporTarihi", raporSearch.getRaporTarihi());
		}

		if (raporSearch.getIsEmriTarihi() != null) {
			query.setParameter("isEmriTarihi", raporSearch.getIsEmriTarihi());
		}

		return query.getResultList();
	}
}
