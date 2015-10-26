package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.KsGucUnitesi;
import com.kayiyazilim.ekentsayim.model.search.KsGucUnitesiSearch;

@Stateless
public class KsGucUnitesiDao extends DAO<KsGucUnitesi> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6503608119735444873L;

	/**
	 * 
	 */

	public KsGucUnitesiDao() {
		super(KsGucUnitesi.class);

	}
	
	public KsGucUnitesi searchRest(int barkod, String cihazSeriNo) {
		try {
			if(barkod!=0 || cihazSeriNo != null && !cihazSeriNo.equals("")){
			String sql = "select a from KsGucUnitesi as a where 1=1";
			if (barkod!=0)
				sql += " and a.barkod=:barkod";
			if (cihazSeriNo != null && !cihazSeriNo.equals(""))
				sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
			Query query = entityManager.createQuery(sql);
			if (barkod!=0)
				query.setParameter("barkod", barkod);
			if (cihazSeriNo != null && !cihazSeriNo.equals(""))
				query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());
			
			return (KsGucUnitesi) query.getSingleResult();}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<KsGucUnitesi> search(KsGucUnitesiSearch search) {
		String sql = "select a from KsGucUnitesi as a " + " where 1=1";

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

		if (search.getModel() != null && !"".equals(search.getModel())) {
			sql += " and LOWER(a.model) like :model";
		}

		if (search.getOtobusKoseNo() != null
				&& !"".equals(search.getOtobusKoseNo())) {
			sql += " and LOWER(a.otobusKoseNo) like :otobusKoseNo";
		}

		if (search.getDurum() != null) {
			sql += " and a.durum=:durum";
		}

		if (search.getCihaz() != null) {
			sql += " and a.cihaz=:cihaz";
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

		if (search.getModel() != null && !search.getModel().equals("")) {
			query.setParameter("model", "%" + search.getModel().toLowerCase()
					+ "%");
		}

		if (search.getOtobusKoseNo() != null
				&& !search.getOtobusKoseNo().equals("")) {
			query.setParameter("otobusKoseNo", "%"
					+ search.getOtobusKoseNo().toLowerCase() + "%");
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		if (search.getCihaz() != null) {
			query.setParameter("cihaz", search.getCihaz());
		}

		return query.getResultList();

	}

}
