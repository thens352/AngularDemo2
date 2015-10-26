package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.Kullanici;
import com.kayiyazilim.ekentsayim.model.search.KullaniciSearch;

@Stateless
public class KullaniciDao extends DAO<Kullanici> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6754087768103513816L;

	public KullaniciDao() {
		super(Kullanici.class);

	}

	public Kullanici login(String kullaniciAdi) {
		try {
			return (Kullanici) entityManager
					.createQuery(
							"select k from Kullanici k where k.kullaniciAdi=:kullaniciAdi")
					.setParameter("kullaniciAdi", kullaniciAdi)
					.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Kullanici> search(KullaniciSearch search) {
		String sql = "select a from Kullanici as a " + " where 1=1 ";

		if (search.getAd() != null && !"".equals(search.getAd())) {
			sql += " and LOWER(a.ad) like :ad";
		}

		if (search.getSoyAd() != null && !"".equals(search.getSoyAd())) {
			sql += " and LOWER(a.soyad) like :soyad";
		}

		if (search.getKullaniciAdi() != null
				&& !"".equals(search.getKullaniciAdi())) {
			sql += " and LOWER(a.kullaniciadi) like :kullaniciadi";
		}

		Query query = entityManager.createQuery(sql);

		if (search.getAd() != null && !search.getAd().equals("")) {
			query.setParameter("ad", "%" + search.getAd().toLowerCase() + "%");
		}

		if (search.getSoyAd() != null && !search.getSoyAd().equals("")) {
			query.setParameter("soyad", "%" + search.getSoyAd().toLowerCase()
					+ "%");
		}

		if (search.getKullaniciAdi() != null
				&& !search.getKullaniciAdi().equals("")) {
			query.setParameter("kullaniciadi", "%"
					+ search.getKullaniciAdi().toLowerCase() + "%");
		}

		return query.getResultList();
	}
}
