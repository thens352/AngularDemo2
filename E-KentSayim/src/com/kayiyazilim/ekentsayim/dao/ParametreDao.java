package com.kayiyazilim.ekentsayim.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.kayiyazilim.ekentsayim.model.entity.Parametre;
import com.kayiyazilim.ekentsayim.model.search.ParametreSearch;
import com.kayiyazilim.ekentsayim.model.type.Alan;
import com.kayiyazilim.ekentsayim.model.type.Ekran;

@Stateless
public class ParametreDao extends DAO<Parametre> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -588054794336029625L;

	public ParametreDao() {
		super(Parametre.class);
	}

	@SuppressWarnings("unchecked")
	public List<Parametre> getParametreListesi(Ekran ekran, Alan alan) {

		return entityManager
				.createQuery(
						"select p from Parametre p where p.ekran=:ekran and p.alan=:alan")
				.setParameter("ekran", ekran).setParameter("alan", alan)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Parametre> search(ParametreSearch search) {
		String sql = "select a from Parametre as a " + " where 1=1 ";

		if (search.getEkran() != null) {
			sql += " and a.ekran=:ekran";
		}

		if (search.getAlan() != null) {
			sql += " and a.alan=:alan";
		}

		if (search.getDeger() != null && !search.getDeger().equals("")) {
			sql += " and LOWER(a.deger) like :deger";
		}

		Query query = entityManager.createQuery(sql);

		if (search.getEkran() != null) {
			query.setParameter("ekran", search.getEkran());
		}

		if (search.getAlan() != null) {
			query.setParameter("alan", search.getAlan());
		}

		if (search.getDeger() != null && !search.getDeger().equals("")) {
			query.setParameter("deger", "%" + search.getDeger().toLowerCase()
					+ "%");
		}

		return query.getResultList();
	}

}
