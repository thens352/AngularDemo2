package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.Masa_Sandalye_Dolap_YanginTupu;
import com.kayiyazilim.ekentsayim.model.search.Masa_Sandalye_Dolap_YanginTupuSearch;

@Stateless
public class Masa_Sandalye_Dolap_YanginTupuDao extends
		DAO<Masa_Sandalye_Dolap_YanginTupu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 216123458912239683L;

	/**
	 * 
	 */

	public Masa_Sandalye_Dolap_YanginTupuDao() {
		super(Masa_Sandalye_Dolap_YanginTupu.class);

	}

	public Masa_Sandalye_Dolap_YanginTupu searchRest(int barkod,
			String cihazSeriNo) {
		try {
			if (barkod != 0 || cihazSeriNo != null && !cihazSeriNo.equals("")) {
				String sql = "select a from Masa_Sandalye_Dolap_YanginTupu as a where 1=1";
				if (barkod != 0)
					sql += " and a.barkod=:barkod";
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
				Query query = entityManager.createQuery(sql);
				if (barkod != 0)
					query.setParameter("barkod", barkod);
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());

				return (Masa_Sandalye_Dolap_YanginTupu) query.getSingleResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Masa_Sandalye_Dolap_YanginTupu> search(
			Masa_Sandalye_Dolap_YanginTupuSearch search) {
		String sql = "select a from Masa_Sandalye_Dolap_YanginTupu as a "
				+ " where 1=1 ";

		if (search.getBolge() != null) {
			sql += " and a.bolge=:bolge";
		}

		if (search.getDurum() != null) {
			sql += " and a.durum=:durum";
		}

		if (search.getKullanici() != null) {
			sql += " and a.kullanici=:kullanici";
		}

		if (search.getBarkod() != 0) {
			sql += " and a.barkod =:barkod";
		}

		if (search.getCesit() != null && !"".equals(search.getCesit())) {
			sql += " and a.cesit=:cesit";
		}

		Query query = entityManager.createQuery(sql);

		if (search.getBolge() != null) {
			query.setParameter("bolge", search.getBolge());
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		if (search.getKullanici() != null) {
			query.setParameter("kullanici", search.getKullanici());
		}

		if (search.getBarkod() != 0) {
			query.setParameter("barkod", search.getBarkod());
		}

		if (search.getCesit() != null && !search.getCesit().equals("")) {
			query.setParameter("cesit", search.getCesit());
		}

		return query.getResultList();
	}

}
