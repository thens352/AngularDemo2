package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.CCTV_Alarm_Klima_TV;
import com.kayiyazilim.ekentsayim.model.search.CCTV_Alarm_Klima_TVSearch;

@Stateless
public class CCTV_Alarm_Klima_TVDao extends DAO<CCTV_Alarm_Klima_TV> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5805181913285638949L;

	public CCTV_Alarm_Klima_TVDao() {
		super(CCTV_Alarm_Klima_TV.class);

	}

	public CCTV_Alarm_Klima_TV searchRest(int barkod, String cihazSeriNo) {
		try {
			if (barkod != 0 || cihazSeriNo != null && !cihazSeriNo.equals("")) {
				String sql = "select a from CCTV_Alarm_Klima_TV as a where 1=1";
				if (barkod != 0)
					sql += " and a.barkod=:barkod";
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
				Query query = entityManager.createQuery(sql);
				if (barkod != 0)
					query.setParameter("barkod", barkod);
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());

				return (CCTV_Alarm_Klima_TV) query.getSingleResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CCTV_Alarm_Klima_TV> search(CCTV_Alarm_Klima_TVSearch search) {
		String sql = "select a from CCTV_Alarm_Klima_TV as a " + " where 1=1 ";

		if (search.getBolge() != null) {
			sql += " and a.bolge=:bolge";
		}

		if (search.getDurum() != null) {
			sql += " and a.durum=:durum";
		}

		if (search.getCesit() != null && !"".equals(search.getCesit())) {
			sql += " and a.cesit=:cesit";
		}

		if (search.getKullanici() != null) {
			sql += " and a.kullanici=:kullanici";
		}

		if (search.getBarkod() != 0) {
			sql += " and a.barkod =:barkod";
		}

		if (search.getMarka() != null && !"".equals(search.getMarka())) {
			sql += " and LOWER(a.marka) like :marka";
		}

		if (search.getModel() != null && !"".equals(search.getModel())) {
			sql += " and LOWER(a.model) like :model";
		}

		if (search.getCihazSeriNo() != null
				&& !"".equals(search.getCihazSeriNo())) {
			sql += " and LOWER(a.cihazSeriNo) like :cihazSeriNo";
		}

		Query query = entityManager.createQuery(sql);

		if (search.getBolge() != null) {
			query.setParameter("bolge", search.getBolge());
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		if (search.getCesit() != null && !search.getCesit().equals("")) {
			query.setParameter("cesit", search.getCesit());
		}

		if (search.getKullanici() != null) {
			query.setParameter("kullanici", search.getKullanici());
		}

		if (search.getBarkod() != 0) {
			query.setParameter("barkod", search.getBarkod());
		}

		if (search.getMarka() != null && !search.getMarka().equals("")) {
			query.setParameter("marka", "%" + search.getMarka().toLowerCase()
					+ "%");
		}

		if (search.getModel() != null && !search.getModel().equals("")) {
			query.setParameter("model", "%" + search.getModel().toLowerCase()
					+ "%");
		}

		if (search.getCihazSeriNo() != null
				&& !search.getCihazSeriNo().equals("")) {
			query.setParameter("cihazSeriNo", "%"
					+ search.getCihazSeriNo().toLowerCase() + "%");
		}

		return query.getResultList();
	}

}
