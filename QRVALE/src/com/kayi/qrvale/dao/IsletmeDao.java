package com.kayi.qrvale.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.kayi.qrvale.dao.search.IsletmeSearch;
import com.kayi.qrvale.model.entity.Isletme;

@Stateless
public class IsletmeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Isletme IsletmeKaydet(Isletme isletme) {
		entityManager.persist(isletme);
		return isletme;
	}

	public Isletme IsletmeSil(Isletme isletme) {
		if (entityManager.contains(isletme)) {
			entityManager.remove(isletme);
			return isletme;
		} else {
			Isletme yeniIsletme = entityManager.merge(isletme);
			entityManager.remove(yeniIsletme);
			return yeniIsletme;
		}
	}

	public Isletme IsletmeGuncelle(Isletme isletme) {
		isletme = entityManager.merge(isletme);
		return isletme;
	}

	@SuppressWarnings("unchecked")
	public List<Isletme> isletmeGetir(IsletmeSearch isletmeSearch) {

		String sql = "select a from Isletme as a " + " join a.sokak as s "
				+ "join s.mahalle as m  " + " join m.ilce as ilc "
				+ "join ilc.il as ill  where 1=1 ";

		if (isletmeSearch.getIsletmeAdi() != null && !"".equals(isletmeSearch.getIsletmeAdi())) {
			sql += " and UPPER(a.isletmeAdi) like :isletmeAdi";
		}

		if (isletmeSearch.getSokak() != null) {
			sql += " and s=:sokak";
		}

		if (isletmeSearch.getMahalle() != null) {
			sql += " and m=:mahalle";
		}

		if (isletmeSearch.getIlce() != null) {
			sql += " and ilc=:ilce";
		}

		if (isletmeSearch.getIl() != null) {
			sql += " and ill=:il";
		}

		Query query = entityManager.createQuery(sql);

		if (isletmeSearch.getIsletmeAdi() != null
				&& !"".equals(isletmeSearch.getIsletmeAdi())) {
			query.setParameter("isletmeAdi", "%"
					+ isletmeSearch.getIsletmeAdi().toUpperCase() + "%");
		}

		if (isletmeSearch.getMahalle() != null) {
			query.setParameter("mahalle", isletmeSearch.getMahalle());
		}

		if (isletmeSearch.getSokak() != null) {
			query.setParameter("sokak", isletmeSearch.getSokak());
		}

		if (isletmeSearch.getIlce() != null) {
			query.setParameter("ilce", isletmeSearch.getIlce());
		}

		if (isletmeSearch.getIl() != null) {
			query.setParameter("il", isletmeSearch.getIl());
		}

		return query.getResultList();
	}

	public Isletme IsletmeGetirById(Integer id) {
		return (Isletme) entityManager
				.createQuery("select a from Isletme a where a.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Isletme> hepsiniGetir() {
		return entityManager.createQuery("select i from Isletme i")
				.getResultList();
	}
}
