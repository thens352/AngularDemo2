package com.kayi.qrvale.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kayi.qrvale.dao.search.ValeSearch;
import com.kayi.qrvale.model.entity.Vale;

@Stateless
public class ValeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Vale valeKaydet(Vale vale) {
		entityManager.persist(vale);
		return vale;
	}

	public Vale valeSil(Vale vale) {
		if (entityManager.contains(vale)) {
			entityManager.remove(vale);
			return vale;
		} else {
			Vale yeniVale = entityManager.merge(vale);
			entityManager.remove(yeniVale);
			return yeniVale;
		}
	}

	public Vale valeGuncelle(Vale vale) {
		vale = entityManager.merge(vale);
		return vale;
	}

	@SuppressWarnings("unchecked")
	public List<Vale> valeGetirByIsletmeId(Integer id) {
		return entityManager
				.createQuery("select i from Vale i where i.isletme.id=:id")
				.setParameter("id", id).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Vale> valeGetir(ValeSearch valeSearch) {

		String sql = "select a from Vale as a " + " where 1=1 ";

		if (valeSearch.getAd() != null && !"".equals(valeSearch.getAd())) {
			sql += " and UPPER(a.ad) like :ad";
		}

		if (valeSearch.getSoyad() != null && !"".equals(valeSearch.getSoyad())) {
			sql += " and UPPER(a.soyad) like :soyad";
		}

		if (valeSearch.getKullaniciAdi() != null
				&& !"".equals(valeSearch.getKullaniciAdi())) {
			sql += " and UPPER(a.kullaniciAdi) like :kullaniciAdi";
		}

		Query query = entityManager.createQuery(sql);

		if (valeSearch.getAd() != null && !"".equals(valeSearch.getAd())) {
			query.setParameter("ad", "%" + valeSearch.getAd().toUpperCase()
					+ "%");
		}

		if (valeSearch.getSoyad() != null && !"".equals(valeSearch.getSoyad())) {
			query.setParameter("soyad", "%"
					+ valeSearch.getSoyad().toUpperCase() + "%");
		}

		if (valeSearch.getKullaniciAdi() != null
				&& !"".equals(valeSearch.getKullaniciAdi())) {
			query.setParameter("kullaniciAdi", "%"
					+ valeSearch.getKullaniciAdi().toUpperCase() + "%");
		}

		return query.getResultList();
	}

	public Vale ValeGetirById(Integer id) {
		try {
			return (Vale) entityManager
					.createQuery("select i from Vale i where i.id=:id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Vale ValeGetirByKullaniciAdi(String kullaniciAdi) {

		try {
			return (Vale) entityManager
					.createQuery(
							"select i from Vale i where i.kullaniciAdi =:kullaniciAdi")
					.setParameter("kullaniciAdi", kullaniciAdi)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Vale> hepsiniGetir() {

		return entityManager.createQuery("select i from Vale i")
				.getResultList();

	}
}
