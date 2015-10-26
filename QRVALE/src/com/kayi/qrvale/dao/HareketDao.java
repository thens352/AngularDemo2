package com.kayi.qrvale.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kayi.qrvale.dao.search.HareketSearch;
import com.kayi.qrvale.model.entity.Hareket;

@Stateless
public class HareketDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Hareket hareketKaydet(Hareket hareket) {
		entityManager.persist(hareket);
		return hareket;
	}

	public Hareket hareketSil(Hareket hareket) {
		if (entityManager.contains(hareket)) {
			entityManager.remove(hareket);
			return hareket;
		} else {
			Hareket yeniHareket = entityManager.merge(hareket);
			entityManager.remove(yeniHareket);
			return yeniHareket;
		}
	}

	public Hareket hareketGuncelle(Hareket hareket) {
		hareket = entityManager.merge(hareket);
		return hareket;
	}

	public Hareket suankiHareket(Integer valeID) {
		try {
			return (Hareket) entityManager
					.createQuery(
							"select i from Hareket i where i.verenVale.id=:valeID and i.tamamlanmaTarihi=null")
					.setParameter("valeID", valeID).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Hareket> HareketGetir(HareketSearch hareketSearch) {

		String sql = "select a from Hareket as a " + " where 1=1 ";

		if (hareketSearch.getArac() != null) {
			sql += " and a.arac=:arac";
		}

		if (hareketSearch.getMinAlisTarihi() != null) {
			sql += " and a.alisTarihi>=:minAlisTarihi";
		}

		if (hareketSearch.getMaxAlisTarihi() != null) {
			sql += " and a.alisTarihi<=:maxAlisTarihi";
		}

		if (hareketSearch.getAlanVale() != null) {
			sql += " and a.alanVale=:alanVale";
		}

		if (hareketSearch.getMinIstekTarihi() != null) {
			sql += " and a.istekTarihi>=:minIstekTarihi";
		}

		if (hareketSearch.getMaxIstekTarihi() != null) {
			sql += " and a.istekTarihi<=:maxIstekTarihi";
		}

		if (hareketSearch.getMinVerisTarihi() != null) {
			sql += " and a.verisTarihi>=:minVerisTarihi";
		}

		if (hareketSearch.getMaxVerisTarihi() != null) {
			sql += " and a.verisTarihi<=:maxVerisTarihi";
		}

		if (hareketSearch.getVerenVale() != null) {
			sql += " and a.verenVale=:verenVale";
		}

		if (hareketSearch.getMinTamamlanmaTarihi() != null) {
			sql += " and a.tamamlanmaTarihi>=:minTamamlanmaTarihi";
		}

		if (hareketSearch.getMaxTamamlanmaTarihi() != null) {
			sql += " and a.tamamlanmaTarihi<=:maxTamamlanmaTarihi";
		}

		if (hareketSearch.getIsletme() != null) {
			sql += " and a.isletme=:isletme";
		}

		Query query = entityManager.createQuery(sql);

		if (hareketSearch.getArac() != null) {
			query.setParameter("arac", hareketSearch.getArac());
		}

		if (hareketSearch.getMinAlisTarihi() != null) {
			query.setParameter("minAlisTarihi",
					hareketSearch.getMinAlisTarihi());
		}

		if (hareketSearch.getMaxAlisTarihi() != null) {
			query.setParameter("maxAlisTarihi",
					hareketSearch.getMaxAlisTarihi());
		}

		if (hareketSearch.getAlanVale() != null) {
			query.setParameter("alanVale", hareketSearch.getAlanVale());
		}

		if (hareketSearch.getMinIstekTarihi() != null) {
			query.setParameter("minIstekTarihi",
					hareketSearch.getMinIstekTarihi());
		}

		if (hareketSearch.getMaxIstekTarihi() != null) {
			query.setParameter("maxIstekTarihi",
					hareketSearch.getMaxIstekTarihi());
		}

		if (hareketSearch.getMinVerisTarihi() != null) {
			query.setParameter("minVerisTarihi",
					hareketSearch.getMinVerisTarihi());
		}

		if (hareketSearch.getMaxVerisTarihi() != null) {
			query.setParameter("maxVerisTarihi",
					hareketSearch.getMaxVerisTarihi());
		}

		if (hareketSearch.getVerenVale() != null) {
			query.setParameter("verenVale", hareketSearch.getVerenVale());
		}

		if (hareketSearch.getMinTamamlanmaTarihi() != null) {
			query.setParameter("minTamamlanmaTarihi",
					hareketSearch.getMinTamamlanmaTarihi());
		}

		if (hareketSearch.getMaxTamamlanmaTarihi() != null) {
			query.setParameter("maxTamamlanmaTarihi",
					hareketSearch.getMaxTamamlanmaTarihi());
		}

		if (hareketSearch.getIsletme() != null) {
			query.setParameter("isletme", hareketSearch.getIsletme());
		}

		return query.getResultList();
	}

	public Hareket HareketGetirById(Integer id) {
		return (Hareket) entityManager
				.createQuery("select a from Hareket a where a.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Hareket> hepsiniGetir() {
		return entityManager.createQuery("select i from Hareket i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Hareket> HareketGetirByIsletmeId(Integer id) {
		return entityManager
				.createQuery("select i from Hareket i where i.isletme.id=:id")
				.setParameter("id", id).getResultList();

	}
}
