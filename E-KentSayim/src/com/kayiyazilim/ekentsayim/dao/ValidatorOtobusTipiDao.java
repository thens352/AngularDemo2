package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.ValidatorOtobusTipi;
import com.kayiyazilim.ekentsayim.model.search.ValidatorOtobusTipiSearch;

@Stateless
public class ValidatorOtobusTipiDao extends DAO<ValidatorOtobusTipi> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259423624025731523L;

	/**
	 * 
	 */

	public ValidatorOtobusTipiDao() {
		super(ValidatorOtobusTipi.class);
		// TODO Auto-generated constructor stub
	}
	
	public ValidatorOtobusTipi searchRest(int barkod, String cihazSeriNo) {
		try {
			if(barkod!=0 || cihazSeriNo != null && !cihazSeriNo.equals("")){
				String sql = "select a from ValidatorOtobusTipi as a where 1=1";
				if (barkod!=0)
					sql += " and a.barkod=:barkod";
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
				Query query = entityManager.createQuery(sql);
				if (barkod!=0)
					query.setParameter("barkod", barkod);
				if (cihazSeriNo != null && !cihazSeriNo.equals(""))
					query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());
				
				return (ValidatorOtobusTipi) query.getSingleResult();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ValidatorOtobusTipi> search(ValidatorOtobusTipiSearch search) {
		String sql = "select a from ValidatorOtobusTipi as a " + " where 1=1";

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

		if (search.getOtobusKoseNumarasi() != null
				&& !"".equals(search.getOtobusKoseNumarasi())) {
			sql += " and LOWER(a.otobusKoseNumarasi) like :otobusKoseNumarasi";
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

		if (search.getModel() != null && !search.getModel().equals("")) {
			query.setParameter("model", "%" + search.getModel().toLowerCase()
					+ "%");
		}

		if (search.getOtobusKoseNumarasi() != null
				&& !search.getOtobusKoseNumarasi().equals("")) {
			query.setParameter("otobusKoseNumarasi", "%"
					+ search.getOtobusKoseNumarasi().toLowerCase() + "%");
		}

		if (search.getDurum() != null) {
			query.setParameter("durum", search.getDurum());
		}

		return query.getResultList();
	}

}
