package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.Parkomat;
import com.kayiyazilim.ekentsayim.model.search.ParkomatSearch;

@Stateless
public class ParkomatDao extends DAO<Parkomat> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1899084187961021900L;

	/**
	 * 
	 */

	public ParkomatDao() {
		super(Parkomat.class);

	}

	public Parkomat searchRest(int barkod, String cihazSeriNo) {
		try {
			if (barkod != 0 || cihazSeriNo != null && !cihazSeriNo.equals("")) {
				String sql = "select a from Parkomat as a where 1=1";
				if (barkod != 0)
					sql += " and a.barkod=:barkod";
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
				Query query = entityManager.createQuery(sql);
				if (barkod != 0)
					query.setParameter("barkod", barkod);
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());

				return (Parkomat) query.getSingleResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Parkomat> search(ParkomatSearch search) {
		String sql = "select a from Parkomat as a " + " where 1=1";

		if (search.getBolge() != null) {
			sql += " and a.bolge=:bolge";
		}

		if (search.getKullanici() != null) {
			sql += " and a.kullanici=:kullanici";
		}

		if (search.getBarkod() != 0) {
			sql += " and a.barkod =:barkod";
		}

		if (search.getCihazSeriNo() != null
				&& !"".equals(search.getCihazSeriNo())) {
			sql += " and LOWER(a.cihazSeriNo) like :cihazSeriNo";
		}

		if (search.getMarka() != null && !"".equals(search.getMarka())) {
			sql += " and LOWER(a.marka) like :marka";
		}

		if (search.getDurakAdi() != null && !"".equals(search.getDurakAdi())) {
			sql += " and LOWER(a.durakAdi) like :durakAdi";
		}

		if (search.getDurum() != null) {
			sql += " and a.durum=:durum";
		}

		Query query = entityManager.createQuery(sql);

		if (search.getBolge() != null) {
			query.setParameter("bolge", search.getBolge());
		}

		if (search.getKullanici() != null) {
			query.setParameter("kullanici", search.getKullanici());
		}

		if (search.getBarkod() != 0) {
			query.setParameter("barkod", search.getBarkod());
		}

		if (search.getCihazSeriNo() != null
				&& !search.getCihazSeriNo().equals("")) {
			query.setParameter("cihazSeriNo", "%"
					+ search.getCihazSeriNo().toLowerCase() + "%");
		}

		if (search.getMarka() != null && !search.getMarka().equals("")) {
			query.setParameter("marka", "%" + search.getMarka().toLowerCase()
					+ "%");
		}

		if (search.getDurakAdi() != null && !search.getDurakAdi().equals("")) {
			query.setParameter("durakAdi", "%"
					+ search.getDurakAdi().toLowerCase() + "%");
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		return query.getResultList();

	}

}