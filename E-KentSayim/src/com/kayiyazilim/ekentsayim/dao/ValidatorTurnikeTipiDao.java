package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.ValidatorTurnikeTipi;
import com.kayiyazilim.ekentsayim.model.search.ValidatorTurnikeTipiSearch;

@Stateless
public class ValidatorTurnikeTipiDao extends DAO<ValidatorTurnikeTipi> {

	private static final long serialVersionUID = 3158029259066054879L;

	public ValidatorTurnikeTipiDao() {
		super(ValidatorTurnikeTipi.class);

	}

	public ValidatorTurnikeTipi searchRest(int barkod, String cihazSeriNo) {
		try {
			if(barkod!=0 || cihazSeriNo != null && !cihazSeriNo.equals("")){
				String sql = "select a from ValidatorTurnikeTipi as a where 1=1";
				if (barkod!=0)
					sql += " and a.barkod=:barkod";
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
				Query query = entityManager.createQuery(sql);
				if (barkod!=0)
					query.setParameter("barkod", barkod);
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());
				
				return (ValidatorTurnikeTipi) query.getSingleResult();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ValidatorTurnikeTipi> search(ValidatorTurnikeTipiSearch search) {
		String sql = "select a from ValidatorTurnikeTipi as a " + " where 1=1 ";

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

		if (search.getTurnikeNo() != null && !"".equals(search.getTurnikeNo())) {
			sql += " and LOWER(a.turnikeNo) like :turnikeNo";
		}

		if (search.getMarka() != null && !"".equals(search.getMarka())) {
			sql += " and a.marka = :marka";
		}

		if (search.getModel() != null && !"".equals(search.getModel())) {
			sql += " and a.model = :model";
		}

		if (search.getIstasyon_DurakAdi() != null
				&& !"".equals(search.getIstasyon_DurakAdi())) {
			sql += " and LOWER(a.istasyon_DurakAdi) like :istasyon_DurakAdi";
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
			query.setParameter("marka", search.getMarka());
		}

		if (search.getTurnikeNo() != null && !search.getTurnikeNo().equals("")) {
			query.setParameter("turnikeNo", "%"
					+ search.getTurnikeNo().toLowerCase() + "%");
		}

		if (search.getModel() != null && !search.getModel().equals("")) {
			query.setParameter("model", search.getModel());
		}

		if (search.getIstasyon_DurakAdi() != null
				&& !search.getIstasyon_DurakAdi().equals("")) {
			query.setParameter("istasyon_DurakAdi", "%"
					+ search.getIstasyon_DurakAdi().toLowerCase() + "%");
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		return query.getResultList();

	}

}
