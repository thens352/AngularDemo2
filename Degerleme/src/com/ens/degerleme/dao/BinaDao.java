package com.ens.degerleme.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ens.degerleme.dao.searchobject.BinaSearch;
import com.ens.degerleme.model.entity.Bina;

@Stateless
public class BinaDao {

	@PersistenceContext
	EntityManager entityManager;

	BinaSearch binaSearch;

	public void binaKaydet(Bina bina) {
		entityManager.persist(bina);
	}

	public Bina binaGuncelle(Bina bina) {
		return entityManager.merge(bina);
	}

	@SuppressWarnings("unchecked")
	public List<Bina> hepsiniGetir() {

		return entityManager.createQuery("select i from Bina i")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Bina> binaGetir(BinaSearch binaSearch) {

		String sql = "select a from Bina as a " + " join a.sokak as s "
				+ "join s.mahalle as m  " + " join m.ilce as ilc "
				+ "join ilc.il as ill  where 1=1 ";

		if (binaSearch.getId() != null && 0 != binaSearch.getId()) {
			sql += " and a.id=:id";
		}

		if (binaSearch.getSokak() != null) {
			sql += " and s=:sokak";
		}

		if (binaSearch.getMahalle() != null) {
			sql += " and m=:mahalle";
		}
		
		if (binaSearch.getIlce() != null) {
			sql += " and ilc=:ilce";
		}

		if (binaSearch.getIl() != null) {
			sql += " and ill=:il";
		}

		if (binaSearch.getKapiNo() != null) {
			sql += " and a.kapiNo=:kapiNo";
		}

		if (binaSearch.getDaireNo() != null) {
			sql += " and a.daireNo=:daireNo";
		}

		if (binaSearch.getAdres() != null) {
			sql += " and a.adres=:adres";
		}

		Query query = entityManager.createQuery(sql);

		if (binaSearch.getId() != null && 0 != binaSearch.getId()) {
			query.setParameter("id", binaSearch.getId());
		}

		if (binaSearch.getMahalle() != null) {
			query.setParameter("mahalle", binaSearch.getMahalle());
		}
		
		if (binaSearch.getSokak() != null) {
			query.setParameter("sokak", binaSearch.getSokak());
		}
		
		if (binaSearch.getIlce() != null) {
			query.setParameter("ilce", binaSearch.getIlce());
		}
		
		if (binaSearch.getIl() != null) {
			query.setParameter("il", binaSearch.getIl());
		}
		
		if (binaSearch.getKapiNo() != null) {
			query.setParameter("kapiNo", binaSearch.getKapiNo());
		}
		
		if (binaSearch.getDaireNo() != null) {
			query.setParameter("daireNo", binaSearch.getDaireNo());
		}
		
		if (binaSearch.getAdres() != null) {
			query.setParameter("adres", binaSearch.getAdres());
		}
		return query.getResultList();
	}

	public Bina binaGetir(Integer id) {
		return entityManager.find(Bina.class, id);
	}
}
