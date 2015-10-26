package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.POS;
import com.kayiyazilim.ekentsayim.model.search.POSSearch;

@Stateless
public class POSDao extends DAO<POS> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9123601383503393647L;

	/**
	 * 
	 */

	public POSDao() {
		super(POS.class);

	}

	public POS searchRest(int barkod, String cihazSeriNo) {
		try {
			if (barkod != 0 || cihazSeriNo != null && !cihazSeriNo.equals("")) {
				String sql = "select a from POS as a where 1=1";
				if (barkod != 0)
					sql += " and a.barkod=:barkod";
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
				Query query = entityManager.createQuery(sql);
				if (barkod != 0)
					query.setParameter("barkod", barkod);
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());

				return (POS) query.getSingleResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<POS> search(POSSearch search) {
		String sql = "select a from POS as a " + " where 1=1";

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

		if (search.getPadCihazSeriNo() != null
				&& !"".equals(search.getPadCihazSeriNo())) {
			sql += " and LOWER(a.padCihazSeriNo) like :padCihazSeriNo";
		}

		if (search.getMarka() != null && !"".equals(search.getMarka())) {
			sql += " and LOWER(a.marka) like :marka";
		}

		if (search.getModel() != null && !"".equals(search.getModel())) {
			sql += " and LOWER(a.model) like :model";
		}

		if (search.getBulunduguBayiTicariAdi() != null
				&& !"".equals(search.getBulunduguBayiTicariAdi())) {
			sql += " and LOWER(a.bulunduguBayiTicariAdi) like :bulunduguBayiTicariAdi";
		}

		if (search.getAnaEkipman() != 0) {
			sql += " and a.anaEkipman =:anaEkipman";
		}

		if (search.getDurum() != null) {
			sql += " and a.durum=:durum";
		}

		if (!(search.isPadCihazVar() && search.isPadCihazYok())) {
			if (search.isPadCihazVar()) {
				sql += " and a.padCihaziVarMi=true";
			}
			if (search.isPadCihazYok()) {
				sql += " and a.padCihaziVarMi=false";
			}
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

		if (search.getPadCihazSeriNo() != null
				&& !search.getPadCihazSeriNo().equals("")) {
			query.setParameter("padCihazSeriNo", "%"
					+ search.getPadCihazSeriNo().toLowerCase() + "%");
		}

		if (search.getMarka() != null && !search.getMarka().equals("")) {
			query.setParameter("marka", "%" + search.getMarka().toLowerCase()
					+ "%");
		}

		if (search.getModel() != null && !search.getModel().equals("")) {
			query.setParameter("model", "%" + search.getModel().toLowerCase()
					+ "%");
		}

		if (search.getBulunduguBayiTicariAdi() != null
				&& !search.getBulunduguBayiTicariAdi().equals("")) {
			query.setParameter("bulunduguBayiTicariAdi", "%"
					+ search.getBulunduguBayiTicariAdi().toLowerCase() + "%");
		}

		if (search.getAnaEkipman() != 0) {
			query.setParameter("anaEkipman", search.getAnaEkipman());
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		return query.getResultList();

	}
}