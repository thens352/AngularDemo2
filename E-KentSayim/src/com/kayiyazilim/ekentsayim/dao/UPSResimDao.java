package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.UPSResim;
import com.kayiyazilim.ekentsayim.model.search.UPSResimSearch;

@Stateless
public class UPSResimDao extends DAO<UPSResim> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 174928729619711942L;

	/**
	 * 
	 */

	public UPSResimDao() {
		super(UPSResim.class);
		
	}
	
	public UPSResim searchRest(int barkod, String cihazSeriNo) {
		try {
			if(barkod!=0 || cihazSeriNo != null && !cihazSeriNo.equals("")){
			String sql = "select a from UPSResim as a where 1=1";
			if (barkod!=0)
				sql += " and a.barkod=:barkod";
			if (cihazSeriNo != null && !cihazSeriNo.equals(""))
				sql += " and lower(a.cihazSeriNo) like :cihazSeriNo";
			Query query = entityManager.createQuery(sql);
			if (barkod!=0)
				query.setParameter("barkod", barkod);
			if (cihazSeriNo != null && !cihazSeriNo.equals(""))
				query.setParameter("cihazSeriNo", cihazSeriNo.toLowerCase());
			
			return (UPSResim) query.getSingleResult();}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<UPSResim> search(UPSResimSearch search) {
		String sql = "select a from UPSResim as a " + " where 1=1 ";

		if (search.getBolge() != null) {
			sql += " and a.bolge=:bolge";
		}

		if (search.getKullanici() != null) {
			sql += " and a.kullanici=:kullanici";
		}

		if (search.getBarkod() != 0) {
			sql += " and a.barkod like :barkod";
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

		return query.getResultList();
	}

}
