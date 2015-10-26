package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.Bolge;
import com.kayiyazilim.ekentsayim.model.search.BolgeSearch;

@Stateless
public class BolgeDao extends DAO<Bolge> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2152092836667730189L;

	public BolgeDao() {
		super(Bolge.class);

	}

	@SuppressWarnings("unchecked")
	public List<Bolge> search(BolgeSearch search) {

		String sql = "select a from Bolge as a " + " where 1=1";

		if (search.getAd() != null && !search.getAd().equals("")) {
			sql += " and LOWER(a.ad) like :ad";
		}

		Query query = entityManager.createQuery(sql);

		if (search.getAd() != null && !search.getAd().equals("")) {
			query.setParameter("ad", "%" + search.getAd().toLowerCase() + "%");
		}

		return query.getResultList();
	}

}
